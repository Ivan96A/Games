(function () {
	'use strict';

	angular
		.module('main')
		.service('GamesService', function ($http) {

			var urlBase = '/game';

			this.getAll = function() {
				return $http.get(urlBase);
			};

			this.getById = function(id) {
				return $http.get(urlBase + '/' + id)
			}

		});
})();