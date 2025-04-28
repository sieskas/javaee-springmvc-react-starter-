<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>

<% if (request.getParameter("error") != null) { %>
<div style="color: red;">
    Identifiants invalides, veuillez réessayer.
</div>
<% } %>

<form method="post" action="${pageContext.request.contextPath}/api/v1/login">
    Username: <input type="text" name="username" />
    Password: <input type="password" name="password" />
    <button type="submit">Login</button>
</form>
</body>
</html>
