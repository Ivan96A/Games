(function () {
    'use strict';

    angular
        .module('order', [
            'ui.router'
        ])
        .config(configure);

    configure.$inject = ['$stateProvider', '$urlRouterProvider'];
    function configure($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('main.order', {
                url: 'order',
                controller: 'OrderCtrl',
                templateUrl: 'app/order/order.view.html',
                data: {
                    is_granted: ["ROLE_USER"]
                }
            });

    }

})();