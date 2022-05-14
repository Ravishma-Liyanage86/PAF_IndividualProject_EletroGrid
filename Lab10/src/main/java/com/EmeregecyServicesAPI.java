package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

@WebServlet("/EmergencyServicesAPI")
public class EmeregecyServicesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//creating a Emergency Service Management model class type object
	EmergencyServicesManagement eServiceObj = new EmergencyServicesManagement();

	public EmeregecyServicesAPI() {
		super();

	}

	
	//API for view all Emergency Service requests made
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
  
	//API for make an Emergency Service request
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//passing form values to model class through the object
		String requestOutput = eServiceObj.addEmergencyServiceRequest(request.getParameter("meterFailure"),
				request.getParameter("otherIssue"), 
				request.getParameter("reqDate"), 
				request.getParameter("timePeriod"),
				Integer.parseInt(request.getParameter("phoneNo")),
				request.getParameter("desc"),
				request.getParameter("address"));

		response.getWriter().write(requestOutput);

		doGet(request, response);
	}

	//API for update an Emergency Service request
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map paras = getParasMap(request);

		//passing updated form values to model class through the object
		String updateOutput = eServiceObj.updateEmergencyServiceRequest(paras.get("hidEServiceIDSave").toString(),
				paras.get("meterFailure").toString(), 
				paras.get("otherIssue").toString(),
				paras.get("reqDate").toString(), 
				paras.get("timePeriod").toString(), 
				paras.get("phoneNo").toString(),
				paras.get("desc").toString(), 
				paras.get("address").toString());

		response.getWriter().write(updateOutput);

	}

	//API for delete an Emergency Service request
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map paras = getParasMap(request);

		//passing Service request's ID  to model class through the object for delete it
		String deleteOutput = eServiceObj.deleteEmergencyServiceRequest(paras.get("serviceID").toString());

		response.getWriter().write(deleteOutput);

	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
}
