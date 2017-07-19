var app = angular.module('kanban', ['ngResource']);

app.constant('API_URL', '${api.url}');
//app.value('API_URL', '${api.url}');

var mainController =  function($rootScope) {
	this.rootScope = $rootScope;
	this.username = 'John Smith';
	this.idKanban = 1;
};


mainController.prototype.showKanban = function() {
	this.rootScope.$broadcast('showKanban', {
		id: this.idKanban
	});
};

var kanbanController = function($scope, KanbanFactory) {
	var vm = this;
	$scope.$on('showKanban', function(event, data) {
		vm.openedOn = '2017-01-01';
	})
};

app.factory('KanbanFactory', function($resource, API_URL) {
	return $resource(API_URL + '/kanban');
});

app.controller('KanbanController', kanbanController);
app.controller('MainController', mainController);