/**
 * Created by Сергей on 16.02.2017.
 */
var upump = angular.module("UPump", [])
    .controller("UrlCtrl", function ($scope) {
        $scope.urlHeader = "header.html";
        $scope.urlFooter = "footer.html";
        $scope.urlMiddle="начало";
        $scope.openPage  = function (page) {
            $scope.urlMiddle =page ;

        }
    });
upump.controller("MyWorkotCtrl", function ($scope, $http) {
    $scope.urlHeader = "header.html";
    $scope.urlFooter = "footer.html";
    $scope.urlMiddle="template/myWorkoutTemplate.html";
    $scope.items={
        "workoutXML": [
            {
                "userId": 2,
                "workoutId": 91,
                "author": "Artyom Petrenko",
                "name": "новая",
                "rate": 2,
                "exerciseXMLList": []
            },
            {
                "userId": 2,
                "workoutId": 92,
                "author": "Artyom Petrenko",
                "name": "новая",
                "rate": 0,
                "exerciseXMLList": []
            },
            {
                "userId": 16,
                "workoutId": 107,
                "author": "",
                "name": "админская",
                "rate": 0,
                "exerciseXMLList": []
            }
        ],
            "postXMLs": [],
            "userInfoId": 0,
            "date": null,
            "login": null,
            "email": null,
            "password": null,
            "userId": 0

    }
    $scope.getPageAuthor= function (item) {
        alert(item.author);
        $http.get("/http://localhost:8080/api/v.1/user/from/0");
        // переход на страницу автора с его тренировками
    }

    $scope.open=function (item) {
        alert(item.name);
        // переход на стараницу упражнений
    }
    $scope.delete = function (item) {
        alert(item.workoutId);
        //удаление
    }



})
   /* .controller("FirstCtrl", function ($scope, $http) {


        $scope.addUser = function (newUser) {
            $scope.user = {
                login: newUser.login,
                password: newUser.password,
                email: newUser.email
            };
            var req = {
                method: 'POST',
                url: '/example.com',
                headers: {
                    'Content-Type': 'application/xml'
                },
            data: this.user
            };
                $http(req);
        }
        $scope.getError=function (error) {
            if (angular.isDefined(error)) {
                if (error.required) {
                    return "Поле не должно быть пустым";
                } else if (error.email) {
                    return "Введен некоректный емейл";
                }
            }
        }


    });*/
