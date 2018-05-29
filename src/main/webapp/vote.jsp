<%--
  Created by IntelliJ IDEA.
  User: Anne Sun
  Date: 2/24/2018
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
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

    <title>Registration</title>
</head>
<body>

<%

Cookie[] cookies = null;
boolean wasLoggedIn = false;
String sessionCode = "";

cookies = request.getCookies();
if ( cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		
		if (cookies[i].getName().equals("sessionID") && !cookies[i].getValue().equals("")) {
			wasLoggedIn = true;
			sessionCode = cookies[i].getValue();
		}
	}
}

if (!wasLoggedIn) { %>

<%@ include file="includes/navBar.jsp" %>
			<div class="container-fluid"> 
				<div class="row-fluid">
    				<div class="col-md-offset-4 col-md-4" id="box">
        				<h2>Did you land here by mistake? You aren't logged in.</h2>
        			</div>
        			</div>
        		</div>
	
<% }


else {

SessionGrabber sg = new SessionGrabber();
		String userStatus = sg.checkAdminStatus(sessionCode);
		String firstName = sg.getFirstName(sessionCode);
		String lastName = sg.getLastName(sessionCode);
		String precinct = sg.getPrecinct(sessionCode);
		pageContext.setAttribute("firstName", firstName);
		int id = sg.getID(sessionCode);

		
		if (userStatus.equals("admin")){
			%>
			
			<%@ include file="includes/adminSideNav.jsp" %>
			<%@ include file="includes/adminNavBar.jsp" %>
			<div class="container-fluid"> 
			<%
		}
		else if (userStatus.equals("manager")) {
			%>
		
			 <%@ include file="includes/managerSideNav.jsp" %> 
			 
			 <%@ include file="includes/managerNavBar.jsp" %> 
			<div class="container-fluid"> 
			<%
		}
		else { // user
			int voterStatus = sg.getVoterStatus(sessionCode);

			if (voterStatus == 0) { // Not applied
				
				EmailAuthGrabber eag = new EmailAuthGrabber();
				System.out.println(sg.getUsername(sessionCode));
				if (eag.checkValidatedEmail(sessionCode) == 0){
					
				%>
				<div class="container-fluid"> 
				<%@ include file="includes/userNavBar.jsp" %>
    				<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>Welcome, ${firstName}! Make sure to verify your email! You can't become a voter without doing so!</h2>
        				</div>
    				</div>
				
				<%
				}
				else {
					%>
					<div class="container-fluid"> 
					<%@ include file="includes/userNavBar.jsp" %>
    					<div class="row-fluid">
        					<div class="col-md-offset-2 col-md-8" id="box">
            					<h2>Welcome, ${firstName}! Make sure you apply to become a voter before the next election cycle! To apply, <a href="/voterApply">click here!</a></h2>
        					</div>
    					</div>
					
					
					<%
				}
			}
			else if (voterStatus == 1) { // Applied but not yet approved
				%>
				<div class="container-fluid"> 
				<%@ include file="includes/userNavBar.jsp" %>
		
				<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>Welcome, ${firstName}! An administrator is reviewing your application now! Please check in again later! </h2>
        				</div>
    				</div>
				
				<%
				
			}
			else { // Registered and was approved
				%>
				<div class="container-fluid"> 
				<%@ include file="includes/userNavBar.jsp" %>

     <div class="login">
        <div class="container">
            <div class="col-lg-6 col-lg-offset-1">
                <div class="inner-form">
                    <h2>Candidate List</h2> 
                    <hr>
                <p style="color:red;">${errorMessage}</p>
                <p style="color:green;">${successMessage}</p>
                <div class="panel-body">
                    <form method="post">
                    	
                    		<input type="hidden" id="userID" name="userID" value="<%=id%>">
                    		<input type="hidden" id="precinct" name="precinct" value="<%=precinct%>">
                    		<div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
		                    		<div class = "form-group">
		                    			 <input type="radio" name="candidateID" value="1" >Hilary Clinton 
		                    			 </div>   
		                    			 <div class = "form-group">         
    									<input type="radio" name="candidateID" value=2 >Donald Trump
    									</div>  
    									<div class = "form-group">
    									<input type="radio" name="candidateID" value=3 >Bernie Sanders
    									
		                    		</div>
		                    	</div>
		                    	
		                </div>
                  
                        <input type="submit" name ="submit" value="Vote" class="btn btn-success btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
    
</div>
</div>
<% 
}}}
%>
<%@ include file="includes/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        crossorigin="anonymous"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
