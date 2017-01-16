function getXmlAllWorkouts() {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlAllWorkouts",
        cache: false,
        scriptCharset: "UTF-8",
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("workout").each(function () {
                var name = $(this).find("name:last").text();
                var id = $(this).find("id:last").text();
                var author = $(this).find("author:first").text();
                var element =
                    "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + id + "'class='col-md-9 cta-contents cta-button'>" +
                    "<div><h1 id='name' class='update btn btn-primary'>" + name + "</h1></div><div><button id='" + author + "' class='btnAuthor btn btn-xs btn-primary'>автор: " + author +
                    "</button></div>" +
                    "</div>" +

                    "<div tag='" + name + "'name ='workout' class='col-md-3 cta-button' id='" + id + "'name='" + name + "'>" +
                    "<button id='btnEdit' class='btnEdit btn btn-lg btn-block btn-primary'>Открыть</button>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                    "</div>" +

                    "</div>" +

                    "</div>";

                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
            $(".btnAuthor").click(getAuthorWorkout);
            spinner.stop();
        }

    });
}

function getXmlThisWorkout(id) {

    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlWorkout?id=" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("exercise").each(function () {
                var elementRepeat = "<div class=''><div class='cta-button'>";
                var name = $(this).find("name:first").text();
                var id = $(this).find("id:first").text();
                $(this).find("tryes").each(function () {
                    var weight = $(this).find("weight:first").text();
                    var repeat = $(this).find("repeat:first").text();
                    var elementButton = "<button class='color-change btn btn-primary'>" + weight + "/" + repeat + "</>";
                    elementRepeat += elementButton;
                });
                elementRepeat += "</div></div>";
                var element = "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div  id='" + id + "' class='col-md-9 cta-contents cta-button'>" +
                    "<h1 id='name' class='update btn btn-primary'>" + name + "</h1>" + elementRepeat + "</div>" +
                    "<div tag='" + name + "'name ='exercise'class='col-md-3 cta-button' id='" + id + "'name='" + name + "'>" +
                    "<button id='btnEdit' class='btnEdit btn btn-lg btn-block btn-primary'>Редактировать подходы</button>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                    "</div>" +
                    "</div>";
                $("#list").append(element);
            });
            $(".color-change").click(changeColorRepeat);
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
            spinner.stop();
        }
    });
}
/* не используется
function getXmlThisWorkoutOriginal(id) {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlWorkout?id=" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("exercise").each(function () {
                var name = $(this).find("name:first").text();
                var id = $(this).find("id:first").text();
                $(this).find("tryes").each(function () {
                    var weight = $(this).find("weight:first").text();
                    var repeat = $(this).find("repeat:first").text();
                });
               // var elementRepeat = "<div class='container'><div class='row'></div></div>";
                var element = "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div  id='" + id + "' class='col-md-9 cta-contents'>" +
                    "<h1 id='name' class='cta-title update'>" + name + "</h1>" +
                    "</div>" +

                    "<div tag='" + name + "'name ='exercise'class='col-md-3 cta-button' id='" + id + "'name='" + name + "'>" +
                    "<button id='btnEdit' class='btnEdit btn btn-lg btn-block btn-primary'>Редактировать</button>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +

                    "</div>" +

                    "</div>";
                $("#list").append(element);
            });
            $(".color-change").click(changeColorRepeat);
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
            spinner.stop();
        }
    });
}*/
function getXmlThisExercise(id) {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlExercise?id=" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("tryes").each(function () {
                var weight = $(this).find("weight:first").text();
                var id = $(this).find("id:first").text();
                var repeat = $(this).find("repeat:first").text();
                var element = "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + id + "' name ='try' class='col-md-9 cta-contents cta-button'>" +
                    "<h2 id='weight' class=' update update btn btn-primary'> Вес: " + weight + "</h2>" +
                    "<h2 id='repeat' class='update update btn btn-primary'> Повторений: " + repeat + "</h2>" +
                    "</div>" +
                    "<div id='" + id + "' name ='try' class='try col-md-3 cta-button'>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>";
                $("#list").append(element);
            });
            // var buttonReset = "'<div class='row'><div class='btn-group btn-group-justified'><div class='container btn-group'>"
            //     + " <button id='reset' class='reset btn btn-danger'>Сбросить подходы</button></div></div></div>";
            // $("#list").append(buttonReset);
            // $(".reset").click(resetRepeat);
            // $(".color-change").click(changeColorRepeat);
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
            spinner.stop();
        }

    });


}
function changeColorRepeat() {
    var color = $(this).attr("class");
    if (color == "color-change btn btn-primary") {
        $(this).removeClass("btn-primary");
        $(this).addClass("btn-danger");
    } else {
        $(this).removeClass("btn-danger");
        $(this).addClass("btn-primary");
    }
}
function resetRepeat() {
    $(".color-change").removeClass("bs-calltoaction-danger");
    $(".color-change").addClass("bs-calltoaction-primary");
}

