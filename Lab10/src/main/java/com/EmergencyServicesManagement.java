package com;

import java.sql.*;

public class EmergencyServicesManagement {

	// A common method which use to connect to the database

	private Connection myConnection() {

		// declaring a connection type variable which returns the connection
		Connection myConnect = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: Database name, user name, password
			myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// returning the connection
		return myConnect;
	}

	// Creating a method to add new Emergency Service Request
	public String addEmergencyServiceRequest(String electricityMeter, String otherIssue, String requestedDate,
			String requiredTime, Integer phoneNo, String description, String address) {

		String requestOutput = "";

		try {
			// creating theDB connection
			Connection myConnect = myConnection();

			// check whether successfully connected with the db or not
			if (myConnect == null) {
				return "Error occured while connecting to the database for add the request";
			}

			// creating a prepared statement
			String myQuerry = " insert into emergency_service( EService_ID, Electricity_Meter_Failure, Other_Issue, Requested_Date, Required_Time_Period, Phone_Number, Problem_Description, Address)"
					+ "values( ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = myConnect.prepareStatement(myQuerry);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, electricityMeter);
			preparedStmt.setString(3, otherIssue);
			preparedStmt.setString(4, requestedDate);
			preparedStmt.setString(5, requiredTime);
			preparedStmt.setInt(6, phoneNo);
			preparedStmt.setString(7, description);
			preparedStmt.setString(8, address);

			// execute the statement

			preparedStmt.execute();

			// closing the database connection
			myConnect.close();

			// states whether emergency service request has made successfully
			String newRequest = viewEmergencyServiceRequest();
			requestOutput = "{\"status\":\"success\",\"data\":\"" + newRequest + "\"}";
			
		} catch (Exception e) {

			// displaying if an error occur while adding the request
			requestOutput = "{\"status\":\"error\", \"data\":\"Error while making your emergency service request!!.\"}";
			System.err.println(e.getMessage());
		}

		// returning the output
		return requestOutput;
	}

	// Creating a method to view created Emergency Service Request
	public String viewEmergencyServiceRequest() {

		String viewOutput = "";

		try {
			// creating theDB connection
			Connection myConnect = myConnection();

			// check whether successfully connected with the db or not
			if (myConnect == null) {
				return "Error occured while connecting to the database for view the request";
			}

			// prepare the html table to display the emergency service request made by
			// customer

			viewOutput = "<table class=\"table table-info table-striped\"><tr><th>Request Type:Electricity Meter Failure</th><th>Request Type:Other Issue</th>"
					+ "<th>Requested Date</th>" + "<th>Required Time Period(Within)</th>"
					+ "<th>Phone Number</th><th>Description about the Problem</th><th>Address</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			// creating the query to view all emergency service request made by customer
			String myQuerry = "select * from emergency_service";
			Statement myStmt = myConnect.createStatement();
			ResultSet myResult = myStmt.executeQuery(myQuerry);

			// iterate through the rows to read all the emergency services request made by
			// customer

			while (myResult.next()) {
				Integer serviceID = myResult.getInt("EService_ID");
				String electricityMeter = myResult.getString("Electricity_Meter_Failure");
				String otherIssue = myResult.getString("Other_Issue");
				String requestedDate = myResult.getString("Requested_Date");
				String requiredTime = myResult.getString("Required_Time_Period");
				Integer phoneNo = myResult.getInt("Phone_Number");
				String description = myResult.getString("Problem_Description");
				String address = myResult.getString("Address");

				// put the taken data into html table
				
				viewOutput +=  "<tr><td><input id='hidServiceIDUpdate' name='hidServiceIDUpdate' type='hidden' value='" + serviceID
						+ "'>" + electricityMeter + "</td>";
				viewOutput += "<td>" + otherIssue + "</td>";
				viewOutput += "<td>" + requestedDate + "</td>";
				viewOutput += "<td>" + requiredTime + "</td>";
				viewOutput += "<td>" + phoneNo + "</td>";
				viewOutput += "<td>" + description + "</td>";
				viewOutput += "<td>" + address + "</td>";
				//buttons
				viewOutput += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-success' data-serviceid='" + serviceID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-serviceid='" + serviceID + "'></td></tr>";


			}

			// closing the database connection
			myConnect.close();

			// Completing the Emergency service request table
			viewOutput += "</table>";

		} catch (Exception e) {
			// displaying if an error occur while reading the previously made request
			viewOutput = " Error! Unable to view your Emergency Service Request";
			System.err.println(e.getMessage());

		}

		// returning the output
		return viewOutput;

	}

	// creating a method to update Emergency Service Request
	public String updateEmergencyServiceRequest(String serviceId, String electricityMeter, String otherIssue,
			String requestedDate, String requiredTime, String phoneNo, String description, String address) {

		String updateOutput = "";

		try {

			// creating theDB connection
			Connection myConnect = myConnection();

			// check whether successfully connected with the db or not
			if (myConnect == null) {
				return "Error occured while connecting to the database for update the request";
			}

			// crating a prepared statement for executing the query
			String myQuery = "UPDATE emergency_service SET Electricity_Meter_Failure=?, Other_Issue=?, Requested_Date=?, Required_Time_Period=?, Phone_Number=?, Problem_Description=?, Address=? WHERE EService_ID=?";

			PreparedStatement myPreparedStmt = myConnect.prepareCall(myQuery);

			// binding values
			myPreparedStmt.setString(1, electricityMeter);
			myPreparedStmt.setString(2, otherIssue);
			myPreparedStmt.setString(3, requestedDate);
			myPreparedStmt.setString(4, requiredTime);
			myPreparedStmt.setString(5, phoneNo);
			myPreparedStmt.setString(6, description);
			myPreparedStmt.setString(7, address);
			myPreparedStmt.setString(8, serviceId);

			// execute the statement
			myPreparedStmt.execute();

			// closing the database connection
			myConnect.close();

			// states whether emergency service request has updated successfully
			String newRequest = viewEmergencyServiceRequest();
			updateOutput = "{\"status\":\"success\",\"data\":\"" + newRequest + "\"}";
			
			
		} catch (Exception e) {
			// displaying if an error occur while updating the previously made request
			updateOutput = "{\"status\":\"error\", \"data\":\"Error while updating your emergency service request!!.\"}";
			System.err.println(e.getMessage());

		}

		// returning the output
		return updateOutput;

	}

	// creating a method to delete an Emergency Service Request
	public String deleteEmergencyServiceRequest(String eServiceId) {

		String deleteOutput = "";

		try {

			// creating theDB connection
			Connection myConnect = myConnection();

			// check whether successfully connected with the db or not
			if (myConnect == null) {
				return "Error occured while connecting to the database for add the request";
			} else {
				// create a prepared statement to run the SQL query
				String myQuery = "DELETE from emergency_service where EService_ID=?";

				PreparedStatement myPreparedStmt = myConnect.prepareStatement(myQuery);

				// binding values
				myPreparedStmt.setString(1, eServiceId);

				// execute the statement
				myPreparedStmt.execute();

				// closing the database connection
				myConnect.close();

				// states whether emergency service request has updated successfully
				String newRequest = viewEmergencyServiceRequest();
				deleteOutput = "{\"status\":\"success\",\"data\":\"" + newRequest + "\"}";
				
			}

		} catch (Exception e) {
			// displaying if an error occur while deleting the previously made request
			deleteOutput= "{\"status\":\"error\", \"data\":\"Error while updating your emergency service request!!.\"}";
			System.err.println(e.getMessage());

		}

		// returning the output
		return deleteOutput;
	}
}

