var app = angular.module('kanban', ['ngResource']);

app.constant('API_URL', '${api.url}');
//app.value('API_URL', '${api.url}');

var mainController =  function($rootScope, DataFactory) {
	this.rootScope = $rootScope;
	this.username = 'John Smith';
	this.idKanban = null;
	DataFactory.query((data) =>	this.kanbanList = data);
};

mainController.prototype.showKanban = function() {
	this.rootScope.$broadcast('showKanban', {
		id: this.idKanban
	});
};

var kanbanController = function($scope, KanbanFactory, CategoryFactory) {
	var vm = this;
	vm.ready = false;
	$scope.$on('showKanban', function(event, data) {
		KanbanFactory.get(data, (kanban) => {
			vm.instance = kanban;
			CategoryFactory.query({ kanbanId: kanban.id },
				(categories) => {
					vm.categories = categories;
					vm.ready = true;
				});
		});
	});
	vm.addCategory = function() {
		CategoryFactory.add({
			name: vm.newCategoryName,
			order: vm.newOrder,
			kanban: {
				id: vm.instance.id
			}
		}, (category) => {
			vm.categories.push(category);
			vm.newCategoryName = null;
			vm.newOrder = null;
		});
	};
};

app.factory('DataFactory', function($resource, API_URL) {
	return $resource(API_URL, null, {
		query: {
			isArray: false
		}
	});
});

app.factory('KanbanFactory', function($resource, API_URL) {
	return $resource(API_URL + '/kanban/:id');
});

app.factory('CategoryFactory', function($resource, API_URL) {
	return $resource(API_URL + '/kanban/:kanbanId/category/:id', null, {
		add: {
			method: 'PUT',
			params: {
				kanbanId: '@kanban.id'
			}
		}
	});
});

app.controller('KanbanController', kanbanController);
app.controller('MainController', mainController);