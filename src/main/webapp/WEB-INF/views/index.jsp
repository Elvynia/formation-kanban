<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<title>Application Kanban</title>
<c:url value="/webjars/angularjs/1.6.4" var="angularUrl" />
<script src="${angularUrl}/angular.min.js"></script>
<script src="${angularUrl}/angular-resource.min.js"></script>
<script src="${angularUrl}/angular-animate.min.js"></script>
<script src="${angularUrl}/angular-aria.min.js"></script>
<script src="${angularUrl}/angular-messages.min.js"></script>
<script
	src="<c:url value="/webjars/angular-material/1.1.4/angular-material.min.js" />"></script>
<script
	src="<c:url value="/webjars/momentjs/2.18.1/min/moment.min.js" />"></script>
<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js" />"></script>
<script
	src="<c:url value="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/app.js" />"></script>

<link
	href="<c:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/css/application.css" />"
	rel="stylesheet">
<link href="<c:url value="/webjars/angular-material/1.1.4/angular-material.css" />"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
</head>
<body ng-app="kanban">
	<div id="login" ng-controller="MainController as main">
		<h1>Application Kanban</h1>
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
			<button class="btn btn-primary">Valider</button>
		</form>
	</div>
	<div id="kanban" ng-controller="KanbanController as kanban" ng-show="kanban.ready">
		<h2>Kanban démarré le {{kanban.instance.openedOn | date}}</h2>
		<div>
			<div class="category" ng-repeat="category in kanban.categories">
				<div class="edit-icon" ng-controller="EditController as edit"
					ng-init="edit.init(category, kanban.instance.id)">
					<md-icon md-font-set="material-icons"
						ng-click="edit.showCategoryEdit($event)">mode_edit</md-icon>
                    <md-icon md-font-set="material-icons"
						ng-click="edit.categoryDelete($event, $index)">delete</md-icon>
				</div>
				<h2>{{category.name}}</h2>
				<div class="task" ng-repeat="task in category.tasks">
					<h3>{{task.title}}</h3>
				</div>
			</div>
			<div class="category">
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
				<button class="btn btn-primary">Ajouter</button>
				</form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>