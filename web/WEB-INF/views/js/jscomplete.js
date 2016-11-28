function best() {
    getXmlAllWorkoutsComplete();
    tryLevel = false;
    exerciseLevel = false;
    workoutLevel = true;
}
function all() {
    getXmlAllWorkoutsAll();
    tryLevel = false;
    exerciseLevel = false;
    workoutLevel = true;
    $("#back").addClass("hiden");
}
$(document).ready(function () {
    $("#best").text("Готовые");
    $("#back").addClass("hiden")
    $("#best").click(best);
    $("#all").click(all);
    $("#back").click(back);
    getXmlAllWorkoutsComplete();
});
var workoutLevel = true;
var exerciseLevel = false;
var tryLevel = false;
var workoutId;
var exerciseId;
var titleWorkout;
var titleExercise;

function back() {
    if (exerciseLevel) {
        getXmlAllWorkoutsComplete();
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

