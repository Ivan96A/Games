(function () {
	'use ctrict';

	angular
		.module('main')
		.controller('GameEditCtrl', GameEditCtrl);

	function GameEditCtrl($scope, $stateParams, GamesService, $location, $rootScope) {
		var sc = $scope;

		 sc.gameId = $stateParams.gameId;

		 sc.gameName = '';

		 sc.gameAndUserData = {
		 		'name': '',
		 		'username': ''
		 };

		 sc.gameAndUserData.username = $rootScope.globals.currentUser.username

		sc.getGameById = function(id) {

			function success(response) {
				sc.game = response.data;
				sc.gameName = sc.game.name;
			}

			function failed(response) {
				sc.game = response.data;
				console.log(response.status);
			}

			GamesService.getById(id).then(success, failed);

		};

		sc.sentData = function () {
			sc.gameAndUserData.name = sc.gameName;
			GamesService.sentData(sc.gameAndUserData)
				.then(function successCallback(response) {

				}, function errorCallback(response) {

				});
		}
	}
})();