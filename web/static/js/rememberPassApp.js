var upump = angular.module("UPump", []);
upump.controller("UrlCtrl", function ($scope, $http) {
    $scope.redirect = function redirect() {
        window.location.href = "http://www.upump.info";
    };
    
    $scope.item = {};
    $scope.changePass = function (item) {
        var req = {
            method: 'POST',
            url: '/api/v.1/user/changePass',
            data: {
                "password": item.password
            }

        };
        $http(req)
            .then(function () {
                $scope.rememberPassResult = "пароль изменен, перейдите на сайт";
                $scope.status = true;
            }, function () {
                $scope.rememberPassResult = "изменение пароля невозможно";
                $scope.status = true;
            });
    }
});
