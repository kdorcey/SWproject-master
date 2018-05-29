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

    <title>Add a Poll</title>
</head>
<body>
<%@ include file="includes/adminSideNav.jsp" %>
<%@ include file="includes/adminNavBar.jsp" %>
<div class="login">
        <div class="container">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="inner-form">
                    <h2>Add a Poll</h2> 
                    <hr>
               
                <div class="panel-body">
                    <form method="post" action="/poll">
                    		<div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                    				<div class ="form-group">
                    					<label>electionName</label>
                    					<input type="text" name="electionName" id="electionName" class="form-control input-sm" placeholder="electionName" autofocus="autofocus" required/>
                    				</div>
                    			</div>
                    			<div class="col-xs-6 col-sm-6 col-md-6">
                    				<div class ="form-group">
                    					<label>dateStarted</label>
                    					<input type="text" name="dateStarted" id="dateStarted" class="form-control input-sm" placeholder="dateStarted" required/>
                    				</div>
                    			</div>
                    		</div>
                    		
                    		<div class = "form-group">
                    			<label>dateEnded</label>
                    			<input type="text" name="dateEnded" id="dateEnded" class="form-control input-sm" placeholder="dateEnded" required/>
                    		</div>
                    		<div class = "form-group">
                    			<label>number of candidates</label>
                    			<input type="text" name="candidateCount" id="candidateCount" class="form-control input-sm" placeholder="candidateCount" required/>
                    		</div>
                    		<div class = "form-group">
                    			<label>onGoing</label>
                    			<input type="text" name="onGoing" id="onGoing" class="form-control input-sm" placeholder="onGoing" required/>
                    		</div>
                    		
		                
                    		
                        <input type="submit" name ="submit" value="Create" class="btn btn-success btn-block">
                        
                    </form>
                    </div>
                </div>
            </div>
        </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/bootstrap.js"></script>
    </div>

</body>
</html>