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
						CredentialsService.SetCredentials(response.data.firstName, response.data.username, response.data.role);
						crAcl.setRole(response.data.role);
						switch (response.data.massage) {
                        case 'Success':
                            $state.go('main.home');
                            break;
                    }

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