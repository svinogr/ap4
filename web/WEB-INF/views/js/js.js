$(document).ready(function () {
    $("#add").text("Добавить новую тренировку");
    $("#back").addClass("hiden")
    $("#add").click(add);
    $("#back").click(back);
    getXmlAllWorkouts();
    function hello() {
        alert("hello")
    }
});
var workoutLevel = true;
var exerciseLevel = false;
var tryLevel = false;
var workoutId;
var exerciseId;
var titleWorkout;
var titleExercise;
var updateVar;

function add() {
    if (workoutLevel) {
        addNewWorkout();
    }
    if (exerciseLevel) {
        addNewExercise();
    }
    if (tryLevel) {
        addNewRepeat();
    }
}
function back() {
    if (exerciseLevel) {
        getXmlAllWorkouts();
        tryLevel = false;
        exerciseLevel = false;
        workoutLevel = true;
        $("#add").text("Добавить новую тренировку");
        $("#topTitle").text("Список тренировок");
        $("#back").addClass("hiden");
        $("#middleTitle").removeClass("unhiden");
        $("#middleTitle").addClass("hiden");
    }
    if (tryLevel) {
        getXmlThisWorkout(workoutId);
        tryLevel = false;
        exerciseLevel = true;
        workoutLevel = false;
        $("#add").text("Добавить новое упражнение");
        $("#topTitle").text("Нзвание тренировки: " + titleWorkout);
        $("#middleTitle").removeClass("hiden");
        $("#middleTitle").addClass("unhiden");
        $("#bottomTitle").removeClass("unhiden");
        $("#bottomTitle").addClass("hiden");
    }
}
function edit() {
    var typeOfEdit = $(this).parent().attr("name");
    if (typeOfEdit == "workout") {
        $("#add").text("Добавить новое упражнение");
        $("#back").removeClass("hiden");
        $("#back").addClass("unhiden");
        $("#middleTitle").removeClass("hiden");
        $("#middleTitle").addClass("unhiden");
        workoutLevel = false;
        exerciseLevel = true;
        tryLevel = false;
        workoutId = $(this).parent().attr("id");
        getXmlThisWorkout(workoutId);
        titleWorkout = $(this).parent().attr("tag");
        $("#topTitle").text("Текущая тренировка: " + titleWorkout);
    }
    if (typeOfEdit == "exercise") {
        $("#add").text("Добавить новый подход");
        $("#middleTitle").removeClass("unhiden");
        $("#middleTitle").addClass("hiden");
        $("#bottomTitle").removeClass("hiden");
        $("#bottomTitle").addClass("unhiden");
        workoutLevel = false;
        exerciseLevel = false;
        tryLevel = true;
        exerciseId = $(this).parent().attr("id");
        getXmlThisExercise(exerciseId);
        titleExercise = $(this).parent().attr("tag");
        $("#topTitle").text("Текущее упражнение: " + titleExercise);
    }
}
function addNewWorkout() {
    $(".modal-title").empty();
    $(".modal-title").text("Введите имя новой тренировки");
    $(".modal-body").empty();
    $(".modal-body").append("<span>имя тренировки:</span>");
    $(".modal-body").append("<input type='text' id='var'/>");
    $("#myModalBox").modal('show');
    $("#save").unbind();
    $("#save").click(function () {
        var nameWorkout = save();
        if (nameWorkout != "") {
            var x = new XMLHttpRequest();
            x.open("GET", "addNewWorkout?name=" + encodeURIComponent(nameWorkout), true);
                    x.send();
                    x.onreadystatechange = function () {
                        if (x.readyState == 4) {
                            if (x.status == 200) {
                                getXmlAllWorkouts();
                            } else alert("no connection");
                }
            }
        }
    });
}
function save() {
    var name = $("#var").val();
    $("#myModalBox").modal('hide');
    return name;
}

function addNewExercise() {
    $(".modal-body").empty();
    $("#modal-title").text("Введите имя нового упражнения");
    $(".modal-body").append("<span>имя упражнения:</span>");
    $(".modal-body").append("<input type='text' id='var'/>");
    $("#myModalBox").modal('show');
    $("#save").unbind();
    $("#save").click(function () {
        var name = save();
        if (name != "") {
            var x = new XMLHttpRequest();
            x.open("GET", "addNewExercise?id=" + workoutId + "&name=" + encodeURIComponent(name), true);
            x.send();
            x.onreadystatechange = function () {
                if (x.readyState == 4) {
                    if (x.status == 200) {
                        getXmlThisWorkout(workoutId);

                    } else alert("no connection");
                }
            }
        }
    });
}

function addNewRepeat() {
    var weight;
    var repeat;
    var tries;
    $(".modal-body").empty();
    $("#modal-title").text("Введите параметры подхода");
    $(".modal-body").append("<span>вес:</span>");
    $(".modal-body").append("<input type='text'id='var'/>");
    $(".modal-body").append("<span>кол-во повторений:</span>");
    $(".modal-body").append("<input type='text' id='var2'/>");
    $(".modal-body").append("<span>кол-во подходов:</span>");
    $(".modal-body").append("<input type='text'id='var3' value='1'/>");

    $("#myModalBox").modal('show');
    $("#save").unbind();
    $("#save").click(function () {
        weight = $("#var").val();
        repeat = $("#var2").val();
        tries=$("#var3").val();
        $("#myModalBox").modal('hide');
        if (weight != "" && repeat != "") {
            var x = new XMLHttpRequest();
            x.open("GET", "addNewTry?id=" + exerciseId + "&weight=" + weight + "&repeat=" + repeat+"&tries="+tries, true);
            x.send();
            x.onreadystatechange = function () {
                if (x.readyState == 4) {
                    if (x.status == 200) {
                        getXmlThisExercise(exerciseId);
                    } else alert("no connection");
                }
            }
        }

    });
}

function deleting() {
    var id = $(this).parent().attr("id");
    if (workoutLevel) {
        deletingWorkout(id);
    }
    if (exerciseLevel) {
        deletingExercise(id);
    }
    if (tryLevel) {
        deletingTry(id);
    }
}

function deletingWorkout(id) {
    var x = new XMLHttpRequest();
    x.open("GET", "deleteWorkout?id=" + id, true);
    x.send();
    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            if (x.status == 200) {
                getXmlAllWorkouts()
            } else alert("no connection");
        }
    }
}

function deletingExercise(id) {
    var x = new XMLHttpRequest();
    x.open("GET", "deleteExercise?id=" + id, true);
    x.send();
    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            if (x.status == 200) {
                getXmlThisWorkout(workoutId);
            } else alert("no connection");
        }
    }
}

function deletingTry(id) {
    var x = new XMLHttpRequest();
    x.open("GET", "deleteTry?id=" + id, true);
    x.send();
    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            if (x.status == 200) {
                getXmlThisExercise(exerciseId);
            } else alert("no connection");
        }
    }
}

function update() {
    var id = $(this).parent().attr("id");
    var name = $(this).attr("id");
    if (workoutLevel) {
        var type = "workout"
        updatingWorkout(name, id);
    }
    if (exerciseLevel) {
        var type = "exercise";
        updatingexercise(name, id);
    }
    if (tryLevel) {
        var type = "try";
        updatingTry(name, id);
    }
}

function updatingWorkout(name, id) {
    $("#modal-title").text("Введите новое название");
    $(".modal-body").empty();
    $(".modal-body").append("<span>название тренировки:</span>");
    $(".modal-body").append("<input type='text'id='var'/>");
    $("#myModalBox").modal('show');
    $("#save").unbind();
    $("#save").click(function () {
        var updateVar = save();
        $("#myModalBox").modal('hide');
        if (updateVar != "") {
            var x = new XMLHttpRequest();
            x.open("GET", "updateWorkout?id=" + id + "&name=" + name + "&updateVar=" + encodeURIComponent(updateVar), true);
            x.send();
            x.onreadystatechange = function () {
                if (x.readyState == 4) {
                    if (x.status == 200) {
                        getXmlAllWorkouts()
                    } else alert("no connection");
                }
            }
        }
    });
}

function updatingexercise(name, id) {
    $("#modal-title").text("Введите новое название");
    $(".modal-body").empty();
    $(".modal-body").append("<span>название упражнения:</span>");
    $(".modal-body").append("<input type='text'id='var'/>");
    $("#myModalBox").modal('show');
       $("#save").unbind();
    $("#save").click(function () {
        var updateVar = save();
        $("#myModalBox").modal('hide');
        if (updateVar != "") {
            var x = new XMLHttpRequest();
            x.open("GET", "updateExercise?id=" + id + "&name=" + name + "&updateVar=" + encodeURIComponent(updateVar), true);
            x.send();
            x.onreadystatechange = function () {
                if (x.readyState == 4) {
                    if (x.status == 200) {

                        getXmlThisWorkout(workoutId)

                    } else alert("no connection");
                }
            }
        }
    });
}

function updatingTry(name, id) {
    $("#modal-title").text("Введите новое значение");
    $(".modal-body").empty();
    $(".modal-body").append("<span>новое значение:</span>");
    $(".modal-body").append("<input type='text'id='var'/>");
    $("#myModalBox").modal('show');
    $("#save").unbind();
    $("#save").click(function () {
        var updateVar = save();
        $("#myModalBox").modal('hide');
        if (updateVar != "") {
            var x = new XMLHttpRequest();
            x.open("GET", "updateTry?id=" + id + "&name=" + name + "&updateVar=" + encodeURIComponent(updateVar), true);
            x.send();
            x.onreadystatechange = function () {
                if (x.readyState == 4) {
                    if (x.status == 200) {
                        getXmlThisExercise(exerciseId)
                    } else alert("no connection");
                }
            }
        }
    });
}
function getAuthorWorkout() {
    var nameAuthor = $(this).attr("id");

    var x = new XMLHttpRequest();
    x.open("GET", "/curtainUserRequest", true);
    x.send();
    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            if (x.status == 200) {
                alert(nameAuthor);
                window.location.href = "/curtainUser?id=" + nameAuthor;
            }
            if (x.status == 400) {
                alert("no connection");
            }
        }
    }
}



