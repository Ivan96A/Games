(function () {
	'use strict';

	angular
		.module('main')
		.service('UserService', function ($http) {

			this.create = function (user) {
				return $http.post('user/public/register', user);
			}

		});
		
})();
