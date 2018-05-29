<%--
  Created by IntelliJ IDEA.
  User: Anne Sun
  Date: 2/26/2018
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voter Registration</title>
</head>
<body>
<h1>Election System</h1>

<form action="loginRegister" method="post">
    <table style="background-color: gold; margin-left: 20px; margin-left:20px;">
        <tr>
            <td><h3 style="color: indigo;">Please register to vote:</h3></td>
        </tr>
        <tr>
            <td>username:</td>
            <td>${USERNAME}</td>
        </tr>
        <tr>
            <td> Name:</td>
            <td><input type="text" name="name" width="10"/></td>
        </tr>
        <tr>
            <td> SSN:</td>
            <td><input type="text" name="ssn" width="10"/></td>
        </tr>
        <tr>
            <td> Address:</td>
            <td><input type="text" name="address" width="10"/></td>
        </tr>
        <tr>
            <td><input type="submit" name="submit" value="infosubmit"/></td>
        </tr>
    </table>
</form>
<p style="color:red;">${errorMessage}</p>
<p style="color:green;">${successMessage}</p>


</body>
</html>
