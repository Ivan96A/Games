(function () {
	'use strict';

	angular
		.module('main')
		.controller('OrderCtrl', OrderCtrl);

	function OrderCtrl($scope, $state, OrderService, $location, ngDialog, $rootScope) {

		var sc = $scope;

		sc.getOrdersByUsername = function () {

			function success(response) {
				sc.orders = response.data;
			};

			function failed(response) {
				sc.orders = response.data;
				console.log(response.status);
			};

			OrderService.getByUsername($rootScope.globals.currentUser.username).then(success, failed);
		};

		sc.getCost = function () {

			function success(response) {
				sc.cost = response.data;
			};

			function failed(response) {
				sc.cost = response.data;
				console.log(response.status);
			};

			OrderService.getCost($rootScope.globals.currentUser.username).then(success, failed);
		};

		sc.removeOrderGame = function(id) {
			function success(response) {
                sc.getOrdersByUsername();
            }

            function failed(response) {
                sc.orders = response.data;
                console.log(response.status);
            }

            OrderService.removeGame(id).then(success, failed);

		};
 	
 }

})();