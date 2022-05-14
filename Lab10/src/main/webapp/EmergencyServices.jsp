<%@page import="com.EmergencyServicesManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Emergency Services Management</title>
<!-- link the CSS files and bootstrap -->

<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="EService.css">

<!-- link the jquery file and JS file -->
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/EmeregencyService.js"></script>
</head>
<body>
	<div class="container">
		<div class="row g-3">
			<div class="col-6">
				<div class="header">
					<h1>Electro Grid System</h1>
					<br></br>
				</div>
				<br></br>
				
				<h1 id="au">Welcome to Emergency Services Management</h1>
                  <br></br>
                 <p id="t1">Please provide  the details to make An Emergency Service Request </p>
                 
                <!-- Emergency service request form --> 
				<form id="formEmergencyService" name="formEmergencyService">
					<table class="tablestyle">
						<tr>
							<th>
								<div class="formstyle1">
									<div class="row mb-3">
										Request Type:Electricity Meter Failure<br> <input
											id="meterFailure" name="meterFailure" type="text"
											class="form-control form-control-lg"> <br>
									</div>
									<div class="row mb-3">
										Request Type:Other Issue<br> <input id="otherIssue"
											name="otherIssue" type="text"
											class="form-control form-control-lg"> <br>
									</div>

									<div class="row mb-3">
										Requested_Date: <br> <input id="reqDate" name="reqDate"
											type="text" class="form-control form-control-lg"> <br>

									</div>
									<div class="row mb-3">
										What is the time period you hope to get the service:<br>
										<input id="timePeriod" name="timePeriod" type="text"
											class="form-control form-control-lg"> <br>
									</div>
								</div>

							</th>
							<th>
								<div class="formstyle2">
									<div class="row mb-3">
										Please provide a phone number to contact you: <br> <input
											id="phoneNo" name="phoneNo" type="text" placeholder="1234-567-890" pattern="[0-9]{10}" maxlength=10 class="form-control form-control-lg"> <br>
									</div>
									<div class="row mb-3">
										Please give us a brief idea about your problem: <br> <input
											id="desc" name="desc" type="text"
											class="form-control form-control-lg"> <br>
									</div>
									<div class="row mb-3">
										Your address:<br> <input id="address" name="address"
											type="text" class="form-control form-control-lg"> <br>
									</div>
									<br>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" id="gridCheck">
										<label class="form-check-label" for="gridCheck">
											<h3 class="text-end">I agree to all the terms and
												condition Of ElectroGrid</h3>
										</label> 
										<!-- Submitting the form  -->
										<input id="btnSave" name="btnSave" type="button"
											value="Make the Emergency Service Request"
											class="btn btn-primary"> <input type="hidden"
											id="hidEServiceIDSave" name="hidEServiceIDSave" value="">
									</div>
								</div>

							</th>

						</tr>
					</table>
				</form>
				<br></br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<br></br>
				<div id="alertError" class="alert alert-warning"></div>
				<br>
				<p id="t1">You can view your Emergency Service requests below </p>
				<br>
				<p id="t1">You can update or remove your Emergency Service requests as per your wish </p>
				<div id="divItemsGrid">
					<%
					EmergencyServicesManagement serviceObj = new EmergencyServicesManagement();
					out.print(serviceObj.viewEmergencyServiceRequest());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
