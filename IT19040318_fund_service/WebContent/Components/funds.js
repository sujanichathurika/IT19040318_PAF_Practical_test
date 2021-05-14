$(document).ready(function()
{

 
 $("#alertSuccess").hide();
 $("#alertError").hide();

});

// SAVE ============================================
$(document).on("click", "#btnSave", function (event) {
    
	// Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    
    // Form validation-------------------
    var status = validateFundForm();
    if (status != true) 
    {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    
    // If valid-------------------------
    var type = ($("#hidFundIDUpdate").val() == "") ? "POST" : "PUT";
    
    $.ajax
    (
    	{
    		
    		 url : "fundsAPI", 
    		 type : type, 
    		 data : $("#formfund").serialize(), 
    		 dataType : "text", 
    		 complete : function(response, status) 
    		 { 
    			 onFundSaveComplete(response.responseText, status); 
    		 }
    	});
});

function onFundSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 }
$("#hidFundIDUpdate").val("");
$("#formfund")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function (event) 
	{
	    $("#hidFundIDSave").val($(this).closest("tr").find('#hidFundUpdate').val());
	    $("#fundCode").val($(this).closest("tr").find('td:eq(0)').text());
	    $("#fundName").val($(this).closest("tr").find('td:eq(1)').text());
	    $("#fundPrice").val($(this).closest("tr").find('td:eq(2)').text());
	    $("#fundDesc").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "fundsAPI",
		 type : "DELETE",
		 data : "fundID=" + $(this).data("fundid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
			 onFundDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onFundDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}



// CLIENT-MODEL================================================================
function validateFundForm() {
    // CODE
    if ($("#fundCode").val().trim() == "") 
	    {
	        return "Insert Fund Code.";
	    }
    // NAME
    if ($("#fundName").val().trim() == "") 
	    {
	        return "Insert Fund Name.";
	    }
    // PRICE-------------------------------
    if ($("#fundPrice").val().trim() == "") 
	    {
	        return "Insert Fund Price.";
	    }
    // is numerical value
    var tmpPrice = $("#fundPrice").val().trim();
    if (!$.isNumeric(tmpPrice)) 
	    {
	        return "Insert a numerical value for Fund Price.";
	    }
    // convert to decimal price
    $("#fundPrice").val(parseFloat(tmpPrice).toFixed(2));
    // DESCRIPTION------------------------
    if ($("#fundDesc").val().trim() == "") 
	    {
	        return "Insert Fund Description.";
	    }
    return true;
}

