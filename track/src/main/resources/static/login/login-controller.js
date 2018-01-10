app.controller('loginController', ['$scope', '$http', '$rootScope', '$state', function ($scope, $http, $rootScope, $state) {

    $rootScope.isAuthenticated = false;

    $scope.validateLogin = validateLogin;
    $scope.dataModelChange = dataModelChange;
    $scope.crateNewUser = crateNewUser;
    $scope.logout = logout;

    function validateLogin() {

        var user = $scope.user;
        console.log('login controller');

        if (user.username === '' && user.password === '') {
            console.log('error');
        } else {
            //http://172.20.10.3:8085/login
            $http.post('http://localhost:8085/login', user)
                .then(
                    function (response) {
                        // success callback
                        successfullyResponse(response);
                    },
                    function (response) {
                        errorResponse(response);
                        // failure callback
                    }
                );
        }
    }

    function successfullyResponse(response) {
        if (response.data.status === 200) {

            $rootScope.isAuthenticated = true;
            $rootScope.session = angular.copy(response.data.entity);
            $scope.invalidUser = false;
            $state.go('map');

        } else if (response.data.status === 404) {
            $rootScope.isAuthenticated = false;
            $scope.invalidUser = true;
            console.log('user not found');
        }
    }

    function errorResponse(response) {
        console.log('response error === ' + JSON.stringify(response));
        console.log('login error');
    }

    function dataModelChange() {
        $scope.invalidUser = false;
    }
    function crateNewUser() {
        $state.go('signUp');
    }

    function logout() {
        $rootScope.isAuthenticated = false;
        $rootScope.session = {};
        $state.go('login');
    }
}]);