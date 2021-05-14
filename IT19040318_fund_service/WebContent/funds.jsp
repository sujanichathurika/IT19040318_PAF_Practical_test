<%@ page import="com.fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>fund details</title>
<!-- Link Bootstrap, jQuery, and main.js to the index page -->
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/funds.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
			
			
				<!-- add the id attribute to the form and input elements, for jQuery to access them. -->

				<h2>Funds Management Details</h2>
				
				<form  id="formfund" name="formfund">
					Fund code: 
					<input id="fundCode" name="fundCode" type="text" class="form-control form-control-sm"><br> 
					
					Fund name: 
					<input id="fundName" name="fundName" type="text" class="form-control form-control-sm"><br> 
					
					Fund price: 
					<input id="fundPrice" name="fundPrice" type="text" class="form-control form-control-sm"><br> 
					
					Fund description: 
					<input id="fundDesc" name="fundDesc" type="text" class="form-control form-control-sm"><br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success">
				
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				
				</div>
				<div id="alertError"  class="alert alert-danger"></div>
				
				<br>
				
				<%
						fund fundObjRead = new fund();
						out.print(fundObjRead.readFunds());
				%>
				</div>


			</div>
		</div>
	
</body>
</html>