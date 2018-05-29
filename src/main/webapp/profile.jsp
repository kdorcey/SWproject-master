<%--
  Created by IntelliJ IDEA.
  User: Anne Sun
  Date: 2/24/2018
  Time: 5:12 PM
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

    <title>Profile</title>
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
		pageContext.setAttribute("firstName", firstName);
		String lastName = sg.getLastName(sessionCode);
		String city = sg.getCity(sessionCode);
		String address = sg.getAddress(sessionCode);
		String zipcode = sg.getZip(sessionCode);
		String ssn = sg.getSSN(sessionCode);
		String username = sg.getUsername(sessionCode);
		String email = sg.getEmail(sessionCode);
		String gender = sg.getGender(sessionCode);
		int age = sg.getAge(sessionCode);
		String education = sg.getEducation(sessionCode);
		String state = sg.getState(sessionCode);
		String securityQuestion1 = sg.getSecurityQuestion1(sessionCode);
		String securityQuestion2 = sg.getSecurityQuestion2(sessionCode);
		String securityAnswer1 = sg.getSecurityAnswer1(sessionCode);
		String securityAnswer2 = sg.getSecurityAnswer2(sessionCode);
		
		
		if (userStatus.equals("admin")){
			%>
			
			<%@ include file="includes/adminSideNav.jsp" %>
			<%@ include file="includes/adminNavBar.jsp" %>
			
			<%
		}
		else if (userStatus.equals("manager")) {
			%>
			
			 <%@ include file="includes/managerSideNav.jsp" %> 
			 
			 <%@ include file="includes/managerNavBar.jsp" %> 
			
			<%
		}
		else { 
				%>
				
				<%@ include file="includes/userNavBar.jsp" %>
				
				<%

		
		}
%>
    <div class="container-fluid">

     <div class="login">
        <div class="container">
            <div class="col-lg-6 col-lg-offset-1">
                <div class="inner-form">
                    <h2>Update User Information</h2> 
                    <hr>
                <p style="color:red;">${errorMessage}</p>
                <p style="color:green;">${successMessage}</p>
                <div class="panel-body">
                    <form method="post">
                    		<div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                    				<div class ="form-group">
                    					<label>First Name</label>
                    					<input type="text" name="first_name" id="first_name" class="form-control input-sm" value="<%=firstName %>" autofocus="autofocus" required/>
                    				</div>
                    			</div>
                    			<div class="col-xs-6 col-sm-6 col-md-6">
                    				<div class ="form-group">
                    					<label>Last Name</label>
                    					<input type="text" name="last_name" id="last_name" class="form-control input-sm" value="<%=lastName%>" required/>
                    				</div>
                    			</div>
                    		</div>
                    		
                    		<div class = "form-group">
                    			<label>Address</label>
                    			<input type="text" name="address" id="address" class="form-control input-sm" value="<%=address %>" required/>
                    		</div>
                    		<div class = "form-group">
                    			<label>City</label>
                    			<input type="text" name="city" id="city" class="form-control input-sm" value="<%=city %>" required/>
                    		</div>
                    		<div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
		                    		<div class = "form-group">
		                    			<label>State</label>
		                    			<select class="form-control" id="state" name="state" required>
										<option value="AK">Alaska</option>
										<option value="AL">Alabama</option>
										<option value="AR">Arkansas</option>
										<option value="AZ">Arizona</option>
										<option value="CA">California</option>
										<option value="CO">Colorado</option>
										<option value="CT">Connecticut</option>
										<option value="DC">District of Columbia</option>
										<option value="DE">Delaware</option>
										<option value="FL">Florida</option>
										<option value="GA">Georgia</option>
										<option value="HI">Hawaii</option>
										<option value="IA">Iowa</option>
										<option value="ID">Idaho</option>
										<option value="IL">Illinois</option>
										<option value="IN">Indiana</option>
										<option value="KS">Kansas</option>
										<option value="KY">Kentucky</option>
										<option value="LA">Louisiana</option>
										<option value="MA">Massachusetts</option>
										<option value="MD">Maryland</option>
										<option value="ME">Maine</option>
										<option value="MI">Michigan</option>
										<option value="MN">Minnesota</option>
										<option value="MO">Missouri</option>
										<option value="MS">Mississippi</option>
										<option value="MT">Montana</option>
										<option value="NC">North Carolina</option>
										<option value="ND">North Dakota</option>
										<option value="NE">Nebraska</option>
										<option value="NH">New Hampshire</option>
										<option value="NJ">New Jersey</option>
										<option value="NM">New Mexico</option>
										<option value="NV">Nevada</option>
										<option value="NY">New York</option>
										<option value="OH">Ohio</option>
										<option value="OK">Oklahoma</option>
										<option value="OR">Oregon</option>
										<option value="PA">Pennsylvania</option>
										<option value="PR">Puerto Rico</option>
										<option value="RI">Rhode Island</option>
										<option value="SC">South Carolina</option>
										<option value="SD">South Dakota</option>
										<option value="TN">Tennessee</option>
										<option value="TX">Texas</option>
										<option value="UT">Utah</option>
										<option value="VA">Virginia</option>
										<option value="VT">Vermont</option>
										<option value="WA">Washington</option>
										<option value="WI">Wisconsin</option>
										<option value="WV">West Virginia</option>
										<option value="WY">Wyoming</option>
									</select>
		                    		</div>
		                    	</div>
		                    	<div class="col-xs-6 col-sm-6 col-md-6">
		                    		<div class = "form-group">
		                    			<label>Zip Code</label>
                    					<input type="text" name="zipcode" id="zipcode" class="form-control input-sm" value="<%=zipcode %>" required/>
                    				</div>
		                    	</div>
		                </div>
                    		<!--  city, state, zip -->
                    		<div class = "form-group">
                    			<label>Social Security Number</label>
                    			<input type="text" name="ssn" id="ssn" class="form-control input-sm" value="<%=ssn %>" required/>
                    		</div>
                        <div class="form-group">
                        		<label>Email</label>
                            <input type="email" name="email" id="email" class="form-control input-sm"
                                   value="<%=email %>" readonly required>
                        </div>
                        
                        <div class="form-group">
                        		<label>Username</label>
                            <input type="text" name="username" id="username" class="form-control input-sm"
                                   value="<%=username %>" readonly required>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                		<label>Current Password</label>
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder="Password" required>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                		<label>New Password</label>
                                    <input type="password" name="newpassword" id="newpassword" class="form-control input-sm"
                                           placeholder="New Password" >
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group">
                        		<label>Gender</label>
                            <input type="text" name="gender" id="gender" class="form-control input-sm"
                                   value="<%=gender %>" required>
                        </div>
                        <div class="form-group">
                        		<label>Age</label>
                            <input type="text" name="age" id="age" class="form-control input-sm"
                                   value="<%=age %>" required>
                        </div>
                        <div class="form-group">
                        		<label>Education</label>
                            <input type="text" name="education" id="education" class="form-control input-sm"
                                   value="<%=education %>" required>
                        </div>
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
		                    		<div class = "form-group">
		                    			<label>Security Question 1</label>
		                    			<select class="form-control" id="securityQuestion1" name="securityQuestion1" required>
										<option>What was your childhood nickname?</option>
										<option>What is the name of your favorite childhood friend?</option>
										<option>In what city or town did your mother and father meet?</option>
										<option>What is the middle name of your oldest child?</option>
										<option>What is your favorite sports team?</option>
										<option>What is your favorite movie?</option>
										<option">What was the color of your first car?</option>
										<option>What was the name of the hospital where you were born?</option>
										<option>What was the name of the company where you had your first job?</option>
										<option>What school did you attend for sixth grade?</option>
										<option>What is the name of the first person you kissed?</option>
									</select>
		                    		</div>
		                    	</div>
		                    	<div class="col-xs-6 col-sm-6 col-md-6">
		                    		<div class = "form-group">
		                    			<label>Security Question 1 Answer</label>
		                    			<input type="text" name="securityQuestion1Answer" id="securityQuestion1Answer" class="form-control input-sm" value="<%=securityAnswer1 %>" required/>
		                    		</div>
		                    	</div>
				           </div>
				           <div class="row">
		                            <div class="col-xs-6 col-sm-6 col-md-6">
				                    		<div class = "form-group">
				                    			<label>Security Question 2</label>
				                    			<select class="form-control" id="securityQuestion2" name="securityQuestion2" required>
												<option>What was your childhood nickname?</option>
												<option>What is the name of your favorite childhood friend?</option>
												<option>In what city or town did your mother and father meet?</option>
												<option>What is the middle name of your oldest child?</option>
												<option>What is your favorite sports team?</option>
												<option>What is your favorite movie?</option>
												<option>What was the color of your first car?</option>
												<option>What was the name of the hospital where you were born?</option>
												<option>What was the name of the company where you had your first job?</option>
												<option>What school did you attend for sixth grade?</option>
												<option>What is the name of the first person you kissed?</option>
											</select>
				                    		</div>
				                    	</div>
				                    	<div class="col-xs-6 col-sm-6 col-md-6">
				                    		<div class = "form-group">
				                    			<label>Security Question 2 Answer</label>
				                    			<input type="text" name="securityQuestion2Answer" id="securityQuestion2Answer" class="form-control input-sm" value="<%=securityAnswer2 %>" required/>
				                    		</div>
				                    	</div>
				           </div>
                        
                        <input type="submit" name ="submit" value="Update" class="btn btn-success btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
    
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>

	<script>
	$(state).val("<%=state%>");
	$(securityQuestion1).val("<%=securityQuestion1%>");
	$(securityQuestion2).val("<%=securityQuestion2%>");
	</script>
<% 
}
%>
<%@ include file="includes/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        crossorigin="anonymous"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
