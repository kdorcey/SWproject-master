<%--
  Created by IntelliJ IDEA.
  User: Anne Sun
  Date: 3/2/2018
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>manager page</title>
</head>
<body>
<h1>manager page</h1>
<p>Hello, Manager</p>
<p>username: <%= session.getAttribute("USERNAME") %></p>
<form action="loginRegister" method="post">
    <p><input type="submit" name="submit" value="logout"/></p>
</form>
</body>
</html>
