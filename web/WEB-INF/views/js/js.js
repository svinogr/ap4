
$(document).ready(function () {
    $("#add").click(add);
    $("#back").click(back);
    getXmlAllWorkouts();
});
var workoutLevel = true;
var exerciseLevel = false;
var tryLevel = false;
var workoutId;
var exerciseId;
var titleWorkout;
var titleExercise;

function add() {
    alert(workoutLevel);
    alert(exerciseLevel);
    alert(tryLevel);
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
    alert(workoutLevel);
    alert(exerciseLevel);
    alert(tryLevel);
    if (exerciseLevel) {
        getXmlAllWorkouts();
        $("#topTitle").text("List of Workouts");
    }
    if (tryLevel) {
        getXmlThisWorkout(workoutId);
        tryLevel = false;
        exerciseLevel = true;
        workoutLevel = false;
        $("#topTitle").text("Name of workout is "+titleWorkout);
    }
}
function edit() {
    var typeOfEdit = $(this).parent().attr("class");
    if (typeOfEdit == "workout") {
        workoutLevel = false;
        exerciseLevel = true;
        tryLevel = false;
        alert("this is " + typeOfEdit);
        workoutId = $(this).parent().attr("id");
        getXmlThisWorkout(workoutId);
        titleWorkout = $(this).parent().attr("name");
        $("#topTitle").text("Name of workout is "+titleWorkout);
    }
    if (typeOfEdit == "exercise") {
        workoutLevel = false;
        exerciseLevel = false;
        tryLevel = true;
        alert("this is " + typeOfEdit);
        exerciseId = $(this).parent().attr("id");
        getXmlThisExercise(exerciseId);
        titleExercise = $(this).parent().attr("name");
        $("#topTitle").text("Name of exercise is "+titleExercise);
    }
}
function addNewWorkout() {
    var nameWorkout = prompt("Write name of Workout");

    if (nameWorkout != null) {
        var x = new XMLHttpRequest();
        x.open("GET", "addNewWorkout?name=" + nameWorkout, true);
        x.send();
        x.onreadystatechange = function () {
            if (x.readyState == 4) {

                if (x.status == 200) {
                    getXmlAllWorkouts();
                } else alert("no connection");
            }
        }

    }
}
function addNewExercise() {
    {
        var name = prompt("Write name of Exercise");
        if (name != null) {
            var x = new XMLHttpRequest();
            x.open("GET", "addNewExercise?id=" + workoutId + "&name=" + name, true);
            x.send();
            x.onreadystatechange = function () {
                if (x.readyState == 4) {
                    if (x.status == 200) {
                        getXmlThisWorkout(workoutId);

                    } else alert("no connection");
                }
            }
        }
    }
}
function addNewRepeat() {

    var weight = prompt("Weight");
    var repeat = prompt("Repeat");
    if (weight != null && repeat != null) {
        var x = new XMLHttpRequest();
        x.open("GET", "addNewTry?id=" + exerciseId + "&weight=" + weight + "&repeat=" + repeat, true);
        x.send();
        x.onreadystatechange = function () {
            if (x.readyState == 4) {
                if (x.status == 200) {
                    getXmlThisExercise(exerciseId);
                } else alert("no connection");
            }
        }
    }

}
function deleting(){
    var id = $(this).parent().attr("id");
    alert(id);
    alert(workoutLevel);
    alert(exerciseLevel);
    alert(tryLevel);
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
function deletingTry(id){
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
