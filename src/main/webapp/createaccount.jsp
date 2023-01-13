<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Create New Account</h1>
	<form method="post" action="<%= response.encodeUrl(request.getContextPath() + "/Controller?action=doregister") %>">
		<input type="hidden" name="action" value="doregister">
		
		<p>Email Adress</p>
		<input type="email" name="email" value="<%= request.getAttribute("email") %>">
		<p>Password</p>
		<input type="password" name="password" value="<%= request.getAttribute("password") %>">
		<p>Repeat Password</p>
		<input type="password" name="repeatpassword" value="<%= request.getAttribute("repeatpassword") %>">
		
		<input type="submit" value="Create">
		
		<p class="login-error"><%= request.getAttribute("message") %></p>
	</form>
</body>
</html>