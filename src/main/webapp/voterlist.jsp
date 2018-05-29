<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.SessionGrabber" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.EmailAuthGrabber" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="XA-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
    
    <script src="https://use.fontawesome.com/07b0ce5d10.js"></script>

    <title>Voterlist</title>
</head>
<body>

<% 


String sessionCode = ""; 
	Cookie[] cookies = null;
	
	cookies = request.getCookies();
	if ( cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			
			if (cookies[i].getName().equals("sessionID")) {
				sessionCode = cookies[i].getValue();
				
			}
		}
	}
	
	if (sessionCode.equals("")){ // Not logged in
		%>
		<%@ include file="includes/navBar.jsp" %>
		<div class="container-fluid"> 
    		<div class="row-fluid">
        		<div class="col-md-offset-2 col-md-8" id="box">
            		<h2>Welcome to our voting service! Please register or login!</h2>
        		</div>
    		</div>

		<%
	} else {
		SessionGrabber sg = new SessionGrabber();
		String userStatus = sg.checkAdminStatus(sessionCode);
		String firstName = sg.getFirstName(sessionCode);
		pageContext.setAttribute("firstName", firstName);
		if (userStatus.equals("admin")){
			%>
			<%@ include file="includes/adminSideNav.jsp" %>
			<%@ include file="includes/adminNavBar.jsp" %>
		<% } 
		else if(userStatus.equals("manager")){ %>
			<%@ include file="includes/managerSideNav.jsp" %> 
			<%@ include file="includes/managerNavBar.jsp" %> 
			<% } %>
	
	<div class="container-fluid"> 
		<sql:setDataSource var = "data" driver = "com.mysql.jdbc.Driver"
	         url = "jdbc:mysql://bais.mysql.database.azure.com/db"
	         user = "voterapp@bais"  password = "P@$$w0rD"/>
		<sql:query dataSource = "${data}" var = "result">
	         select distinct precinct from user_info where voter_status = 2 order by precinct;
		</sql:query>
		
		<div class="panel panel-defafult panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h1 class="panel-title">Voters</h1>
					</div>
					<div class="col col-xs-6 text-right">
						<form method="post" action="/voterlist">
							Search by Precinct: <select name="choice">
								<c:forEach var = "row" items = "${result.rows}">
	    							<option><c:out value = "${row.precinct}"/></option>
								</c:forEach>
							</select>
							<input type="submit" value="Select" />
						</form>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<table class="table table-striped table-bordered table-list">
					<thead>
						<tr>
							<th class="hidden-xs">UserId</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>SSN</th>
							<th>Address</th>
							<th>City</th>
							<th>State</th>
							<th>Zipcode</th>
							<th>Precinct</th>
							<th>Gender</th>
							<th>Age</th>
							<th>Education</th>	
						</tr>
					</thead>
					<tbody>
						<sql:query dataSource = "${data}" var = "result">
				        	select id, first_name,last_name, ssn, address, city, 
				         	state, zipcode, precinct, gender, age, education from user_info where voter_status = 2 and precinct ="${param.choice}" order by id;
				    	</sql:query>
						<c:forEach var = "row" items = "${result.rows}">
							<tr>
								<td class="hidden-xs"> <c:out value = "${row.id}"/> </td>
								<td> <c:out value = "${row.first_name}"/> </td>
								<td> <c:out value = "${row.last_Name}"/> </td>
								<td> <c:out value = "${row.ssn}"/> </td>
								<td> <c:out value = "${row.address}"/> </td>
								<td> <c:out value = "${row.city}"/> </td>
								<td> <c:out value = "${row.state}"/> </td>
								<td> <c:out value = "${row.zipcode}"/> </td>
								<td> <c:out value = "${row.precinct}"/> </td>
								<td> <c:out value = "${row.gender}"/> </td>
								<td> <c:out value = "${row.age}"/> </td>
								<td> <c:out value = "${row.education}"/> </td>
							</tr>
						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
		</div>

	<% }  %>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>