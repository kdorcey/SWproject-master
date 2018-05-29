<%--
  User: Heather Kemp
  Date: 3/8/2018
  Time: 10:10 AM
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.SessionGrabber" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.EmailAuthGrabber" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.UserDAOImpl" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.User" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.VoterStatus" %>
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
	
	int voterStatus = 2;
	
	if (sessionCode.equals("")){ // Not logged in
		%>
		<%@ include file="includes/navBar.jsp" %>

		<%
	} else {
		SessionGrabber sg = new SessionGrabber();
		String userStatus = sg.checkAdminStatus(sessionCode);
		voterStatus = sg.getVoterStatus(sessionCode);

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
	
	
	// if it's 0, convert update user object to 1, say HEY WE UPDATED
	// if it's 1 You've already applied, please wait while an admin reviews your information
	// if it's 2 You're already approved, did you land here by mistake?
	// sessionCode.equals("") -- you can't apply if you aren't logged in! Please login or register now!
	
	if (sessionCode.equals("")){ // you can't apply if you aren't logged in! Please login or register now!
			%>
			
			<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>You can't apply to be a voter if you aren't registered! Please login or register now!</h2>
        				</div>
    			</div>

			<%
		}
		else if (voterStatus == 0) { // convert update user object to 1, say HEY WE UPDATED
			
			EmailAuthGrabber eag = new EmailAuthGrabber();
			if (eag.checkValidatedEmail(sessionCode) == 0)
			
				{ %>
			
				<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>You can't apply to become a voter without validating your email first!</h2>
        				</div>
    			</div>
			
				<%
				}
			else {
			UserDAOImpl UDAO = new UserDAOImpl();
			User thisUser = UDAO.getUser(sessionCode);
			thisUser.setVoterStatus(VoterStatus.APPLIED);
			
			%>
			
			<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>Thanks for applying! An admin will review your information soon!</h2>
        				</div>
    			</div>
			
			<%
			}
		} 
		else if (voterStatus == 1) { // You've already applied, please wait while an admin reviews your information
			%>
			
			<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>You've already applied to become a voter! Please wait while an admin reviews your information!</h2>
        				</div>
    			</div>
			
			<%
		}
		else { // 2, You're already approved, did you land here by mistake?

				%>
				
				<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>You're already a voter! Did you perhaps land on this page by accident?</h2>
        				</div>
    				</div>
				
				<%
    			
		}

	%>

    
</div><!-- close main div -->
<br><br><br><br><br><br>
<%@ include file="includes/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        crossorigin="anonymous"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>

</html>