<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="header.jsp" />
</head>
<body>
	<h2>Détails de la tâche :</h2>
	<div class="taskDetails">
		<div>Titre : ${task.title}</div>
		<div>Description : ${task.description}</div>
		<div>Points : ${task.points}</div>
		<div>Créée le : ${task.createdOn}</div>
		<div>Modifiée le : ${task.lastModifiedOn}</div>
	</div>
</body>
</html>