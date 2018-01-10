/**
 * Angular main application module
 */
var app = angular.module('app', ['ui.router']);

app.run(function ($rootScope, $location, $state) {
   /* $rootScope.$on('$stateChangeStart',
        function (event, toState, toParams, fromState, fromParams) {
            console.log('Changed state to: ' + toState);
        });*/

    if (!$rootScope.isAuthenticated) {
        $state.transitionTo('login');
    }
});

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/index');

    var loginState = {
        name: 'login',
        url: '/login',
        templateUrl: '/login/login-view.html'
    };

    var mapState = {
        name: 'map',
        url: '/map',
        templateUrl: '/map/map-view.html'
    };

    var signUpState = {
        name: 'signUp',
        url: '/signUp',
        templateUrl: '/signUp/signup-view.html'
    };

    $stateProvider.state(loginState);
    $stateProvider.state(mapState);
    $stateProvider.state(signUpState);
}]);