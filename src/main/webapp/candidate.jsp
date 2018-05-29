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

    <title>Add a Candidate</title>
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
		if (userStatus.equals("admin")) { %>
<%@ include file="includes/adminSideNav.jsp" %>
<%@ include file="includes/adminNavBar.jsp" %>
<div class="login">
        <div class="container">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="inner-form">
                    <h2>Add a Candidate</h2> 
                    <hr>
               
                <div class="panel-body">
                    <form method="post" action="/candidate">
                    		<div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                    				<div class ="form-group">
                    					<label>candidateName</label>
                    					<input type="text" name="candidateName" id="candidateName" class="form-control input-sm" placeholder="candidateName" autofocus="autofocus" required/>
                    				</div>
                    			</div>
                    			<div class="col-xs-6 col-sm-6 col-md-6">
                    				<div class ="form-group">
                    					<label>electionID</label>
                    					<input type="text" name="electionID" id="electionID" class="form-control input-sm" placeholder="electionID" required/>
                    				</div>
                    			</div>
                    		</div>
                    		
                    		<div class = "form-group">
                    			<label>Party</label>
                    			<input type="text" name="party" id="party" class="form-control input-sm" placeholder="party" required/>
                    		</div>                			                
                    		
                        <input type="submit" name ="submit" value="Add" class="btn btn-success btn-block">
                        
                    </form>
                    </div>
                </div>
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