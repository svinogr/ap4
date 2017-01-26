var pageNumber=0;
function getXmlAllWorkoutsBest(page) {
    if(page!=null){
        pageNumber=page;
    }else pageNumber=0;
      
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "/allWorkoutsXml?page="+pageNumber,
        cache: false,
        scriptCharset: "UTF-8",
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("workout").each(function () {
                var name = $(this).find("name:last").text();
                var id = $(this).find("id:last").text();
                var rate = $(this).find("rate:first").text();
                var author=$(this).find("author:first").text();
                var element =
                    "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + id + "'class='col-md-9 cta-contents'>" +
                    "<div><h1 id='name' class='update btn btn-primary'>" + name + "</h1></div><div class='cta-button'><button id='"+author+"' class='btnAuthor btn btn-xs btn-primary'>автор: "+author+
                    "</button></div>" +
                    "<h3 id='name' class='update btn btn-primary'>Рейтинг: " + rate + "</h3>" +
                    "</div>" +
                    "<div id='" + id + "' class='col-md-3 cta-button'>" +
                    "<button id='1' class='btnRate btn btn-lg btn-block btn-primary'>+</button>" +
                    "<button id='0' class='btnRate btn btn-lg btn-block btn-primary'>-</button>" +
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
            $(".btnRate").click(rate);
            $(".btnCopy").click(copy);
            $(".btnAuthor").click(getAuthorWorkout);
            spinner.stop();
        }
    });
}

function getXmlThisWorkout(id) {
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "getXmlWorkoutBest?id=" + id,
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
    });
    spinner.stop();
}

