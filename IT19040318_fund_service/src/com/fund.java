package com;

//IT19040318

//DE SILVA U.S.C
//FUND MANAGEMENT SERVICE

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class fund {
	
	//A common method to connect to the DB
	public  Connection connect() 
	 { 
			Connection con = null;
			
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
	 
				//Provide the correct details: DBServer/DBName,user name, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funddetails", "root" , ""); 
				
				//For Testing
				System.out.print("Successfully connected");
			} 
			catch (Exception e) 
			{e.printStackTrace();}
			
			return con; 
	 } 
	
	//insert
		public String insertFund(String code,String name, String price, String desc)
		 {
				String output = "";
				
				try
				{
						Connection con = connect();
						
						if (con == null)
						{return "Error while connecting to the database for inserting."; }
		 
						// create a prepared statement
						String query = "INSERT INTO funddetails (`fundId`, `fundCode`,`fundName`, `fundPrice`, `fundDesc`)"
										+ " values (?, ?, ?, ?, ?)";
		 
						PreparedStatement preparedStmt = con.prepareStatement(query);
		 
						// binding values
						 preparedStmt.setInt(1, 0); 
						 preparedStmt.setString(2, code);
						 preparedStmt.setString(3, name);
						 preparedStmt.setDouble(4, Double.parseDouble(price));
						 preparedStmt.setString(5, desc);
		
						 // execute the statement
						 preparedStmt.execute();
						 con.close();
						 
						 String newFunds = readFunds();
						 output = "{\"status\":\"success\", \"data\": \"" + 
								 newFunds + "\"}"; 
						 
						 
		       }
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the funds.\"}"; 
							 System.err.println(e.getMessage()); 
				}
				
				return output;
		 }
	
	//read
		public String readFunds() {
			
				String output = ""; 
			
				try
				 { 
						Connection con = connect(); 
						
						 if (con == null) 
						 {return "Error while connecting to the database for reading."; }
				 
						 // Prepare the html table to be displayed
						 output = "<table border='1'><tr><th>Fund Code</th>" +
						 "<th>Fund Name</th>" +
						 "<th>Fund Price</th>" + 
						 "<th>Fund Description</th>" +
						 "<th>Update</th><th>Remove</th></tr>"; 
					
						 String query = "select * from funddetails"; 
						 Statement stmt = con.createStatement(); 
						 ResultSet rs = stmt.executeQuery(query); 
			
			
						// iterate through the rows in the result set
						 while (rs.next()) 
						 { 
								 String fundId = Integer.toString(rs.getInt("fundId"));
								 String fundCode = rs.getString("fundCode");
								 String fundName = rs.getString("fundName"); 
								 String fundPrice = Double.toString(rs.getDouble("fundPrice")); 
								 String fundDesc = rs.getString("fundDesc"); 
						 
								 // Add into the html table
								 output += "<tr><td><input id ='hidFundUpdate' name='hidFundUpdate' type = 'hidden' value='" + fundId + "'> "+ fundCode +"</td>";
								 output += "<td>" + fundName + "</td>"; 
								 output += "<td>" + fundPrice + "</td>"; 
								 output += "<td>" + fundDesc + "</td>"; 
				 
								 //button
								 output += "<td><input name='btnUpdate' type='button' value='Update' "+" class='btnUpdate btn btn-secondary'></td>" 
								 
								 +"<td><input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger' data-fundid='" + fundId +"'></td></tr>";
			
						 }
						 
						 con.close();
				 
						 // Complete the html table
						 output += "</table>";
				  }
				 	catch (Exception e)
				 	{
						 output = "Error while reading the funds.";
						 System.err.println(e.getMessage());
				 	}
				
				 	return output;
				 }
		

	
	
	
	//update
	public String updateFund(String ID,String code, String name, String price, String desc)
	{
		 String output = "";
		 
		 try
		 {
			 
			 	Connection con = connect();
		 
			 	if (con == null)
			 	{return "Error while connecting to the database for updating."; }
		 
			 	// create a prepared statement
			 	String query = "UPDATE funddetails SET fundCode=?,fundName=?,fundPrice=?,fundDesc=?WHERE fundId=?";
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query);
		 
				 // binding values
			 	 preparedStmt.setString(1, code);
				 preparedStmt.setString(2, name);
				 preparedStmt.setDouble(3, Double.parseDouble(price));
				 preparedStmt.setString(4, desc);
				 preparedStmt.setInt(5,Integer.parseInt(ID));
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				
				 
				 String newFunds = readFunds();
				 output = "{\"status\":\"success\", \"data\": \"" + 
						 newFunds + "\"}"; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the funds.\"}"; 
			 System.err.println(e.getMessage()); 
		 }
		 
		 return output;
	}
	
	//delete
	public String deleteFund(String ID)
		 {
		 		String output = "";
		 		
		 		try
		 		{
					 Connection con = connect();
					 
					 if (con == null)
					 {return "Error while connecting to the database for deleting."; }
					 
					 // create a prepared statement
					 String query = "delete from funddetails where fundId=?";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 preparedStmt.setInt(5,Integer.parseInt(ID));
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newFunds = readFunds();
					 output = "{\"status\":\"success\", \"data\": \"" + 
							 newFunds + "\"}"; 
		 		}
		 		catch (Exception e)
		 		{
		 			output = "{\"status\":\"error\", \"data\": \"Error while updating the funds.\"}"; 
					System.err.println(e.getMessage()); 
		 		}
		 		
		 		return output;
		 } 

	
}
		
	
	

