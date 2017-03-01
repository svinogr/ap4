var upump = angular.module("UPump", ["ngAnimate", "ngRoute"]);

upump.controller("UrlCtrl", function ($scope, $http, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        console.log("dwdwdwdw" + $location.path());
        console.log($location.url());

        $scope.logoutHeader = function () {
            var promise = $http.get("/logout");
            promise.then(logoutSuccess, logoutError)
        };
        function logoutSuccess(respons) {
            $scope.value = {status: false};
            $scope.url.url = "/static/template/new.html";
        }

        function logoutError(respons) {
            $scope.value = {status: true}
        }

        $scope.loginHeader = function () {

            $scope.url.url = "/static/myWorkout.html";

        };


        $scope.openPage = function (page) {
            $scope.url.url = page;
            var path = page.substring(8, 13);
            /* $location.path(path);*/
        }
        $scope.url = {
            header: "/static/header.html",
            footer: "/static/footer.html",
            url: "/static/template/new.html"
        };
        var status = function () {
            var promise = $http.get("/loginstatus");
            promise.then(fulfilled, rejected)
        };

        function fulfilled(respons) {
            $scope.value = {status: true}
        }

        function rejected(respons) {
            $scope.value = {status: false}
        }

        status();

    }
);

upump.controller("MyViewCtrl", function ($scope, $http, $route, $routeParams, $location) {

    if ($scope.value.status) {
        $scope.urlView = {
            url: "/static/template/myWorkoutTemplate.html"
        }
    } else  $scope.urlView = {url: "/static/template/loginForm.html"};
    $scope.login = function (user) {
        var dataLogin = btoa(user.login + ":" + user.password);
        var req = {
            method: 'GET',
            url: '/login',
            headers: {
                'Authorization': "Basic " + dataLogin
            }
        };
        $http(req)
            .then(function () {
                $scope.value.status = true;
                $scope.urlView.url = "/static/template/myWorkoutTemplate.html";
                getItems();
            }, function () {
                $scope.status = {};
            });
    }
    var getItems = function () {
        var promise = $http.get("/api/v.1/user/auth/workout");
        promise.then(fulfilled, rejected)
    };

    function fulfilled(respons) {
        $scope.items = respons.data;
    }

    function rejected(respons) {
        return {};
    }

});

upump.controller("MyWorkoutCtrl", function ($scope, $http, $route, $routeParams, $location) {
    $scope.getPageAuthor = function (item) {
        var req = {
            method: 'GET',
            url: '/api/v.1/user/' + item.userId + "/userInfo",
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http(req)
            .then(function (response) {
                $scope.userInfo = response.data;
                $scope.urlView.url = "/static/authorPage.html";

            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });

    }


    $scope.createOrEditWorkout = function (item) {
        $scope.editWorkout = item ? angular.copy(item) : {};
        $scope.urlView.url = "/static/template/editNameWorkout.html";
    };
    
    $scope.saveWorkout = function (item) {
        if (angular.isDefined(item.workoutId)) {
            updateWorkout(item)
        } else createWorkout(item);
    }
    function createWorkout(item) {

        var req = {
                method: 'POST',
                url: '/api/v.1/user/auth/workout/',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {"name": item.name}
            }
            ;
        $http(req)
            .then(function (respons) {
                var data = respons.data;
                var newItem = {
                    "userId": data.userId,
                    "workoutId": data.workoutId,
                    "author": data.author,
                    "name": data.name,
                    "rate": 0
                };
                $scope.items.workoutXML.push(newItem);
                $scope.urlView.url = "/static/template/myWorkoutTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    $scope.deleteWorkout = function (item) {
        var req = {
                method: 'DELETE',
                url: '/api/v.1/workout/' + item.workoutId,
                headers: {
                    'Content-Type': 'application/json'
                },
            }
            ;
        $http(req)
            .then(function () {
                $scope.items.workoutXML.splice($scope.items.workoutXML.indexOf(item), 1);
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    };

    function updateWorkout(item) {
        var req = {
                method: 'PUT',
                url: '/api/v.1/workout/' + item.workoutId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {"name": item.name}
            }
            ;
        $http(req)
            .then(function () {
                for (var i = 0; i < $scope.items.workoutXML.length; i++) {
                    if ($scope.items.workoutXML[i].workoutId == item.workoutId) {
                        $scope.items.workoutXML[i].name = item.name;
                        break;
                    }
                }
                $scope.urlView.url = "/static/template/myWorkoutTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    var getItemsWorkout = function () {
        var promise = $http.get("/api/v.1/user/auth/workout");
        promise.then(fulfilled, rejected)
    };

    function fulfilled(respons) {

        $scope.items = respons.data;
    }

    function rejected(respons) {
        return {};
    }

    $scope.openMyWorkout = function (item) {
        var req = {
                method: 'GET',
                url: '/api/v.1/workout/' + item.workoutId + "/exercise",
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            ;
        $http(req)
            .then(function (respons) {
                $scope.workout= respons.data;
                $scope.urlView.url = "/static/template/myExerciseTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }
    $scope.backToWorkouts = function () {
        $scope.urlView.url = "/static/template/myWorkoutTemplate.html";

    }

    $scope.openWorkout = function (item) {
        var req = {
                method: 'GET',
                url: '/api/v.1/workout/' + item.workoutId + "/exercise",
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            ;
        $http(req)
            .then(function (response) {
                $scope.workout = response.data;
                $scope.urlView.url = "/static/template/exerciseTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }
    $scope.backToWorkouts = function () {
        $scope.urlView.url = "/static/template/myWorkoutTemplate.html";

    }


//for exercise
    $scope.backToExercise = function (item) {
        $scope.urlView.url = "/static/template/myExerciseTemplate.html";
    }

    $scope.backToTry = function (item) {
        $scope.urlView.url = "/static/template/myTryTemplate.html";
    }


    /*var getItemsExercise = function (item) {
        var promise = $http.get("/api/v.1/workout/" + item.workoutId + "/exercise");
        promise.then(getExerciseSuccess, getExerciseError)
    };*/

    function getExerciseSuccess(respons) {
        $scope.workout = respons.data;
    }

    function getExerciseError(respons) {
        return {};
    }


    $scope.createOrEditExercise = function (item) {
        if (!angular.isDefined(item.exerciseId)) {
            $scope.editExercise = {"workoutId": item.workoutId};
        } else $scope.editExercise = {"name": item.name, "workoutId": item.workoutId, "exerciseId": item.exerciseId};
        $scope.urlView.url = "/static/template/editNameExercise.html";
    };

    $scope.saveExercise = function (item) {
        if (angular.isDefined(item.exerciseId)) {
            updateExercise(item)
        } else createExercise(item);
    }


    function createExercise(item) {

        var req = {
            method: 'POST',
            url: '/api/v.1/workout/' + item.workoutId + "/exercise",
            headers: {
                'Content-Type': 'application/json'
            },
            data: {"name": item.name}
        };
        $http(req)
            .then(function (response) {
                var data = response.data;
                var newItem = {
                    "workoutId": data.workoutId,
                    "exerciseId": data.exerciseId,
                    "position": data.position,
                    "name": data.name
                }
                $scope.workout.exerciseXMLList.push(newItem);
                $scope.urlView.url = "/static/template/myExerciseTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    function updateExercise(item) {
        var req = {
                method: 'PUT',
                url: '/api/v.1/exercise/' + item.exerciseId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {"name": item.name}
            }
            ;
        $http(req)
            .then(function () {
                for (var i = 0; i < $scope.workout.exerciseXMLList.length; i++) {
                    if ($scope.workout.exerciseXMLList[i].exerciseId == item.exerciseId) {
                        $scope.workout.exerciseXMLList[i].name = item.name;
                        break
                    }
                }
                $scope.urlView.url = "/static/template/myExerciseTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    $scope.deleteExercise = function (item) {
        var req = {
                method: 'DELETE',
                url: '/api/v.1/exercise/' + item.exerciseId,
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            ;
        $http(req)
            .then(function () {
                $scope.workout.exerciseXMLList.splice($scope.workout.exerciseXMLList.indexOf(item), 1);
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    };
    //Exercise
    $scope.openExercise = function (item) {
        //getItemsTry(item);
        $scope.try=item;
        $scope.urlView.url = "/static/template/myTryTemplate.html";
    };


    ///try
    $scope.createOrEditTry = function (item) {
        if (!angular.isDefined(item.tryId)) {
            $scope.editTry = {"exerciseId": item.exerciseId};
        } else $scope.editTry = {
            "repeat": item.repeat,
            "weight": item.weight,
            "tryId": item.tryId,
            "exerciseId": item.exerciseId
        };
        $scope.urlView.url = "/static/template/editTry.html";
    };

    $scope.saveTry = function (item) {
        if (angular.isDefined(item.tryId)) {
            updateTry(item)
        } else createTry(item);
    }


    function createTry(item) {
        var req = {
            method: 'POST',
            url: '/api/v.1/exercise/' + item.exerciseId + "/try",
            headers: {
                'Content-Type': 'application/json'
            },
            data: {"weight": item.weight, "repeat": item.repeat}
        };
        $http(req)
            .then(function (response) {
                var data = response.data;
                var newItem = {
                    "exerciseId": data.exerciseId,
                    "tryId": data.tryId,
                    "weight": data.weight,
                    "repeat": data.repeat,
                    "done": false
                };
                for (var i = 0; i < $scope.workout.exerciseXMLList.length; i++) {
                    if ($scope.workout.exerciseXMLList[i].exerciseId == newItem.exerciseId) {
                        var index = $scope.workout.exerciseXMLList.indexOf($scope.workout.exerciseXMLList[i]);
                        $scope.workout.exerciseXMLList[index].list.push(newItem);
                    }
                }
               // $scope.try.list.push(newItem);
                $scope.urlView.url = "/static/template/myTryTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    function updateTry(item) {
        var req = {
                method: 'PUT',
                url: '/api/v.1/try/' + item.tryId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {"weight": item.weight, "repeat": item.repeat}
            }
            ;
        $http(req)
            .then(function () {
                for (var i = 0; i < $scope.try.list.length; i++) {
                    if ($scope.try.list[i].exerciseId == item.exerciseId) {
                        $scope.try.list[i].weight = item.weight;
                        $scope.try.list[i].repeat = item.repeat;
                        break;
                    }
                }
                for (var i = 0; i < $scope.workout.exerciseXMLList.length; i++) {
                    if ($scope.workout.exerciseXMLList[i].exerciseId == item.exerciseId) {
                        var index = $scope.workout.exerciseXMLList.indexOf($scope.workout.exerciseXMLList[i]);
                        var list = $scope.workout.exerciseXMLList[index].list;
                        for (var j = 0; j < list.length; j++) {
                            if (list[j].tryId == item.tryId) {
                                $scope.workout.exerciseXMLList[i].list[j] = item;
                                break;
                            }
                        }
                    }
                }
                $scope.urlView.url = "/static/template/myTryTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    $scope.deleteTry = function (item) {
        var req = {
            method: 'DELETE',
            url: '/api/v.1/try/' + item.tryId,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http(req)
            .then(function () {

                    for (var i = 0; i < $scope.workout.exerciseXMLList.length; i++) {
                    if ($scope.workout.exerciseXMLList[i].exerciseId == item.exerciseId) {
                        $scope.workout.exerciseXMLList[i].list.splice($scope.workout.exerciseXMLList[i].list.indexOf(item),1);
                    }
                }
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    };

    $scope.done = function (item) {
        var done = !item.done;
        var req = {
            method: 'PUT',
            url: '/api/v.1/try/' + item.tryId + "/do",
            headers: {
                'Content-Type': 'application/json'
            },
            data: {"done": done}
        };
        $http(req)
            .then(function () {
                var doneTry = item;
                doneTry.done = done;
                for (var i = 0; i < $scope.workout.exerciseXMLList.length; i++) {
                    if ($scope.workout.exerciseXMLList[i].exerciseId == item.exerciseId) {
                        var index = $scope.workout.exerciseXMLList.indexOf($scope.workout.exerciseXMLList[i]);
                        var list = $scope.workout.exerciseXMLList[index].list;
                        for (var j = 0; j < list.length; j++) {
                            if (list[j].tryId == item.tryId) {
                                $scope.workout.exerciseXMLList[i].list[j] = doneTry;
                                break;
                            }
                        }
                    }
                }
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });


    }

   /* var getItemsTry = function (item) {
        var req = {
                method: 'GET',
                url: '/api/v.1/exercise/' + item.exerciseId + "/try",
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            ;
        $http(req)
            .then(function (respons) {
                $scope.try = respons.data;

            }, function () {
                alert("все плохо");
                /!*$scope.status = {};*!/
            });
    };*/


    getItemsWorkout();
//info

    $scope.openWorkout = function (item) {
        var req = {
                method: 'GET',
                url: '/api/v.1/workout/' + item.workoutId + "/exercise",
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            ;
        $http(req)
            .then(function (respons) {
                $scope.workout = respons.data;
                $scope.urlView.url = "/static/template/exerciseTemplate.html";
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }
    $scope.authorWorkout = function (item) {
        var req = {
            method: 'GET',
            url: '/api/v.1/user/' + item.userId,
            headers: {
                'Content-Type': 'application/json'
            },

        };
        $http(req)
            .then(function (response) {
                $scope.items = response.data;
                $scope.urlView.url = "/static/template/workoutTemplate.html";

            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });

    };


    $scope.backToInfo = function () {
        $scope.urlView.url = "/static/authorPage.html";

    }

})
;

/*

 /!*   while (true) {
 $scope.$on("auth2", function (event, args) {
 $scope.auth = args.auth;
 });
 if ($cope.auth.isDefined) {
 alert("jhkdf");
 break;
 } else if ($scope.auth) {
 alert("true");
 break;
 } else alert("false");
 }
 *!/
 /!*

 /!*   do {*!/
 $scope.$on("auth2", function (event, args) {
 $scope.auth = args.auth;
 });
 /!* } while (!$scope.auth.isDefined());*!/
 *!/
 /!* alert("dd");
 if ($scope.auth == true) {
 $scope.url.url = "/static/template/myWorkoutTemplate.html"
 } else  $scope.url.url = "/static/template/loginForm.html";
 $scope.login = function (user) {
 var dataLogin = btoa(user.login + ":" + user.password);
 var req = {
 method: 'GET',
 url: '/login',
 headers: {
 'Authorization': "Basic " + dataLogin
 }
 };
 $http(req)
 .then(function (data) {
 $scope.currentView = "table";
 $scope.auth = true;
 }, function () {
 alert("dwdw");
 })

 };*!/


 });


 /!*
 $scope.currentView = "table";
 /!* if($scope.auth!=true){
 $rootScope.$broadcast("changePageUrl",
 {
 pageUrl : "loginForm"
 })


 }*!/

 // для workout



 $scope.login = function (user) {
 var dataLogin = btoa(user.login + ":" + user.password);
 var req = {
 method: 'GET',
 url: '/login',
 headers: {
 'Authorization': "Basic " + dataLogin
 }
 };
 $http(req)
 .then(function (data) {
 $scope.currentView="table";
 $scope.auth=true;
 }, function(){alert("dwdw");} )

 };


 $scope.createOrEditWorkout = function (item) {
 $scope.currentView = "addWorkout";
 $scope.currentItem = item ? angular.copy(item) : {};
 console.log(item.name);
 };
 function createWorkout(item) {


 }

 function updateWorkout(item) {

 }

 $scope.saveEdit = function (item) {
 if (angular.isDefined(item.workoutId)) {
 updateWorkout(item)
 } else createWorkout(item);
 };


 $scope.open = function (item) {
 // $scope.urlInclude="/static/template/myExerciseTemplate.html"

 };

 $scope.getPageAuthor = function (item) {
 alert(item.name);
 // переход на стараницу упражнений
 };

 $scope.delete = function (item) {
 alert(item.workoutId);
 //удаление
 };

 }
 );
 *!/
 upump.controller("MyExerciseCtrl", function ($scope, $http, $rootScope) {
 $scope.$on("exercise", function (event, args) {
 console.log(args.workout);
 alert(args.workout);
 });

 });

 /!*
 upump.controller("MyWorkoutCtrl", function ($http) {
 /!* alert("aaa");
 $scope.items = $http.get("/api/v.1/user/auth/workout");
 /!*var promise = $http.get("/api/v.1/user/auth/workout");
 *!/
 promise.then(fulfilled, rejected);

 function fulfilled(response) {
 $scope.items = response.data; // data - данные запроса
 }

 function rejected(error) {
 if (error.status = 401) {
 $scope.currentView = "loginForm";

 }
 console.error(error.status);
 console.error(error.statusText);
 }*!/

 });*!/

 /!* .controller("FirstCtrl", function ($scope, $http) {


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


 });*!/
 *!/
 */
