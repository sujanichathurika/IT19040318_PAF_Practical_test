package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class fundsAPI
 */
@WebServlet("/fundsAPI")
public class fundsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
		fund fundObj = new fund();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fundsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
		
				String output = fundObj.insertFund(request.getParameter("fundCode"), 
				request.getParameter("fundName"), 
				request.getParameter("fundPrice"), 
				request.getParameter("fundDesc")); 
				response.getWriter().write(output); 
		
	}
	
		
	/**
	* @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	*/
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			Map paras = getParasMap(request); 
		
			String output = fundObj.updateFund(paras.get("hidFundIDSave").toString(), 
							paras.get("fundCode").toString(), 
							paras.get("fundName").toString(), 
							paras.get("fundPrice").toString(), 
							paras.get("fundDesc").toString()); 
			
			response.getWriter().write(output); 
	}
	
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
			Map paras = getParasMap(request);
		
			String output = fundObj.deleteFund(paras.get("ID").toString()); 
			
			response.getWriter().write(output); 
	}

	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 {
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
				scanner.useDelimiter("\\A").next() : "";
		 
				scanner.close();
		 
				String[] params = queryString.split("&");
		 
				for (String param : params)
		 { 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
			 }
			catch (Exception e)
			 {
			 }
			return map;
			}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());}**/
	

}	

	

	
