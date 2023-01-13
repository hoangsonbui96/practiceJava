<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="<%= response.encodeUrl(request.getContextPath() + "/Controller?action=dologin") %>">
		<input type="hidden" name="action" value="dologin">
		
		<p>Email Adress</p>
		<input type="text" name="email" value="<%= request.getAttribute("email") %>">
		<p>Password</p>
		<input type="password" name="password" value="<%= request.getAttribute("password") %>">
		<input type="submit" value="Log in">
		
		<p class="login-error"><%= request.getAttribute("message") %></p>
	</form>
	<button ></button>
</body>
</html>