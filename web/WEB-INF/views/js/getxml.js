function getXmlAllWorkouts() {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
       /* url: "getXmlAllWorkouts",*/
       url: "/users/user/log",
        type: "GET",
        cache: false,
        scriptCharset: "UTF-8",
        dataType: "xml",
        error: function (request) {
             if(request.status==401);
            window.location.href = "/login";
        },

        success: function (xml) {
            $("#list").empty();
            var myUserIs= $(xml).find("userXML").find("myUserId:first").text();
            $(".myid").attr("id", myUserIs);
            $(xml).find("workoutXML").each(function () {
                var userId = $(this).find("userId:first").text();
                var name = $(this).find("workoutName:first").text();
                var workoutId = $(this).find("workoutId:first").text();
                var author = $(this).find("author:first").text();
                var element =
                    "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + workoutId + "'class='col-md-9 cta-contents cta-button'>" +
                    "<div><h1 id='"+workoutId+"' class='update cta-title'>" + name + "</h1></div><div><button id='" + userId + "' class='btnAuthor btn btn-xs btn-primary'>автор: " + author +
                    "</button></div>" +
                    "</div>" +
                    "<div id='"+workoutId+"' tag='"+name+"'name='workout' class='col-md-3 cta-button'>" +
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
        //url: "getXmlWorkout?id=" + id,
        url: "/users/user/workout/" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("exerciseXML").each(function () {
                var elementRepeat = "<div class=''><div class='cta-button'>";
                var name = $(this).find("exerciseName:first").text();
                var id = $(this).find("exerciseId:first").text();
                $(this).find("tryXML").each(function () {
                    var idTry = $(this).find("tryId:first").text();
                    var done = $(this).find("done:first").text();
                    var weight = $(this).find("weight:first").text();
                    var repeat = $(this).find("repeat:first").text();
                    var classBtnDone="btn-primary";
                    if(done=="true"){
                        classBtnDone="btn-danger";
                    }
                    var elementButton = "<button id='" + idTry + "' class='color-change btn "+classBtnDone+"'>" + weight + "/" + repeat + "</>";
                    elementRepeat += elementButton;
                });
                elementRepeat += "</div></div>";
                var element = "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div  id='" + id + "' class='col-md-9 cta-contents cta-button'>" +
                    "<h1 id='name' class='update cta-title'>" + name + "</h1>" + elementRepeat + "</div>" +
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
function getXmlThisExercise(id) {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlExercise?id=" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("tryXML").each(function () {
                var weight = $(this).find("weight:first").text();
                var id = $(this).find("tryId:first").text();
                var repeat = $(this).find("repeat:first").text();
                var element = "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + id + "' name ='try' class='col-md-9 cta-contents cta-button'>" +
                    "<h2 id='weight' class=' update update cta-title'> Вес: " + weight + "</h2>" +
                    "<h2 id='repeat' class='update update cta-title'> Повторений: " + repeat + "</h2>" +
                    "</div>" +
                    "<div id='" + id + "' name ='try' class='try col-md-3 cta-button'>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>";
                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
            spinner.stop();
        }
    });
}
function changeColorRepeat() {
    var color = $(this).attr("class");
    var id = $(this).attr("id");
    setStatusDoneForTry(id);
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

