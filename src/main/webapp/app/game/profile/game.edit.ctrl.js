(function () {
	'use ctrict';

	angular
		.module('main')
		.controller('GameEditCtrl', GameEditCtrl);

	function GameEditCtrl($scope, $stateParams, GamesService, $location) {
		var sc = $scope;

		 sc.gameId = $stateParams.gameId;

		sc.getGameById = function(id) {

			function success(response) {
				sc.game = response.data;
			}

			function failed(response) {
				sc.game = response.data;
				console.log(response.status);
			}

			GamesService.getById(id).then(success, failed);
		}
	}
})();