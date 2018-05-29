<%--
  Created by IntelliJ IDEA.
  User: Anne Sun
  Date: 3/3/2018
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="XA-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="style.css" />

    <title>welcome</title>
</head>
<body>
<div class="container-fluid"> <!-- div to hold all other divs -->
    <%@ include file="includes/userNavBar.jsp" %>
    <div class="main">
        <h1>welcome</h1>
        <p>Dear: ${message}</p>
    </div>
    <%@ include file="includes/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</div>
</body>
</html>
