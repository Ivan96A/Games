(function () {
	'use strict';

	angular
		.module('main')
		.controller('LoginCtrl', LoginCtrl);

	function LoginCtrl($scope, $state, LoginService, crAcl, CredentialsService) {
		var sc = $scope;

		CredentialsService.ClearCredentials();

		sc.usernameCheckShow = false;

		sc.login = function (user) {
				LoginService.login(user)
					.then(function successCallback(response) {
						CredentialsService.SetCredentials(response.data.id, user.username, user.password, response.data.role);
						crAcl.setRole(response.data.role);
						sc.user = response.data;
					}, function errorCallback(response) {
						sc.authFailed = true;
					});
		}

		/*sc.checkUsername = function (username) {
			sc.usernameCheckShow = true;

			LoginService.check(username)
				.then(function successCallback(response) {
					sc.usernameChecked = true;
				}, function errorCallback(response) {
					sc.usernameChecked = false;
				});
		}*/
	}
})();