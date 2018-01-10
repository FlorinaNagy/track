app.controller('mapController', ['$scope', '$http', '$rootScope', '$state', '$window', function ($scope, $http, $rootScope, $state, $window) {

    $scope.performSearch = performSearch;
    $scope.clearMap = clearMap;
    $scope.dataModelChange = dataModelChange;

    initMap();

    function initMap() {
        console.log('map init');

        var myLatLng = {lat: 47.139262, lng: 24.489098};

        $scope.map = new google.maps.Map(document.getElementById('googleMap'), {
            zoom: 7,
            center: myLatLng
        });
    }

    function performSearch() {
        console.log('perform search');
        var position = $scope.position;

        if (position == null) {
        } else {
            // http://192.168.0.123:8085/position/terminalId/22222?startDate=2017-10-02&endDate=2017-11-23
            //$http.get('http://192.168.0.123:8085/position/terminalId/' + position.id + '?startDate=' + position.start + '&endDate=' + position.end)
            $http.get('http://localhost:8085/position/terminalId/' + position.id + '?startDate=' + position.start + '&endDate=' + position.end)
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
        console.log('success response = ' + JSON.stringify(response));
        if (response.data.status == 200 && response.data.entity != null) {
            $scope.invalidSearch = false;
            createMapMarkers(response.data.entity);
        } else if (response.data.status == 404) {
            $scope.invalidSearch = true;
        }
    }

    function errorResponse(response) {
        console.log('error response = ' + JSON.stringify(response));

    }

    function createMapMarkers(data) {
        var markers = [];

        for (var index = 0; index < data.length; index++) {
            markers[index] = angular.copy(data[index]);
        }

        addMapMarkers(markers, $scope.map);
    }

    function addMapMarkers(markers, map) {

        $scope.markers = [];
        console.log('markers ==' + JSON.stringify(markers));
        for (var index = 0; index < markers.length; index++) {
            if (markers[index] != null) {
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(markers[index].latitude, markers[index].longitude),
                    map: map,
                    title: markers[index].terminalId
                });

                marker.setMap(map);
                $scope.markers.push(marker);
            }
        }
    }

    function clearMap() {
        if ($scope.markers != null) {
            for (var index = 0; index < $scope.markers.length; index++) {
                $scope.markers[index].setMap(null);
            }
        }

        if($scope.invalidSearch) {
            $scope.invalidSearch = false;
        }

        $scope.position = {};
    }

    function dataModelChange() {
        $scope.invalidSearch = false;
    }
}]);