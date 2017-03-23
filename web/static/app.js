var upump = angular.module("UPump", ["ngRoute", "ngAnimate", "ui.bootstrap"]);
upump.config(function ($routeProvider, $locationProvider) {
    $routeProvider.when("/news", {templateUrl: "/static/news.html",controller: "NewsCTRL"});
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
    $routeProvider.when("/all", {templateUrl: "/static/all.html",controller: "AllCTRL"});
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
        templateUrl: "/static/template/loginForm.html", controller: "LoginCTRL"
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
    $routeProvider.when("/aboutme", {
        templateUrl: "/static/aboutMe.html",
        controller: "AboutMeCTRL"
    });
    $routeProvider.when("/adminPage", {
        templateUrl: "/static/adminPage.html", controller: "AdminCTRL"
    });
    $routeProvider.when("/mailto", {
        templateUrl: "/static/mailto.html",
        controller: "MailToCTRL"
    });

    $locationProvider.html5Mode(true);

});
upump.controller("NewsCTRL", function ($scope, $http) {

    var getTotalPage = function () {
        var req = {
            method: 'GET',
            url: '/api/v.1/post/quantity',
        };
        $http(req)
            .then(function (response) {
                $scope.totalQuantity = response.data;
                $scope.totalPages = Math.ceil(response.data);

            }, function () {
                $scope.totalPages=0;
            });
    };
    getTotalPage();
    $scope.filteredTodos = [];
    $scope.itemsPerPage = 20;
    $scope.currentPage = 1;
    $scope.makeTodos = function () {
        $scope.todos = [];
        var req = {
            method: 'GET',
            url: '/api/v.1/post/from/' + ($scope.currentPage - 1) * 20,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http(req)
            .then(function (response) {
                $scope.todos = response.data;

            }, function () {
            });
    };
    $scope.figureOutTodosToDisplay = function () {
        $scope.makeTodos();
    };
    $scope.makeTodos();
    $scope.figureOutTodosToDisplay();
    $scope.pageChanged = function () {
        $scope.figureOutTodosToDisplay();
    };


});
upump.controller("MailToCTRL", function ($scope, $http) {
    $scope.resultSend = null;
    $scope.send = function (item) {
        var req = {
            method: 'POST',
            url: '/api/v.1/mailto',
            data: {
                "from": item.from,
                "body": item.body
            }
        };
        $http(req)
            .then(function (response) {
                $scope.resultSend = "сообщение отправлено";
            }, function (error) {
                if (error.status == 400) {
                    $scope.resultSend = "сообщение не отправлено";
                }
            });


    }
});
upump.controller("AdminCTRL", function ($scope, $http, $location) {
    $scope.createOrEditStatus = false;
    $scope.create = function () {
        $scope.createOrEditStatus = true;
    };
    $scope.editPost = function (item) {
        $scope.createOrEditStatus = true;
        $scope.post = item;

    };

    $scope.cancel = function () {
        $scope.createOrEditStatus = false;
    };
    $scope.createOrEdit = function (item) {
        if (angular.isDefined(item.id)) {
            updatePost(item);
        } else createPost(item);
    };

    function createPost(item) {

        var req = {
                method: 'POST',
                url: '/api/v.1/user/auth/post/',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    "title": item.title,
                    "description": item.description
                }
            }
            ;
        $http(req)
            .then(function (respons) {
                var data = respons.data;
                var newPost = {
                    "id": data.id,
                    "title": data.title,
                    "description": data.description,
                    "date": item.date
                };
                $scope.todos.postXMLs.push(newPost);
                $scope.resultSend = "пост создан";
                $scope.createOrEditStatus = false;
            }, function () {
                if (error.status == 401 || error.status == 403) {
                    $scope.resultSend = "пост не создан";
                    $location.path("login");
                }
            });
    }

    function updatePost(item) {
        var req = {
                method: 'PUT',
                url: '/api/v.1/post/' + item.id,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {
                    "title": item.title,
                    "description": item.description
                }
            }
            ;
        $http(req)
            .then(function () {
                for (var i = 0; i < $scope.todos.postXMLs.length; i++) {
                    if ($scope.todos.postXMLs[i].id == item.id) {
                        $scope.todos.postXMLs[i] = item;
                        break;
                    }
                }
                $scope.createOrEditStatus = false;
            }, function () {
                $scope.resultPost="пост не создан";
            });
    }


    $scope.deletePost = function (item) {
        var req = {
            method: 'DELETE',
            url: '/api/v.1/post/' + item.id
        };
        $http(req)
            .then(function (response) {
                for (var i = 0; i < $scope.todos.postXMLs.length; i++) {
                    if ($scope.todos.postXMLs[i].id == item.id) {
                        $scope.todos.postXMLs.splice($scope.todos.postXMLs.indexOf(item), 1);
                        break;
                    }
                }

            }, function (error) {
                if (error.status == 401 || error.status == 403) {
                    $location.path("login");
                }
            });


    };
    $scope.resultSend = null;
    $scope.showAll = false;
    $scope.titleBtn = "показать новости";

    $scope.showAllNews = function () {
        if (!$scope.showAll) {
            $scope.showAll = true;
            $scope.titleBtn = "скрыть новости";
            $scope.createOrEditStatus = false;
        } else {
            $scope.showAll = false;
            $scope.titleBtn = "показать новости"
        }


    };
    var getTotalPage = function () {
        var req = {
            method: 'GET',
            url: '/api/v.1/post/quantity',
        };
        $http(req)
            .then(function (response) {
                $scope.totalQuantity = response.data;
                $scope.totalPages = Math.ceil(response.data);

            }, function () {
                $scope.totalPages=0;
            });
    };
    getTotalPage();
    $scope.filteredTodos = [];
    $scope.itemsPerPage = 20;
    $scope.currentPage = 1;
    $scope.makeTodos = function () {
        $scope.todos = [];
        var req = {
            method: 'GET',
            url: '/api/v.1/post/from/' + ($scope.currentPage - 1) * 20,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http(req)
            .then(function (response) {
                $scope.todos = response.data;

            }, function () {
            });
    };
    $scope.figureOutTodosToDisplay = function () {
        $scope.makeTodos();
    };
    $scope.makeTodos();
    $scope.figureOutTodosToDisplay();
    $scope.pageChanged = function () {
        $scope.figureOutTodosToDisplay();
    };


});

upump.controller("AllCTRL", function ($scope, $http, $location) {
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
            });

    };

    $scope.rate = function (item, rate) {
        var req = {
            method: 'PUT',
            url: '/api/v.1/workout/' + item.workoutId + "/rate",
            data: {"rate": rate}
        };
        $http(req)
            .then(function (response) {
                var newItem = response.data;
                for (var i = 0; i < $scope.todos.workoutXML.length; i++) {
                    if ($scope.todos.workoutXML[i].workoutId == newItem.workoutId) {
                        $scope.todos.workoutXML[i] = newItem;
                    }
                }
            }, function (error) {
                if (error.status == 401) {
                    $location.path("login");
                }
            });

    };

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
            });
    };


    //pagination
    var getTotalPage = function () {
        var req = {
            method: 'GET',
            url: '/api/v.1/workout/quantity'
        };
        $http(req)
            .then(function (response) {
                $scope.totalQuantity = response.data;
                $scope.totalPages = Math.ceil(response.data);

            }, function () {
                $scope.totalPages=0;
            });
    };
    getTotalPage();
    $scope.filteredTodos = [];
    $scope.itemsPerPage = 20;
    $scope.currentPage = 1;
    $scope.makeTodos = function () {
        $scope.todos = [];
        var req = {
            method: 'GET',
            url: '/api/v.1/user/from/' + ($scope.currentPage - 1) * 20,
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http(req)
            .then(function (response) {
                $scope.todos = response.data;

            }, function () {
            });
    };
    $scope.figureOutTodosToDisplay = function () {
        $scope.makeTodos();
    };
    $scope.makeTodos();
    $scope.figureOutTodosToDisplay();
    $scope.pageChanged = function () {
        $scope.figureOutTodosToDisplay();
    };
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
            });


    }
});

upump.controller("LoginCTRL", function ($scope, $http, $location) {

    $scope.login = function (user) {
        var getUserInfo = function () {
            var req = {
                method: 'GET',
                url: 'api/v.1/user/auth/userInfo'

            };
            $http(req)
                .then(function (response) {
                    $scope.controllerValue.myUserInfo = response.data;

                }, function () {
                    $scope.controllerValue.myUserInfo = {};
                });
        };
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
                adminStatus();
                getUserInfo();
                $location.path("myworkouts");
            }, function () {
                $scope.status = {};
            });
    };
    var adminStatus = function () {
        var req = {
                method: 'GET',
                url: '/adminstatus'

            }
            ;
        $http(req)
            .then(function () {
                $scope.controllerValue.adminStatus = true;

            }, function () {
                $scope.controllerValue.adminStatus = false;
            });
    };


    $scope.rememberPass = function () {
        $scope.rememberPassStatus= true;
       
    };
    $scope.cancel = function () {
        $scope.rememberPassStatus= false;
    };
    
    
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
    };

    $scope.abortRequestRememberPass = function () {
        $scope.urlView.url = "/static/template/loginForm.html";

    }


})
;

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
            });

    };
    $scope.rate = function (item, rate) {
        var req = {
            method: 'PUT',
            url: '/api/v.1/workout/' + item.workoutId + "/rate",
            data: {"rate": rate}
        };
        $http(req)
            .then(function (response) {
                var newItem = response.data;
                for (var i = 0; i < $scope.controllerValue.authorWorkouts.workoutXML.length; i++) {
                    if ($scope.controllerValue.authorWorkouts.workoutXML[i].workoutId == newItem.workoutId) {
                        $scope.controllerValue.authorWorkouts.workoutXML[i] = newItem;
                    }
                }
            }, function (error) {
                if (error.status == 401) {
                    $location.path("login");
                }
            });

    }


});

upump.controller("ExercisesCTRL", function ($scope, $http, $location) {
    $scope.copy = function (item) {
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
                });


        } else $location.path("login");
    }
});

upump.controller("MyTryCTRL", function ($scope, $http) {
    $scope.cancel = function () {
        $scope.createOrEditStatus = false;
    };
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
        } else if (!angular.isDefined(item.for)) {
            createTry(item);
        } else {
            for (var i = 0; i < item.for; i++) {
                createTry(item);
            }
        }
    };

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
                    if ($scope.controllerValue.myExercises.exerciseXMLList[i].exerciseId == newItem.exerciseId) {
                        $scope.controllerValue.myExercises.exerciseXMLList[i].list.push(newItem);
                        break;
                    }
                }
                $scope.controllerValue.myTryies.list.push(newItem);
                $scope.createOrEditStatus = false;
            }, function () {
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
            }, function () {
            });
    };


})
;

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
    };

    $scope.saveExercise = function (item) {
        if (angular.isDefined(item.exerciseId)) {
            updateExercise(item)
        } else createExercise(item);
    };

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
                    "list": []
                };
                $scope.controllerValue.myExercises.exerciseXMLList.push(newItem);
                $scope.createOrEditStatus = false;
            }, function () {
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
            });


    };

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
            });
    }
});

upump.controller("AboutMeCTRL", function ($scope, $http) {
    $scope.cancel = function () {
        $scope.changeInfo = false;
    };
    $scope.changeInfo = false;
    var getUserInfo = function () {
        var req = {
            method: 'GET',
            url: 'api/v.1/user/auth/userInfo'

        };
        $http(req)
            .then(function (response) {
                $scope.controllerValue.myUserInfo = response.data;

            }, function () {
                $scope.controllerValue.myUserInfo = {};
            });
    };
    $scope.changeMyUserInfo = function () {
        $scope.changeInfo = true;

    };
    if ($scope.controllerValue.myUserInfo == null) {
        getUserInfo();
    }
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
            .then(function () {
                $scope.changeInfo = false;
                $scope.controllerValue.myUserInfo = item;

            }, function () {
            });
    };
});

upump.controller("UrlCtrl", function ($scope, $http, $route, $routeParams, $location) {
        $scope.controllerValue = {};
        var adminStatus = function () {
            var req = {
                    method: 'GET',
                    url: '/adminstatus'

                }
                ;
            $http(req)
                .then(function () {
                    $scope.controllerValue.adminStatus = true;

                }, function () {
                    $scope.controllerValue.adminStatus = false;
                });
        };
        if ($scope.controllerValue.adminStatus == null) {
            adminStatus();
        }

        $scope.backBtn = function () {
            window.history.back();
        };

        $scope.controllerValue = {};

        console.log("path" + $location.path());
        console.log($location.url());
        $scope.acceptRegistration = function (item) {

            $http.get(item);
        };

        $scope.logoutHeader = function () {
            var promise = $http.get("/logout");
            promise.then(logoutSuccess, logoutError)
        };

        function logoutSuccess(respons) {
            $scope.value = {status: false};
            $scope.controllerValue.adminStatus = false;
            $location.path("login");
        }

        function logoutError(respons) {
            $scope.value = {status: true}
        }

        $scope.loginHeader = function () {

            $location.path("login");

        };

        $scope.openPage = function (page) {
            $location.path(page);
            console.log("path" + $location.path());

        };
        $scope.openPage("news");


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
                    $scope.result = "пользователь с таким именем или email уже существует";
                    /*$scope.status = {};*/
                });

        };
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
                    $scope.result = "пользователь с таким именем или email уже существует";
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
        $location.path("login");
    }

    getItems();

    $scope.cancel = function () {
        $scope.createOrEditStatus = false;
    };

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

            });
    };

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
    };

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
            });
    };


});
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
                $scope.result = "пользователь с таким именем или email уже существует";
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
            });
    };

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
            });
    };
    $scope.rate = function (item, rate) {
        var req = {
            method: 'PUT',
            url: '/api/v.1/workout/' + item.workoutId + "/rate",
            data: {"rate": rate}
        };
        $http(req)
            .then(function (response) {
                var newItem = response.data;
                for (var i = 0; i < $scope.itemsBest.workoutXML.length; i++) {
                    if ($scope.itemsBest.workoutXML[i].workoutId == newItem.workoutId) {
                        $scope.itemsBest.workoutXML[i] = newItem;
                    }
                }
            }, function (error) {
                if (error.status == 401) {
                    $location.path("login");
                }
            });

    }

})
;
