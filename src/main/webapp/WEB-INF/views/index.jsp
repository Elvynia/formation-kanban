<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Application Kanban</title>
	</head>
	<body>
		<h1>POC Kanban</h1>
		<ul>
			<c:forEach items="${tasks}" var="task">
				<li><c:out value="${task.title}" />
			</c:forEach>
		</ul>
	</body>
</html>