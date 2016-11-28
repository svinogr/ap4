function createPagination() {
   pages = Math.ceil($("#pagination").attr("count") / qantityRow);
    
    if (pages ==1) {
        $("#firstPage").addClass("hiden")
        $("#middle").addClass("hiden");
        $("#right").addClass("hiden");
        $("#lastPage").addClass("hiden");
        $(".points").addClass("hiden");
    }
    if (pages == 2) {
        $("#firstPage").addClass("hiden")
        $("#right").addClass("hiden");
        $("#lastPage").addClass("hiden");
        $(".points").addClass("hiden");
    }
    if (pages > 3){
        $("#firstPage").text(1);
        $("#left").text(1);
        $("#middle").text(2);
        $("#right").text(3);
        $("#lastPage").text(pages);
    }




        getXmlAllWorkoutsBest();
}
function best() {
    getXmlAllWorkoutsBest();
    tryLevel = false;
    exerciseLevel = false;
    workoutLevel = true;
}
function all() {
    getXmlAllWorkoutsBest();
    tryLevel = false;
    exerciseLevel = false;
    workoutLevel = true;
    $("#back").addClass("hiden");

}
function getXmlBypage() {
    var numberPage = $(this).text();
    alert(numberPage);
    getXmlAllWorkoutsBest(+numberPage - 1);
    if (numberPage > 1 && numberPage<pages) {
        $("#left").text(+numberPage - 1);

        $("#middle").text(+numberPage);

        $("#right").text(+numberPage + 1);

    }
    if (numberPage == pages && numberPage>3 ){
        $("#left").text(+numberPage - 2);
        $("#middle").text(+numberPage - 1);
        $("#right").text(+numberPage);
    }
    if(numberPage==1){
        createPagination();
    }
    
    
}
$(document).ready(function () {
    $("#best").text("Готовые");
    $("#back").addClass("hiden")
    $("#best").click(best);
    $("#all").click(all);
    $("#back").click(back);
    createPagination()
    $(".pag").click(getXmlBypage);
});
var workoutLevel = true;
var exerciseLevel = false;
var tryLevel = false;
var workoutId;
var exerciseId;
var titleWorkout;
var titleExercise;
var qantityRow = 20;
var pages;
function back() {
    if (exerciseLevel) {
        getXmlAllWorkoutsBest();
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
function rate() {
    var typeRate = $(this).attr("id");
    var idWorkout = $(this).parent().attr("id");
    var x = new XMLHttpRequest();
    x.open("GET", "rate?id=" + idWorkout + "&rate=" + typeRate, true);
    x.send();
    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            if (x.status == 401) {
                window.location.href = "/login";
            } else getXmlAllWorkoutsBest();
        }
    }
}
function copy() {
    var idWorkout = $(this).parent().attr("id");
    var x = new XMLHttpRequest();
    x.open("GET", "copyWorkout?id=" + idWorkout, true);
    x.send();
    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            if (x.status == 401) {
                window.location.href = "/login";

            }
            if (x.status == 200) {
                alert("скопировано");
                //TODO сделать окно что тренировка скопирована        
            }
            if (x.status == 400) {
                alert("no connection");
            }
        }
    }
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


