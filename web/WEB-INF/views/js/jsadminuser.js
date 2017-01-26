$(document).ready(function () {
    $("#searchsubmit").click(search);
});

function search() {
    var searcValue = $("#search").val();
    var typSearch = $("input[name=searchType]:checked").val();
    alert(typSearch);
    if ($.trim(searcValue)) {
        $.ajax({
            url: "search?" + typSearch + "=" + searcValue,
            cache: false,
            scriptCharset: "UTF-8",
            dataType: "xml",
            success: function (xml) {
                $("#list").empty();
                $(xml).find("userXML").each(function () {
                    var userId = $(this).find("id:first").text()
                    var date = $(this).find("date:first").text();
                    var userInfo = $(this).find("userInfo:first").text();
                    var login = $(this).find("login:first").text();
                    var element =
                        "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                        "<div class='row'>" +
                        "<div id='" + id + "'class='col-md-9 cta-contents cta-button'>" +
                        "<div><h1 id='" + id + "' class='update cta-title'>" + login + "</h1></div><div><button id='" + userId + "' class='btnAuthor btn btn-xs btn-primary'>автор: " + author +
                        "</button></div>" +
                        "</div>" +
                        "<div id='" + id + "' tag='" + name + "'name='workout' class='col-md-3 cta-button'>" +
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
