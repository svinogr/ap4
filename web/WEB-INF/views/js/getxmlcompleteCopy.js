function getXmlAllWorkoutsComplete() {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlAllWorkoutsComplete",
        cache: false,
        scriptCharset: "UTF-8",
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("workout").each(function () {
                var name = $(this).find("name:last").text();
                var id = $(this).find("id:last").text();
                var element =
                    "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + id + "'class='col-md-9 cta-contents'>" +
                    "<h1 id='name' class='cta-title update'>" + name + "</h1>" +
                    "</div>" +

                    "<div tag='" + name + "'name ='workout' class='col-md-3 cta-button' id='" + id + "'name='" + name + "'>" +
                    "<button id='btnEdit' class='btnEdit btn btn-lg btn-block btn-primary'>Открыть</button>" +
                    "<button id='btnCopy' class='btnCopy btn btn-lg btn-block btn-primary'>Скопировать</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>";
                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            spinner.stop();
        }
    });
}

function getXmlThisWorkout(id) {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlWorkoutComplete?id=" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("exercise").each(function () {
                var elementRepeat = "<div class='row'><div class='cta-button'>";
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
                    "<div  id='" + id + "' class='col-md-9 cta-contents'>" +
                    "<h1 id='name' class='cta-title update'>" + name + "</h1>" + elementRepeat + "</div>" +
                    "<div tag='" + name + "'name ='exercise'class='col-md-3 cta-button' id='" + id + "'name='" + name + "'>" +
                    "</div>" +
                    "</div>";
                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            spinner.stop();
        }
    });spinner.stop();
}

