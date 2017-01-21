(function () {
    'use strict';

    angular
        .module('main', [
            'games',
            'register',
            'login',
            'ui.router',
            'ui.bootstrap',
            'ngCookies',
            'ngAnimate',
            'ngDialog',
            'flow',
            'base64',
            'naif.base64',
            'pascalprecht.translate',
            'cr.acl',
            'textAngular'
        ])
        .config(configure)
        .run(run);

    configure.$inject = ['$stateProvider', '$urlRouterProvider', '$translateProvider'];
    function configure($stateProvider, $urlRouterProvider, $translateProvider) {

        $urlRouterProvider.otherwise(function ($injector) {
            var $state = $injector.get("$state");
            $state.go('main.home');
        });

        $stateProvider
            .state('main', {
                url: '/',
                abstract: true,
                templateUrl: 'app/main.view.html'
            })
            .state('main.home', {
            url: 'home',
            views: {
                '': {
                    templateUrl: '/app/home/home.view.html'
                }
            }
        });
            
        
           
    }

    run.$inject = ['$rootScope', '$cookieStore', '$state', '$translate', '$http', 'crAcl'];
    function run($rootScope, $cookieStore, $state, $translate, $http,  crAcl) {

        $rootScope.globals = $cookieStore.get('globals') || {};

        crAcl.setInheritanceRoles({
            "ROLE_ADMIN": ["ROLE_ADMIN"],
            "ROLE_USER": ["ROLE_ADMIN"], 
            "ROLE_GUEST": ["ROLE_GUEST"]
        });

        crAcl.setRedirect('main.home');

        if($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata.id;
            crAcl.setRole($rootScope.currentUser.role);
        } else {
            crAcl.setRole("ROLE_GUEST");
        }
        
    }

})();