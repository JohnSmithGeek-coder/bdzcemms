$(document).ready(function () {
    $(".user-tr").click(function (e) { 
    	e.preventDefault();
    	
		var name = $(this).children("td.name").text();
        var number = $(this).children("td.number").text();
        $("#syr").attr("number", number);
        $("#syr").val(name);
    });
    
    $(".user-tr").mouseenter(function (e) { 
    	$(this).css({
    		"background-color":"aqua"
    	});
    });
    
    $(".user-tr").mouseleave(function (e) { 
    	$(this).css({
    		"background-color":"white"
    	});
    });
    
    
});