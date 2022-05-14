//CLIENT  MODEL
$(document).on("click","#btnSave",function(event)
{
	//clear alerts
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	
	//form validation
	var status =  validateEmergencyServiceRequestForm();
	if(status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//if valid
	var type = ($("#hidEServiceIDSave").val() == "" ? "POST" : "PUT")
	$.ajax(
		{
			url : "EmergencyServicesAPI",
			type : type,
			data : $("#formEmergencyService").serialize(),
			datatype: "text",
			complete : function(response, status)
			{
				onEServiceSaveComplete(response.responseText, status);
			}
		});
	
		
});
//function for saving the Emergency Service request
function onEServiceSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved your request!.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		$("#alertError").text("Error while saving your emergency service request");
		$("#alertError").show();
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidEServiceIDSave").val("");
	$("#formEmergencyService")[0].reset();
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidEServiceIDSave").val($(this).data("serviceid")); 
		 $("#meterFailure").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#otherIssue").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#reqDate").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#timePeriod").val($(this).closest("tr").find('td:eq(3)').text()); 
	     $("#phoneNo").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#desc").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#address").val($(this).closest("tr").find('td:eq(6)').text()); 
		});

$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "EmergencyServicesAPI", 
		 type : "DELETE", 
		 data : "serviceID=" + $(this).data("serviceid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onEServiceDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
		
//function for delete a request
function onEServiceDeleteComplete(response, status) 
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





//validation function
function validateEmergencyServiceRequestForm(){
	
	//meter faliure
	if($("#meterFailure").val().trim()=="")
	{
		return "Please choose correct request type by providing yes or no";
	}
	
	
	//other issue
	if($("#otherIssue").val().trim()=="")
	{
		return "Please choose correct request type by providing yes or no";
	}
	
	//requested date
	if($("#reqDate").val().trim()=="")
	{
		return "Please provide the date which you make this request";
	}
	
	//required time period
	if($("#timePeriod").val().trim()=="")
	{
		return "Please provide the time period which you want this service within";
	}
	
	//phone number
	
	if($("#phoneNo").val().trim()=="")
	{
		return "Please give a phone number to contact you";
	}
	// is numerical value
		var telNo = $("#phoneNo").val().trim();
		if (!$.isNumeric(telNo))
	{
	return "Insert a numerical value for Phone number.";
	}
	
	// is phone number length valid
		var telNo = $("#phoneNo").val().trim();
		if (telNo.length!=10)
	{
	return "Insert a phone Nummber with 10 digits.";
	}
	//problem description
	if($("#desc").val().trim()=="")
	{
		return "Please give us a brief idea about your problem";
	}
	
	//address
	if($("#address").val().trim()=="")
	{
		return "Please provide your address";
	}
	
   return true;
}