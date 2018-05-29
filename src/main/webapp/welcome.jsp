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
    <link href="css/font-awesome.css" rel="stylesheet" type="text/css">
    
    <script src="https://use.fontawesome.com/07b0ce5d10.js"></script>
    

    <title>Welcome!</title>
</head>

<!-- div to hold all other divs -->

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
			<div class="row">
				<br/>
                <div class="col-lg-2 col-sm-6">
                    <div class="circle-tile">
                        <a href="#">
                            <div class="circle-tile-heading dark-blue">
                                <i class="fa fa-users fa-fw fa-3x"></i>
                            </div>
                        </a>
                        <div class="circle-tile-content dark-blue">
                            <div class="circle-tile-description text-faded">
                                Users
                            </div>
                            <div class="circle-tile-number text-faded">
                                
                                <span id="sparklineA"></span>
                            </div>
                            <a href="/voterApproval" class="circle-tile-footer">Approve Voters <i class="fa fa-chevron-circle-right"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-sm-6">
                    <div class="circle-tile">
                        <a href="#">
                            <div class="circle-tile-heading green">
                                <i class="fa fa-bar-chart fa-fw fa-3x"></i>
                            </div>
                        </a>
                        <div class="circle-tile-content green">
                            <div class="circle-tile-description text-faded">
                                Analytics
                            </div>
                            <div class="circle-tile-number text-faded">
                                
                            </div>
                            <a href="/votes" class="circle-tile-footer">View Results <i class="fa fa-chevron-circle-right"></i></a>
                        </div>
                    </div>
                </div>

                <div class="col-lg-2 col-sm-6">
                    <div class="circle-tile">
                        <a href="#">
                            <div class="circle-tile-heading blue">
                                <i class="fa fa-check-square-o fa-fw fa-3x"></i>
                            </div>
                        </a>
                        <div class="circle-tile-content blue">
                            <div class="circle-tile-description text-faded">
                                Polls
                            </div>
                            <div class="circle-tile-number text-faded">
                                
                                <span id="sparklineB"></span>
                            </div>
                            <a href="/poll" class="circle-tile-footer">Add Poll <i class="fa fa-chevron-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    			
			
			<%
		}
		else if (userStatus.equals("manager")) {
			%>
			
			 <%@ include file="includes/managerSideNav.jsp" %> 
			 
			 <%@ include file="includes/managerNavBar.jsp" %> 
			 
			 <%@ include file="managerWelcome.jsp" %>
    				
			
			<!--  <div class="container-fluid"> 
    			<div class="row-fluid">
        			<div class="col-md-offset-2 col-md-8" id="box">
            			<h2>Welcome, ${firstName}! </h2>
        			</div>
    			</div>
    			-->
			
			<%
		}
		else { // user
			int voterStatus = sg.getVoterStatus(sessionCode);

			if (voterStatus == 0) { // Not applied
				
				EmailAuthGrabber eag = new EmailAuthGrabber();
				System.out.println(sg.getUsername(sessionCode));
				if (eag.checkValidatedEmail(sessionCode) == 0){
					
				%>
				
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
				
				<%@ include file="includes/userNavBar.jsp" %>
				<div class="container-fluid"> 
    				<div class="row-fluid">
        				<div class="col-md-offset-2 col-md-8" id="box">
            				<h2>Welcome, ${firstName}! </h2>
        				</div>
    				</div>
				
				<%
				
			}
		}
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