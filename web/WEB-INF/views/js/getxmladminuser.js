/*
function getXmlUser(){
    var target = document.getElementById("loading");
    var spinner = new Spinner().spin(target);
    $.ajax({
        url: "/user/search",
        cache: false,
        scriptCharset: "UTF-8",
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("workout").each(function () {
                var userId = $(this).find("userId:first").text()
                var name = $(this).find("name:last").text();
                var id = $(this).find("id:last").text();
                var author = $(this).find("author:first").text();
                var element =
                    "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + id + "'class='col-md-9 cta-contents cta-button'>" +
                    "<div><h1 id='"+id+"' class='update cta-title'>" + name + "</h1></div><div><button id='" + userId + "' class='btnAuthor btn btn-xs btn-primary'>автор: " + author +
                    "</button></div>" +
                    "</div>" +
                    "<div id='"+id+"' tag='"+name+"'name='workout' class='col-md-3 cta-button'>" +
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
}
*/
