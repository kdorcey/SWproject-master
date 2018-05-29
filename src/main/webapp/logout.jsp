<%--
  User: Heather Kemp
  Date: 3/8/2018
  Time: 10:10 AM
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Team12.CS5800.VotingApplication.model.SessionGrabber" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="XA-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="style.css"/>

    <title>Welcome!</title>
</head>

<!-- div to hold all other divs -->

<%@ include file="includes/navBar.jsp" %>
<div class="container-fluid"> 

<% 

	Cookie[] cookies = null;
	boolean wasLoggedIn = false;
	
	cookies = request.getCookies();
	if ( cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			
			if (cookies[i].getName().equals("sessionID") && !cookies[i].getValue().equals("")) {
				SessionGrabber sg = new SessionGrabber();
				sg.removeSession(cookies[i].getValue());
				cookies[i].setValue("");
				cookies[i].setPath("/");
	            cookies[i].setMaxAge(0);
	            response.addCookie(cookies[i]);
				wasLoggedIn = true;
				
			}
		}
	}
	
	if (!wasLoggedIn) { // Not logged in
		System.out.println("Not logged in");
%>
		<div class="row-fluid">
        		<div class="col-md-offset-2 col-md-8" id="box">
            		<h2>Whoops! You weren't logged in! Could you have landed on this page by accident?</h2>
        		</div>
    		</div>

<% } 
	else { %>
		
		<div class="row-fluid">
        		<div class="col-md-offset-2 col-md-8" id="box">
            		<h2>You were successfully logged out.</h2>
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