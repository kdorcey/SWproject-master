<%--
  User: Heather Kemp
  Date: 3/8/2018
  Time: 10:10 AM
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.SessionGrabber" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.EmailAuthGrabber" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="XA-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="style.css"/>

    <title>Welcome!</title>
</head>

<h1>Election System</h1>

<div class="container-fluid"> <!-- div to hold all other divs -->

<% 

	Cookie[] cookies = null;
	boolean wasLoggedIn = false;
	String sessionCode = "";
	
	cookies = request.getCookies();
	if ( cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			
			if (cookies[i].getName().equals("sessionID") && !cookies[i].getValue().equals("")) {
				SessionGrabber sg = new SessionGrabber();
				
				sessionCode = cookies[i].getValue();
				
			}
		}
	}
	
	if (sessionCode.equals("")){ // Not logged in
		%>
		<%@ include file="includes/navBar.jsp" %>

		<%
	} else {
		SessionGrabber sg = new SessionGrabber();
		String userStatus = sg.checkAdminStatus(sessionCode);

		if (userStatus.equals("admin")){
			%>
			
			<%@ include file="includes/adminNavBar.jsp" %>

			<%
		}
		else if (userStatus.equals("manager")) {
			%>
			
			<%@ include file="includes/managerNavBar.jsp" %>
			
			<%
		}
		else { // user

				%>
				
				<%@ include file="includes/userNavBar.jsp" %>
				
				<%
    			
		}
	}
	

	
	String emailAuthKey = request.getParameter("authKey");
	
	EmailAuthGrabber eag = new EmailAuthGrabber();
	
	boolean emailFound = false;
	
	if(emailAuthKey != ""){
	
	int emailSuccess = eag.updateEmailStatus(emailAuthKey);
	
	if(emailSuccess == 0){ // was not successful
		emailFound = false;
	}
	else {
		emailFound = true;
		eag.removeEmailKey(emailAuthKey);
	}
	}

	
	if (!emailFound) { // Not logged in
%>
		<div class="row-fluid">
        		<div class="col-md-offset-2 col-md-8" id="box">
            		<h2>Whoops! That was an invalid email key! Could you have arrived on this page by accident?</h2>
        		</div>
    		</div>

<% } 
	else { %>
		
		<div class="row-fluid">
        		<div class="col-md-offset-2 col-md-8" id="box">
            		<h2>Your email was approved!</h2>
        		</div>
    		</div>

<% } %>

    
</div><!-- close main div -->
<br><br><br><br><br><br>
<%@ include file="includes/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        crossorigin="anonymous"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>

</html>