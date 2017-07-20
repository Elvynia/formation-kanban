<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<title>Application Kanban</title>
<script src="<c:url value="/webjars/angular/1.6.5/angular.min.js" />"></script>
<script
	src="<c:url value="/webjars/angular-resource/1.6.5/angular-resource.min.js" />"></script>
<script
	src="<c:url value="/webjars/momentjs/2.18.1/min/moment.min.js" />"></script>
<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js" />"></script>
<script
	src="<c:url value="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/app.js" />"></script>

<link
	href="<c:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />"
	rel="stylesheet">
</head>
<body ng-app="kanban">
	<h1>Application Kanban</h1>
	<div ng-controller="MainController as main">
		<form ng-submit="main.showKanban()" class="form-inline">
			<div class="form-group">
				<label for="username">Saisir un nom :</label> <input id="username"
					ng-model="main.username" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="idKanban">Identifiant du kanban :</label>
				<select id="idKanban" ng-model="main.idKanban"
					class="form-control" required>
					<option value="" disabled selected hidden>Sélectionner...</option>
					<option ng-repeat="(id, openedOn) in main.kanbanList" ng-value="id">{{
						openedOn | date }}</option>
				</select>
			</div>
			<button>Valider</button>
		</form>
	</div>
	<div ng-controller="KanbanController as kanban" ng-show="kanban.ready">
		<h2>Kanban démarré le {{kanban.instance.openedOn | date}}</h2>
		<div style="width: 300px; border: 1px solid black; float: left"
			ng-repeat="category in kanban.categories">
			<h3>{{category.name}}</h3>

		</div>
		<div style="width: 300px; border: 1px solid black; float: left">
			<form ng-submit="kanban.addCategory()">
				<div class="form-group">
					<label for="category_name">Nom :</label>
					<input id="category_name" class="form-control"
						ng-model="kanban.newCategoryName" required>
				</div>
				<div class="form-group">
					<label for="category_order">Valeur d'ordre :</label>
					<input id="category_order" class="form-control"
						ng-model="kanban.newOrder" type="number" required>
				</div>
			<button>Ajouter</button>
			</form>
		</div>
	</div>
</body>
</html>