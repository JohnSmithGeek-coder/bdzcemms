$(document).ready(function () {
    $(".passive").click(function (e) { 
        e.preventDefault();
        var settings = {
            url: "query",
            method: "POST",
            data: {
            	"queryType": "page",
            	"pageIndex": $(this).text(),
            	"pageSize": 10,
            },
            success: function(data){
            	$('#data-table').replaceWith(data)
            }
        };
        $.ajax(settings);
    });
    
    $("#pdfPreview").click(function (e) {
    	e.preventDefault();
    	var reportType = $("#reportTypeSelect").val();
    	var fileName = "资产报废明细表";
    	if (reportType == "1") {
    		fileName = "校内调拨明细表";
    	}
    	var sheetName = fileName;

    	var settings = {
    		url: "query",
    		method: "POST",
    		
    		xhr: function () {
    			var xhr = new XMLHttpRequest()
    			xhr.responseType = "blob"
    			return xhr
    		},
    		data: {
    			"queryType": "excel-table",
    			"filename": fileName,
    			"sheetname": sheetName
    		},	
    		
    		success: function(data, status, xhr) {
    			var tmp = xhr.getResponseHeader('Content-Disposition');
    			var filename = decodeURI(tmp.split("=")[1]);
    			
    			const url = window.URL.createObjectURL(new Blob([data]));
    			const link = document.createElement('a');
    			link.innerText = "111222";
    			link.href = url;
    			link.setAttribute('download', filename);
    			link.click();
    		},
    	};
    	
    	$.ajax(settings);
    });
    
    $("#previousPage").click(function (e) {
    	e.preventDefault();
    	var pageIndex = parseInt($(".active").text());
    	pageIndex = pageIndex - 1;
    	var settings = {
            url: "query",
            method: "POST",
            data: {
            	"queryType": "page",
            	"pageIndex": pageIndex,
            	"pageSize": 10,
            },
            success: function(data){
            	$('#data-table').replaceWith(data)
            }
        };
        $.ajax(settings);
    });
    
    $("#nextPage").click(function (e) {
    	e.preventDefault();
    	var pageIndex = parseInt($(".active").text());
    	pageIndex = pageIndex + 1;
    	var settings = {
            url: "query",
            method: "POST",
            data: {
            	"queryType": "page",
            	"pageIndex": pageIndex,
            	"pageSize": 10,
            },
            success: function(data){
            	$('#data-table').replaceWith(data)
            }
        };
        $.ajax(settings);
    });
});