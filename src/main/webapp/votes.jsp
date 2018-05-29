<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.SessionGrabber" %>
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

    <title>Paper trail</title>
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
		if (userStatus.equals("manager")) { %>
	<%@ include file="includes/managerSideNav.jsp" %>
	<%@ include file="includes/managerNavBar.jsp" %>
	
	<div class="container-fluid"> <!-- div to hold all other divs -->
	
		<sql:setDataSource var = "data" driver = "com.mysql.jdbc.Driver"
		         url = "jdbc:mysql://bais.mysql.database.azure.com/db"
		         user = "voterapp@bais"  password = "P@$$w0rD"/>
		<sql:query dataSource = "${data}" var = "result">
		         select distinct candidateID from paper_trail order by candidateID;
		</sql:query>
		
		
		<div class="panel panel-defafult panel-table">
			<div class="panel-heading">
				<div class="row">
					<div class="col col-xs-6">
						<h1 class="panel-title">Paper Trail</h1>
					</div>
					<div class="col col-xs-6 text-right">
						<form method="post" action="/votes">
							Search by Candidate: <select name="choice">
								<c:forEach var = "row" items = "${result.rows}">
								    <option><c:out value = "${row.candidateID}"/></option>
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
							<th>paperTrailID</th>
							<th>electionID</th>
							<th>voterID</th>
							<th>Precinct</th>
							<th>candidateName</th>		
						</tr>
					</thead>
					<tbody>
						<sql:query dataSource = "${data}" var = "result">
				        	select paperTrailID, electionID, voterID, precinct, candidateID from paper_trail where candidateID ="${param.choice}" order by paperTrailID;
				    	</sql:query>
				    	
				    	<c:forEach var = "row" items = "${result.rows}">
							<tr>
								<td> <c:out value = "${row.paperTrailID}"/> </td>
								<td> <c:out value = "${row.electionID}"/> </td>
								<td> <c:out value = "${row.voterID}"/> </td>
								<td> <c:out value = "${row.precinct}"/> </td>
								<sql:query dataSource = "${data}" var = "result1">
				        			select candidateName from candidate_info where candidateID ="${row.candidateID}";
				    			</sql:query>
				    			<c:forEach var = "row1" items = "${result1.rows}">
				    				<td> <c:out value = "${row1.candidateName}"/> </td>
				    			</c:forEach>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div><%} }%>
<br><br><br><br><br><br>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        crossorigin="anonymous"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>