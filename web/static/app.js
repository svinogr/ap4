var upump = angular.module("UPump", ["ngRoute", "ngAnimate"]);
upump.config(function ($routeProvider, $locationProvider) {

    $routeProvider.when("/news", {
        templateUrl: "/static/news.html"
    });
    $routeProvider.when("/myworkouts", {
        templateUrl: "/static/myWorkout.html",
        controller: "MyWorkoutCtrl"
    });

    $routeProvider.when("/createOrEditWorkout", {
        templateUrl: "/static/template/editNameWorkout.html"
    });


    $routeProvider.when("/best", {
        templateUrl: "/static/best.html",
        controller: "BestCtrl"
    });
    $routeProvider.when("/all", {
        templateUrl: "/static/all.html"
    });
    $routeProvider.when("/userInfo", {
        templateUrl: "/static/authorPage.html",
        controller: "InfoCTRL"
    });

    $routeProvider.when("/workouts", {
        templateUrl: "/static/template/workoutTemplate.html",
        controller: "WorkoutsCTRL"
    });
    $routeProvider.when("/exercises", {
        templateUrl: "/static/template/exerciseTemplate.html",
        controller: "ExercisesCTRL"
    });
    $routeProvider.when("/login", {
        templateUrl: "/static/template/loginForm.html",
        controller: "LoginCTRL"
    });
    $routeProvider.when("/registration", {
        templateUrl: "/static/registrationForm.html",
        controller: "RegistrationCTRL"
    });

    $routeProvider.when("/myexercises", {
        templateUrl: "/static/template/myExerciseTemplate.html",
        controller: "MyExerciseCTRL"
    });
    $routeProvider.when("/mytries", {
        templateUrl: "/static/template/myTryTemplate.html",
        controller: "MyTryCTRL"
    });


    $locationProvider.html5Mode(true);

});

upump.controller("InfoCTRL", function ($scope, $http, $location) {
    $scope.openAuthorWorkout = function () {
        var req = {
            method: 'GET',
            url: '/api/v.1/user/' + $scope.controllerValue.userInfo.userId,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http(req)
            .then(function (response) {
                $scope.controllerValue.authorWorkouts = response.data;
                $location.path("workouts");

            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });


    }
});

upump.controller("LoginCTRL", function ($scope, $http, $location) {
    $scope.login = function (user) {
        var dataLogin = btoa(user.login + ":" + user.password);
        var req = {
            method: 'GET',
            url: '/',
            headers: {
                'Authorization': "Basic " + dataLogin
            }
        };
        $http(req)
            .then(function () {
                $scope.value.status = true;
                $location.path("myworkouts");
            }, function () {
                $scope.status = {};
            });
    }
});

upump.controller("WorkoutsCTRL", function ($scope, $http, $location) {
    $scope.openWorkout = function (item) {
        var req = {
            method: 'GET',
            url: '/api/v.1/workout/' + item.workoutId + "/exercise",
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http(req)
            .then(function (response) {
                $scope.controllerValue.authorExercises = response.data;
                $location.path("exercises");

            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });

    }
});

upump.controller("ExercisesCTRL", function ($scope, $http, $location) {
    $scope.copy = function (item) {
        alert(item);
        if ($scope.value.status) {
            var req = {
                method: 'POST',
                url: '/api/v.1/workout/' + item.workoutId + "/copy",
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req)
                .then(function (response) {
                    var data = response.data;
                    var newWorkout = {
                        "userId": data.userId,
                        "workoutId": data.workoutId,
                        "author": data.author,
                        "name": data.name,
                        "rate": data.rate
                    };
                    $scope.controllerValue.myWorkouts.push(newWorkout);// добавление в скоп моих тренировок
                    $scope.controllerValue.authorWorkouts.workoutXML.push(newWorkout);
                    if ($scope.controllerValue.myUserInfo.userId == newWorkout.userId) {
                        $scope.controllerValue.authorWorkouts.workoutXML.push(newWorkout);
                    }
                }, function () {
                    alert("все плохо");
                    /*$scope.status = {};*/
                });


        } else $location.path("login");
    }
});

upump.controller("MyTryCTRL", function ($scope, $http, $location) {
    $scope.cancel = function () {
        $scope.createOrEditStatus = false;
    }
    $scope.createOrEditTry = function (item) {
        if (!angular.isDefined(item.tryId)) {
            $scope.editTry = {"exerciseId": item.exerciseId};
            $scope.createOrEditStatus = true;
        } else $scope.editTry = {
            "repeat": item.repeat,
            "weight": item.weight,
            "tryId": item.tryId,
            "exerciseId": item.exerciseId
        };
        $scope.createOrEditStatus = true;
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
                for (var i = 0; i < $scope.controllerValue.myExercises.exerciseXMLList.length; i++) {
                    alert($scope.controllerValue.myExercises.exerciseXMLList.length);
                    if ($scope.controllerValue.myExercises.exerciseXMLList[i].exerciseId == newItem.exerciseId) {
                        $scope.controllerValue.myExercises.exerciseXMLList[i].list.push(newItem);
                        break;
                    }
                }
                $scope.controllerValue.myTryies.list.push(newItem);
                $scope.createOrEditStatus = false;
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
                for (var i = 0; i < $scope.controllerValue.myTryies.list.length; i++) {
                    if ($scope.controllerValue.myTryies.list[i].tryId == item.tryId) {
                        $scope.controllerValue.myTryies.list[i].weight = item.weight;
                        $scope.controllerValue.myTryies.list[i].repeat = item.repeat;
                        break;

                    }
                }
                for (var j = 0; j < $scope.controllerValue.myExercises.exerciseXMLList.length; j++) {
                    for (var k = 0; k < $scope.controllerValue.myExercises.exerciseXMLList[j].list.length; k++) {
                        if ($scope.controllerValue.myExercises.exerciseXMLList[j].list[k].tryId == item.tryId) {
                            $scope.controllerValue.myExercises.exerciseXMLList[j].list[k] = item;
                            break;
                        }
                    }
                }
                $scope.createOrEditStatus = false;
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
                $scope.controllerValue.myTryies.list.splice($scope.controllerValue.myTryies.list.indexOf(item), 1);
                for (var j = 0; j < $scope.controllerValue.myExercises.exerciseXMLList.length; j++) {
                    for (var k = 0; k < $scope.controllerValue.myExercises.exerciseXMLList[j].list.length; k++) {
                        if ($scope.controllerValue.myExercises.exerciseXMLList[j].list[k].tryId == item.tryId) {
                            $scope.controllerValue.myExercises.exerciseXMLList[j].list.splice($scope.controllerValue.myExercises.exerciseXMLList[j].list.indexOf(item), 1);
                            break;
                        }
                    }
                }
                /* for (var i = 0; i < $scope.controllerValue.myTryies.list.length; i++) {
                 if ($scope.controllerValue.myTryies.list[i].tryId == item.tryId) {

                 break;

                 }
                 }
                 */
                /* 
                 for (var i = 0; i < $scope.controllerValue.myExercises.exerciseXMLList.length; i++) {
                 if ($scope.controllerValue.myExercises.exerciseXMLList[i].exerciseId == item.exerciseId) {
                 $scope.controllerValue.myExercises.exerciseXMLList[i].list.splice($scope.controllerValue.myExercises.exerciseXMLList[i].list.indexOf(item), 1);
                 }
                 }
                 */
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    };


});

upump.controller("MyExerciseCTRL", function ($scope, $http, $location) {
    $scope.createOrEditExercise = function (item) {
        if (!angular.isDefined(item.exerciseId)) {
            $scope.createOrEditStatus = true;
            $scope.editExercise = {"workoutId": item.workoutId};
        } else $scope.editExercise = {"name": item.name, "workoutId": item.workoutId, "exerciseId": item.exerciseId};
        $scope.createOrEditStatus = true;
    };

    $scope.cancel = function () {
        $scope.createOrEditStatus = false;
    }

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
                    "name": data.name,
                    "list":[]
                }
                $scope.controllerValue.myExercises.exerciseXMLList.push(newItem);
                $scope.createOrEditStatus = false;
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
                for (var i = 0; i < $scope.controllerValue.myExercises.exerciseXMLList.length; i++) {
                    if ($scope.controllerValue.myExercises.exerciseXMLList[i].exerciseId == item.exerciseId) {
                        $scope.controllerValue.myExercises.exerciseXMLList[i].name = item.name;
                        break
                    }
                }
                $scope.createOrEditStatus = false;
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
                $scope.controllerValue.myExercises.exerciseXMLList.splice($scope.controllerValue.myExercises.exerciseXMLList.indexOf(item), 1);
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
                for (var i = 0; i < $scope.controllerValue.myExercises.exerciseXMLList.length; i++) {
                    if ($scope.controllerValue.myExercises.exerciseXMLList[i].exerciseId == item.exerciseId) {
                        var index = $scope.controllerValue.myExercises.exerciseXMLList.indexOf($scope.controllerValue.myExercises.exerciseXMLList[i]);
                        var list = $scope.controllerValue.myExercises.exerciseXMLList[index].list;
                        for (var j = 0; j < list.length; j++) {
                            if (list[j].tryId == item.tryId) {
                                $scope.controllerValue.myExercises.exerciseXMLList[i].list[j] = doneTry;
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

    $scope.openExercise = function (item) {
        var req = {
                method: 'GET',
                url: '/api/v.1/exercise/' + item.exerciseId + "/try",
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            ;
        $http(req)
            .then(function (response) {
                $scope.controllerValue.myTryies = response.data;
                $location.path("mytries");

            }, function () {
                alert("все плохо");
            });
    }
});


upump.controller("UrlCtrl", function ($scope, $http, $route, $routeParams, $location) {
        var back = function () {
            window.history.back();
        };

        $scope.backBtn = function () {
            window.history.back();
        };

        $scope.controllerValue = {};

        console.log("path" + $location.path());
        console.log($location.url());
        $scope.acceptRegistration = function (item) {

            $http.get(item);
        }

        $scope.logoutHeader = function () {
            var promise = $http.get("/logout");
            promise.then(logoutSuccess, logoutError)
        };
        function logoutSuccess(respons) {
            $scope.value = {status: false};
            $scope.url.url = "/static/template/news.html";
        }

        function logoutError(respons) {
            $scope.value = {status: true}
        }

        $scope.loginHeader = function () {

            $location.path("login");

        };


        $scope.openPage = function (page) {
            // $scope.url.url = page;
            $location.path(page);
            console.log("path" + $location.path());
            //  var path = page.substring(8, 13);
            /* $location.path(path);*/
        }
        $scope.url = {
            header: "/static/header.html",
            footer: "/static/footer.html",
            url: "/static/template/news.html"
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
        $scope.aboutMe = function () {

            var req = {
                method: 'GET',
                url: '/api/v.1/user/auth/userInfo',
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http(req)
                .then(function (response) {
                    $scope.myUserInfo = response.data;
                    $scope.url.url = "/static/aboutMe.html"

                }, function () {
                    alert("все плохо");
                    $scope.result = "пользователь с таким именем или email уже существует";
                    /*$scope.status = {};*/
                });

        }
        $scope.changeMyUserInfo = function () {
            $scope.url.url = "/static/changeAboutMe.html";

        };
        $scope.saveMyUserInfo = function (item) {
            var req = {
                method: 'PUT',
                url: '/api/v.1/user/auth/userInfo',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    "userId": item.userId,
                    "userInfoId": item.userInfoId,
                    "name": item.name,
                    "description": item.description,
                    "age": item.age,
                    "height": item.height,
                    "weight": item.weight,
                    "experience": item.experience,
                    "linkImage": "link"
                }
            };

            $http(req)
                .then(function (response) {
                    $scope.url.url = "/static/aboutMe.html";
                    $scope.myUserInfo = item;

                }, function () {
                    alert("все плохо");
                    $scope.result = "пользователь с таким именем или email уже существует";
                    /*$scope.status = {};*/
                });
        };

        $scope.backToMyInfo = function () {
            $scope.url.url = "/static/aboutMe.html";
        };

    }
);

upump.controller("MyWorkoutCtrl", function ($scope, $http, $route, $routeParams, $location) {
    if ($scope.value.status) {
        $location.path("myworkouts")
    } else  $location.path("login");
    var getItems = function () {
        var promise = $http.get("/api/v.1/user/auth/workout");
        promise.then(fulfilled, rejected)
    };

    function fulfilled(response) {
        $scope.controllerValue.myWorkouts = response.data;//добавление в скоп для работы в будущем
    }

    function rejected(respons) {
        return {};
    }

    getItems();

    $scope.cancel = function () {
        $scope.createOrEditStatus = false;
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
            .then(function (response) {
                $scope.controllerValue.myExercises = response.data;
                $location.path("myexercises");

            }, function () {
                alert("все плохо");
            });
    }

    $scope.deleteWorkout = function (item) {
        var req = {
                method: 'DELETE',
                url: '/api/v.1/workout/' + item.workoutId,
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            ;
        $http(req)
            .then(function () {
                $scope.controllerValue.myWorkouts.workoutXML.splice($scope.controllerValue.myWorkouts.workoutXML.indexOf(item), 1);
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    };

    $scope.createOrEditWorkout = function (item) {
        $scope.editWorkout = item ? angular.copy(item) : {};
        $scope.createOrEditStatus = true;
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
                $scope.controllerValue.myWorkouts.workoutXML.push(newItem);
                $scope.createOrEditStatus = false;
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

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
                for (var i = 0; i < $scope.controllerValue.myWorkouts.workoutXML.length; i++) {
                    if ($scope.controllerValue.myWorkouts.workoutXML[i].workoutId == item.workoutId) {
                        $scope.controllerValue.myWorkouts.workoutXML[i].name = item.name;
                        break;
                    }
                }
                $scope.createOrEditStatus = false;

                $location.path("myworkouts");
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    $scope.getPageAuthor = function (item) {
        var req = {
            method: 'GET',
            url: '/api/v.1/user/' + item.userId + "/userInfo",
        };
        $http(req)
            .then(function (response) {
                $scope.controllerValue.userInfo = response.data;
                $location.path("userInfo");
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }


    $scope.rememberPass = function () {
        $scope.email = {};
        $scope.urlView.url = "/static/template/forgetPass.html";
    }
    $scope.sendRequestRememberPass = function (item) {

        var req = {
            method: 'POST',
            url: '/api/v.1/user/rememberPass',
            data: {
                "email": item.email
            }

        };
        $http(req)
            .then(function () {
                $scope.rememberPassResult = "на Ваш емейл будет отправлено письмо с инструкцией";
            }, function () {
                $scope.rememberPassResult = "пользователя с таким емейл не существует";
            });
    }

    $scope.abortRequestRememberPass = function () {
        $scope.urlView.url = "/static/template/loginForm.html";

    }

});

upump.controller("MyWorkoutCtrl1", function ($scope, $http, $route, $routeParams, $location) {
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


    /*  $scope.createOrEditWorkout = function (item) {
     $scope.editWorkout = item ? angular.copy(item) : {};
     $scope.urlView.url = "/static/template/editNameWorkout.html";
     };*/

    /* $scope.saveWorkout = function (item) {
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
     /!*$scope.status = {};*!/
     });
     }
     */
    /*  $scope.deleteWorkout = function (item) {
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
     /!*$scope.status = {};*!/
     });
     };
     */
    /*function updateWorkout(item) {
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
     /!*$scope.status = {};*!/
     });
     }*/

    /*var getItemsWorkout = function () {
     var promise = $http.get("/api/v.1/user/auth/workout");
     promise.then(fulfilled, rejected)
     };

     function fulfilled(respons) {

     $scope.items = respons.data;
     }

     function rejected(respons) {
     return {};
     }*/

    /* $scope.openMyWorkout = function (item) {
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
     $scope.urlView.url = "/static/template/myExerciseTemplate.html";
     }, function () {
     alert("все плохо");
     });
     }*/
    /*$scope.openWorkout = function (item) {
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
     $scope.authorWorkout = response.data;
     $scope.urlView.url = "/static/template/exerciseTemplate.html";
     }, function () {
     alert("все плохо");
     /!*$scope.status = {};*!/
     });
     }*/

    /* $scope.backToWorkouts = function () {
     $scope.urlView.url = "/static/template/myWorkoutTemplate.html";

     }*/


//for exercise
    /*  $scope.backToExercise = function (item) {
     $scope.urlView.url = "/static/template/myExerciseTemplate.html";
     }

     $scope.backToTry = function (item) {
     $scope.urlView.url = "/static/template/myTryTemplate.html";
     }*/


    /*var getItemsExercise = function (item) {
     var promise = $http.get("/api/v.1/workout/" + item.workoutId + "/exercise");
     promise.then(getExerciseSuccess, getExerciseError)
     };*/

    /*  function getExerciseSuccess(respons) {
     $scope.workout = respons.data;
     }

     function getExerciseError(respons) {
     return {};
     }*/


    /*$scope.createOrEditExercise = function (item) {
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
     /!*$scope.status = {};*!/
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
     /!*$scope.status = {};*!/
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
     /!*$scope.status = {};*!/
     });
     };*/
    //Exercise
    /*  $scope.openExercise = function (item) {
     //getItemsTry(item);
     $scope.try = item;
     $scope.urlView.url = "/static/template/myTryTemplate.html";
     };*/


    ///try
    /*$scope.createOrEditTry = function (item) {
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
     /!*$scope.status = {};*!/
     });
     }
     */
    /*  $scope.copy = function (item) {
     var req = {
     method: 'POST',
     url: '/api/v.1/workout/' + item.workoutId + "/copy",
     headers: {
     'Content-Type': 'application/json'
     }
     };
     $http(req)
     .then(function (response) {
     var data = response.data;
     var newWorkout = {
     "userId": data.userId,
     "workoutId": data.workoutId,
     "author": data.author,
     "name": data.name,
     "rate": data.rate
     };
     alert(data);
     if ($scope.items != null) {
     alert(true);
     $scope.items.workoutXML.push(newWorkout);
     }
     alert($scope.userInfo.userId + " " + newWorkout.userId);
     if ($scope.userInfo.userId == newWorkout.userId) {
     $scope.itemsAuthor.workoutXML.push(newWorkout);
     }
     }, function () {
     alert("все плохо");
     /!*$scope.status = {};*!/
     });


     }*/

    /* function updateTry(item) {
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
     /!*$scope.status = {};*!/
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
     $scope.workout.exerciseXMLList[i].list.splice($scope.workout.exerciseXMLList[i].list.indexOf(item), 1);
     }
     }
     }, function () {
     alert("все плохо");
     /!*$scope.status = {};*!/
     });
     };*/

    /* $scope.done = function (item) {
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
     /!*$scope.status = {};*!/
     });


     }

     /!* var getItemsTry = function (item) {
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
     };*!/*/


    getItemsWorkout();
//info

    /*
     $scope.openAuthorWorkout = function (item) {
     var req = {
     method: 'GET',
     url: '/api/v.1/user/' + item.userId,
     headers: {
     'Content-Type': 'application/json'
     }
     };
     $http(req)
     .then(function (response) {
     $scope.itemsAuthor = response.data;
     $scope.urlView.url = "/static/template/workoutTemplate.html";

     }, function () {
     alert("все плохо");
     /!*$scope.status = {};*!/
     });

     }*/

    /*
     $scope.backToInfo = function () {
     $scope.urlView.url = "/static/authorPage.html";

     }*/

})
;
upump.controller("RegistrationCTRL", function ($scope, $http) {
    $scope.registrationUser = function (item) {
        var req = {
            method: 'POST',
            url: '/api/v.1/user/',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                "login": item.login,
                "password": item.password,
                "email": item.email
            }
        };
        $http(req)
            .then(function (response) {
                $scope.result = "для завершения регистрации на Ваш email отправлено письмо";

            }, function () {
                /* alert("все плохо");*/
                $scope.result = "пользователь с таким именем или email уже существует";
                /*$scope.status = {};*/
            });


    };


});

upump.controller("BestCtrl", function ($scope, $http, $location) {
    var itemsBest = function () {
        var req = {
            method: 'GET',
            url: '/api/v.1/workout/best',
            headers: {
                'Content-Type': 'application/json'
            }

        };
        $http(req)
            .then(function (response) {
                $scope.itemsBest = response.data

            }, function () {
                alert("все плохо");
            });

    };

    itemsBest();

    $scope.getPageAuthor = function (item) {
        var req = {
            method: 'GET',
            url: '/api/v.1/user/' + item.userId + "/userInfo",
        };
        $http(req)
            .then(function (response) {
                $scope.controllerValue.userInfo = response.data;
                $location.path("userInfo");
            }, function () {
                alert("все плохо");
                /*$scope.status = {};*/
            });
    }

    $scope.openWorkout = function (item) {
        var req = {
            method: 'GET',
            url: '/api/v.1/workout/' + item.workoutId + '/exercise'
        };
        $http(req)
            .then(function (response) {
                $scope.controllerValue.authorExercises = response.data;
                $location.path('exercises');

            }, function () {
                alert('все плохо');
                /*$scope.status = {};*/
            });
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
