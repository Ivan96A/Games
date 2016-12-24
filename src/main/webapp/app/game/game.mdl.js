(function () {
    'use strict';

    angular
        .module('games', [
            'ui.router'
        ])
        .config(configure);

    configure.$inject = ['$stateProvider', '$urlRouterProvider'];
    function configure($stateProvider, $urlRouterProvider) {
        
        $stateProvider
            .state('main.games', {
                url: 'catalog',
                controller: 'GamesCtrl',
                templateUrl: 'app/game/game.view.html'
            })
            .state('main.game',{
                url: 'game/:gameId',
                controller: 'GameEditCtrl',
                templateUrl: 'app/game/profile/game.profile.html'
            });

    }

})();