(function () {
	'use strict';

	angular
		.module('main')
		.service('OrderService', function ($http) {

			var urlBase = '/order';

			this.getByUsername = function (username) {
				return $http.get(urlBase + '/' + username);
			};

			this.getCost = function(username) {
				return $http.get(urlBase + '/cost/' + username);
			};

			this.removeGame = function(id) {
				return $http.post(urlBase + '/game/delete/' + id);
			};

		});
		
})();