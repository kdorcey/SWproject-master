<%--
  Created by IntelliJ IDEA.
  User: Anne Sun
  Date: 2/24/2018
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
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
    <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
    
    <script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
    
    <title>Login</title>
</head>
<body>
<%
	Cookie[] cookies = null;
	boolean wasLoggedIn = false;
	
	cookies = request.getCookies();
	if ( cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			
			if (cookies[i].getName().equals("sessionID") && !cookies[i].getValue().equals("")) {
				wasLoggedIn = true;
			}
		}
	}
	
	if (wasLoggedIn) { %>
	
	<%@ include file="includes/userNavBar.jsp" %>
    			<div class="container-fluid"> 
    				<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>You are already logged in! Did you land here by mistake!</h2>
            			</div>
            		</div>
		
	<% }
	

	else if (request.getAttribute("successMessage") == null){ %>
    <%@ include file="includes/navBar.jsp" %>
    <div class="container-fluid"> 
    <div class="row-fluid">

     <div class="login">
        <div class="container">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="inner-form">
                    <h2>Login</h2> 
                    <hr>
            			<p class="error">${errorMessage}</p>
                    <form action="login" method="post">
                        <div class="row">      
                            <div class="col-lg-12">
                            	
                            		<span><i class="glyphicon glyphicon-user"></i></span>
                                <label>Username</label>
                                <div class="form-group">
                                		
                                    <input name="username" placeholder="Username" class="form-control" type="text"
                                       autofocus="autofocus">
                                </div>
                            </div>
                            <div class="col-lg-12">
                            	<span><i class="glyphicon glyphicon-lock"></i></span>
                                <label>Password</label>
                                <div class="form-group">
                               		
                                    	<input name="password" placeholder="Password" class="form-control" type="password">
                                </div>
                            </div>	
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-default">
                                    <span>LOGIN</span>
                                </button>
                            </div>
                            <div class="col-lg-12">
                                <div class="forgot">
                                    <p><a href="/forgotPasswordRequest">Forgot Password or Email?</a></p>
                                </div>
                            </div>
                        </div>
                    </form>
                </div> <!-- inner-form -->
            </div>   
        </div>
    </div>
               
</div>
        <% }
    		else { %>
    			<%@ include file="includes/userNavBar.jsp" %>
    			<div class="container-fluid"> 
    				<div class="row-fluid">
        				<div class="col-md-offset-4 col-md-4" id="box">
            				<h2>You've been successfully logged in!</h2>
            			</div>
            		</div>
    		<% }
    		%>
    </div>

</div><!-- close main div -->
<br><br><br><br><br><br>
<%@ include file="includes/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        crossorigin="anonymous"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>