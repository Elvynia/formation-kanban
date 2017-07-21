var app = angular.module('kanban', ['ngResource', 'ngMaterial']);

app.constant('API_URL', '${api.url}');
// app.value('API_URL', '${api.url}');

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

var kanbanController = function($scope, KanbanFactory, CategoryFactory, TaskFactory) {
	var vm = this;
	vm.ready = false;
	$scope.$on('showKanban', function(event, data) {
		KanbanFactory.get(data, (kanban) => {
			vm.instance = kanban;
			CategoryFactory.query({ kanbanId: kanban.id },
				(categories) => {
					vm.categories = categories;
					for (let i = 0; i < categories.length; ++i) {
						TaskFactory.query({
							kanbanId: vm.instance.id,
							categoryId: vm.categories[i].id
						}, (tasks) => vm.categories[i].tasks = tasks);
					}
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

var editController = function($scope, $mdDialog) {
    this.showCategoryEdit = function() {
        let dialog = $mdDialog.prompt()
            .title('Modifier la catégorie')
            .placeholder('catname')
            .ariaLabel('Nom');
        $mdDialog.show(dialog);
    }
}

app.factory('DataFactory', function($resource, API_URL) {
	return $resource(API_URL, null, {
		query: {
			isArray: false
		}
	});
});

app.factory('KanbanFactory', function($resource, API_URL) {
	return $resource(API_URL + '/kanban/:id', null, {
		add: {
			method: 'PUT'
		}
	});
});

app.factory('CategoryFactory', function($resource, API_URL) {
	return $resource(API_URL + '/kanban/:kanbanId/category/:id', {
		kanbanId: '@kanban.id'
	}, {
		add: {
			method: 'PUT'
		}
	});
});

app.factory('TaskFactory', function($resource, API_URL) {
	return $resource(API_URL + '/kanban/:kanbanId/category/:categoryId/task/:id', {
		kanbanId: '@category.kanban.id',
		categoryId: '@category.id'
	}, {
		add: {
			method: 'PUT'
		}
	});
});

app.controller('KanbanController', kanbanController);
app.controller('MainController', mainController);
app.controller('EditController', editController);