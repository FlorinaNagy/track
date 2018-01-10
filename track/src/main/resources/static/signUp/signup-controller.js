app.controller('signUpController', ['$scope', '$http', '$rootScope', '$state', function ($scope, $http, $rootScope, $state) {

    $rootScope.isAuthenticated = false;

    $scope.saveUser = saveUser;
    $scope.cancelSaveUser = cancelSaveUser;

    function saveUser() {
        console.log('sign up controller');
        var newUser = $scope.newUser;

        //http://192.168.0.123:8085/user/saveUser
        //http://172.20.10.3:8085/user/saveUser
        $http.post('http://localhost:8085/user/saveUser', newUser)
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

    function cancelSaveUser(){
        $state.go('login');
    }

    function successfullyResponse(response) {
        if (response.data.status === 200) {
            $rootScope.isAuthenticated = true;
            $rootScope.session = angular.copy(response.data.entity);
            $state.go('map');
        } else if (response.data.status === 404) {
            $rootScope.isAuthenticated = false;
            console.log('user not found');
        }
    }

    function errorResponse(response) {
        console.log('response error === ' + JSON.stringify(response));
        $rootScope.isAuthenticated = false;
        console.log('login error');
    }
}]);