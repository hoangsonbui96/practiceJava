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
	<c:out value="Hello ACE"></c:out>
	<jsp:useBean id="test" class="beans.TestBean" scope="page"></jsp:useBean>
	
	<p>Value of Info "attribute":<c:out value="${test.info}"/></p>
	
	Name Parameter: <c:out value="${param.name}"></c:out><br><br>
	
	<%-- lệnh if else trong jstl --%>
	
	<c:if test='${param.name == "Bob" }'>
		Hello Bob
	</c:if>
	
	<c:if test='${param.name != "Bob" }'>
		Hello There
	</c:if>
	
	<%-- lệnh choose (giống case) --%>
	
	<br><br>
	<c:choose>
		<c:when test='${ param.id == 1 }'>
			<b>ID is equal 1</b>
		</c:when>
		
		<c:when test='${ param.id == 2 }'>
			<b>ID is equal 2</b>
		</c:when>
		
		<c:otherwise>
			<b>ID is equal other number</b>
		</c:otherwise>
	</c:choose>
	
	<%-- lệnh for trong jstl --%>
	
	<br><br>
	
	<c:forEach var="i" begin="0" end="10" step="2">
		Loop counter is: <c:out value="${i}"/><br>
	</c:forEach>
	
	
	
	
</body>
</html>