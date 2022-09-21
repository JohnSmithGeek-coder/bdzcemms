const column = new Map();
column.set("设备编号","zcbh");
column.set("设备名称","zcmc");
column.set("设备分类号","zcflh");
column.set("变动数量","bdsl");
column.set("金额","JE");
column.set("厂家","Cj");
column.set("供货商","ghs");
column.set("发票号","fph");
column.set("现状","Xz");
column.set("使用单位号","lydwh");
column.set("使用单位","lydwm");
column.set("使用人编号","SYRBH");
column.set("使用人","SYR");
column.set("使用方向","syfx");
column.set("购置日期","Ggrq");
column.set("单据号","Djh");
column.set("审核状态","SHZT");
column.set("变动原因","bdyy");
column.set("变动日期","bdrq");
column.set("变动单价","bddj");
column.set("调入单位号","zrdwh");
column.set("调入单位","zrdwm");

let prevPos = 0;

function getCaretPosition(element) {
  var caretOffset = 0;
  var doc = element.ownerDocument || element.document;
  var win = doc.defaultView || doc.parentWindow;
  var sel;
  if (typeof win.getSelection != "undefined") {
    sel = win.getSelection();
    if (sel.rangeCount > 0) {
      var range = win.getSelection().getRangeAt(0);
      var preCaretRange = range.cloneRange();
      preCaretRange.selectNodeContents(element);
      preCaretRange.setEnd(range.endContainer, range.endOffset);
      caretOffset = preCaretRange.toString().length;
    }
  } else if ((sel = doc.selection) && sel.type != "Control") {
    var textRange = sel.createRange();
    var preCaretTextRange = doc.body.createTextRange();
    preCaretTextRange.moveToElementText(element);
    preCaretTextRange.setEndPoint("EndToEnd", textRange);
    caretOffset = preCaretTextRange.text.length;
  }
  return caretOffset;
}


function setCaretPosition(element, offset) {
  var range = document.createRange();
  var sel = window.getSelection();
 
  //select appropriate node
  var currentNode = null;
  var previousNode = null;
 
  for (var i = 0; i < element.childNodes.length; i++) {
    //save previous node
    previousNode = currentNode;
 
    //get current node
    currentNode = element.childNodes[i];
    //if we get span or something else then we should get child node
    while (currentNode.childNodes.length > 0) {
      currentNode = currentNode.childNodes[0];
    }
 
    //calc offset in current node
    if (previousNode != null) {
      offset -= previousNode.length;
    }
    //check whether current node has enough length
    if (offset <= currentNode.length) {
      break;
    }
  }
  //move caret to specified offset
  if (currentNode != null) {
    range.setStart(currentNode, offset);
    range.collapse(true);
    sel.removeAllRanges();
    sel.addRange(range);
  }
}


function placeCaretAtEnd(el) {
    el.focus();
    if (typeof window.getSelection != "undefined"
            && typeof document.createRange != "undefined") {
        var range = document.createRange();
        range.selectNodeContents(el);
        range.collapse(false);
        var sel = window.getSelection();
        sel.removeAllRanges();
        sel.addRange(range);
    } else if (typeof document.body.createTextRange != "undefined") {
        var textRange = document.body.createTextRange();
        textRange.moveToElementText(el);
        textRange.collapse(false);
        textRange.select();
    }
}

function setTextToCurrentPos(op) {
	$("#condition-iptdiv").focus();
	var caretPos = getCaretPosition(document.getElementById("condition-iptdiv"));
	var text = $("#condition-iptdiv").text();
	text = text.slice(0, caretPos) + op + text.slice(caretPos);
	text = text.replace(/(AND|NOT|OR|\(|\)|>=|!=|==|<=>|<=|LIKE|=|<|>|\"[^\"]*\")/g, function (match) {
        if (match == "AND" || match == "NOT" || match == "OR") {
            return "<span style=\"color:tomato; font-weight:bold;\">"+ match +"</span>";
        }
        else if (match == "(" || match == ")") {
            return "<span style=\"color:magenta; font-weight:bold;\">"+ match +"</span>";
        }
        else if (match[0] == "\"") {
            return "<span style=\"color:green;\">"+ match +"</span>";
        }
        else {
            return "<span style=\"color:deepskyblue; font-weight:bold;\">"+ match +"</span>";
        }
    });
	$("#condition-iptdiv").html(text+" ");
	setCaretPosition(document.getElementById("condition-iptdiv"), caretPos + op.length);
	//placeCaretAtEnd(document.getElementById("condition-iptdiv"));
}

$(document).ready(function () {
    $("#reportTypeSelect").change(function (e) { 
        e.preventDefault();
        var reportType = $("#reportTypeSelect").val();
        if (reportType != '') {
            var settings = {
                url: "query",
                method: "POST",
                data: {
                	"queryType": "basic",
                	"reportType": reportType,
                	"pageIndex": 1,
                	"pageSize": 10,
                },
                success: function(data){
                	$('#data-table').replaceWith(data)
                }
            };
            $.ajax(settings);
        }
    });
    
    $("#user-clear").click(function (e) {
    	e.preventDefault();
    	$("#syr").attr("number", "");
        $("#syr").val("");
    });
    
    $("#userSearchBtn").click(function (e) { 
        e.preventDefault();
        var reportType = $("#reportTypeSelect").val();
        if (reportType == "") {
        	return;
        }
        
        var settings = {
            url: "query",
            method: "GET",
            data: {
            	"queryType": "user",
            },
	        success: function(data){
	        	$('#user-table').replaceWith(data);
	        }
        };
        $.ajax(settings);
        
    });
    
    $("#commonQueryBtn").click(function (e) { 
        e.preventDefault();
        var reportType = $("#reportTypeSelect").val();
        var zcflh = $("#equipCate").val();
        var Rzrq = $("#equipEaStartDate").val() + "," + $("#equipEaEndDate").val();
        var bdrq = $("#equipChangeStartDate").val() + "," + $("#equipChangeEndDate").val();
        var lydwh = $("#useDepartSelect").val();
        var SYR = $("#syr").attr("number");
        var syfx = $("#useDerictionSelect").val();
        var zrdwh = $("#toDepartSelect").val();
        var zcbh = $("#equipNumStart").val() + "," + $("#equipNumEnd").val();
        var zcmc = $("#equipNameQType").val() + "," + $("#equipNameSub").val();
        var settings = {
            url: "query",
            method: "GET",
            data: {
            	"queryType": "common",
            	"reportType": reportType,
            	"pageIndex": 1,
            	"pageSize": 10,
            	"zcflh": zcflh,
            	"Rzrq": Rzrq,
            	"bdrq": bdrq,
            	"lydwh": lydwh,
            	"SYR": SYR,
            	"syfx": syfx,
            	"zrdwh": zrdwh,
            	"zcbh": zcbh,
            	"zcmc": zcmc,
            },
            success: function(data){
            	$('#data-table').replaceWith(data);
            },
        };
          
        $.ajax(settings).done(function (response) {
            alert("查询成功！");
        });
    });
    
    $("#common-reset").click(function (e) {
    	e.preventDefault();
    	$("#equipCate").prop('selectedIndex', 0);
    	$("#equipEaStartDate").val("2015-01-01");
    	$("#equipEaEndDate").val("");
    	$("#equipChangeStartDate").val("2015-01-01");
    	$("#equipChangeEndDate").val("");
    	$("#useDepartSelect").val("");
    	$("#syr").attr("number", "");
        $("#syr").val("");
        $("#useDerictionSelect").prop('selectedIndex', 0);
        $("#equipNumStart").val("");
        $("#equipNumEnd").val("");
        $("#equipNameSub").val("");
        $("#equipNameQType").prop('selectedIndex', 0);
    });
    
    $("#bunch-search").click(function (e) {
    	e.preventDefault();
    	let condition = $("#bunchSelect").find("option:selected").text();
    	let type = $("#bunchSelect").find("option:selected").attr("mytype");
    	let columnName = column.get(condition);
    	let valuestr = $("#bunch-input").val();
    	if (valuestr == "") {
    		return;
    	}
    	let values = valuestr.split(",");
    	if (!(type == "numerical")) {
    		for (let i = 0; i < values.length; i++) {
    			values[i] = "\'" + values[i] + "\'";
    		}
    	}
    	let ans = "(" + columnName + " = " + values[0] + " ";
    	for (let i = 1; i < values.length; i++) {
			ans = ans + "OR " + columnName + " = " + values[i] + " "; 
		}
    	ans += ")";
    	console.log(ans);
    	var reportType = $("#reportTypeSelect").val();
    	var settings = {
            url: "query",
            method: "GET",
            data: {
            	"queryType": "bunch",
            	"reportType": reportType,
            	"pageIndex": 1,
            	"pageSize": 10,
            	"condition": ans,
            },
            success: function(data){
            	$('#data-table').replaceWith(data);
            },
        };
    	$.ajax(settings).done(function (response) {
            alert("查询成功！");
        });
    });
    
    $("#bunch-reset").click(function (e){
    	e.preventDefault();
    	$("#bunch-input").val("");
    	$("#bunchSelect").prop('selectedIndex', 0);
    });
    
    $("#bracketBtn").click(function (e){
    	e.preventDefault();
    	setTextToCurrentPos("() ");
    });
    
    $("#bracketLeftBtn").click(function (e){
    	e.preventDefault();
    	setTextToCurrentPos("( ");
    });
    
    $("#bracketRightBtn").click(function (e){
    	e.preventDefault();
    	setTextToCurrentPos(") ");
    });
    
    $("#andBtn").click(function (e){
    	e.preventDefault();
    	setTextToCurrentPos("AND ");
    });
    
    $("#orBtn").click(function (e){
    	e.preventDefault();
    	setTextToCurrentPos("OR ");
    });
    
    $("#notBtn").click(function (e){
    	e.preventDefault();
    	setTextToCurrentPos("NOT ");
    });
    
    $("#addBtn").click(function (e){
    	e.preventDefault();
    	var con = $("#conSelect").find("option:selected").text();
    	var conType = $("#conSelect").find("option:selected").attr("mytype");
    	var op = $("#opSelect").val();
    	var sval = $("#searchField").val();
    	var ans = "";
    	if (conType == "numerical") {
    		ans = "\"" + con + "\" " + op + " " + sval + " ";
    	}
    	else {
    		if (op == "LIKE") {
    			ans = "\"" + con + "\" " + op + " " + "\'%" + sval + "%\' ";
    		}
    		else {
    			ans = "\"" + con + "\" " + op + " " + "\'" + sval + "\' ";
    		}
    	}
    	setTextToCurrentPos(ans);
    });
    
    $("#special-search").click(function (e) {
    	e.preventDefault();
    	let condition = $("#condition-iptdiv").text();
    	if (condition == "") {return;}
    	condition = condition.replace(/\"[^\"]*\"/g, function (match) {
            return column.get(match.slice(1, -1));
        });
    	condition = condition.replace(/ /g, " ");
    	console.log(condition);
    	condition = "(" + condition + ")";
    	var reportType = $("#reportTypeSelect").val();
    	var settings = {
            url: "query",
            method: "GET",
            data: {
            	"queryType": "special",
            	"reportType": reportType,
            	"pageIndex": 1,
            	"pageSize": 10,
            	"condition": condition,
            },
            success: function(data){
            	$('#data-table').replaceWith(data);
            },
        };
    	$.ajax(settings).done(function (response) {
            alert("查询成功！");
        });
    });
    
    $("#special-reset").click(function (e){
    	e.preventDefault();
    	$("#condition-iptdiv").html("");
    	$("#conSelect").prop('selectedIndex', 0);
    	$("#opSelect").prop('selectedIndex', 0);
    	$("#searchField").val("");
    });
    
    $("#condition-iptdiv").blur(function (e) {
    	prevPos = getCaretPosition(document.getElementById("condition-iptdiv"));
    }); 
    
    $("#condition-iptdiv").focus(function (e) {
    	setCaretPosition(document.getElementById("condition-iptdiv"), prevPos);
    });
});

function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("userSearchInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("user-table");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}

