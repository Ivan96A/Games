(function () {
	'use strict';

	angular
		.module('main')
		.controller('GamesCtrl', GamesCtrl);

	function GamesCtrl($scope, $state, GamesService, $location, ngDialog) {

		var sc = $scope;

		sc.getGames = function () {

			function succes(response) {
				sc.games = response.data;
			};

			function failed(response) {
				sc.games = response.data;
				console.log(response.status);
			};

			GamesService.getAll().then(succes, failed);
		};
 	}
})();