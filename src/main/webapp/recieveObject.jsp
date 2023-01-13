<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:out value="${user1.name}"/> <br>
	<c:out value="${user2.name}"/> <br>
	<c:out value="${user3.name}"/> <br>	
	<c:out value="${sessionScope.user2.name}"/> <br>
	<c:out value='${ map1["fruit"]}'/> <br>
	
	<c:out value="${link}"/><br>
	${link}<br>
	
	<c:forEach var="animal" items="${list1}">
		${animal.id}: ${animal.name}<br>
	</c:forEach>
	
	
</body>
</html>