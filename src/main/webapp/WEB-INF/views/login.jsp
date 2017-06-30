<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="login">
	<c:url value="/" var="loginUrl" />
	<form action="${loginUrl}" method="post">
		<label for="username">Nom d'utilisateur :</label>
		<input id="username" name="username">
		<label for="kanbanId">Identifiant du Kanban à visualiser :</label>
		<input type="number" min="0" id="kanbanId" name="kanbanId">
		<button>Valider</button>
	</form>
</div>