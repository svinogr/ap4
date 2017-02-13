$(document).ready(function () {
    $("#searchsubmit").click(search);
});

function search() {
    var searcValue = $.trim($("#search").val());
    var typSearch = $("input[name=searchType]:checked").val();
    if(searcValue!="") {
        $.ajax({
            url: "/user/search?" + typSearch + "=" + searcValue,
            cache: false,
            scriptCharset: "UTF-8",
            dataType: "xml",
            success: function (xml) {
                $("#list").empty();
                $(xml).find("userXML").each(function () {
                    var userId = $(this).find("userId:first").text();
                    var date = $(this).find("date:first").text();
                    var userInfo = $(this).find("userInfoId:first").text();
                    var login = $(this).find("login:first").text();
                    var element =
                        "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                        "<div class='row'>" +
                        "<div id='" + userId + "' class='col-md-9 cta-contents cta-button'>" +
                        "<div><h1 id='" + userId + "' class='update cta-title'>" + login + "</h1></div><div><button id='" + userId + "' class='btnAuthor btn btn-xs btn-primary'>UserInfo: " + userInfo +
                        "</button></div>" +
                        "</div>" +
                        "<div id='" + userId + "' tag='" + name + "'name='workout' class='col-md-3 cta-button'>" +
                        "<button  class='btnEdit btn btn-lg btn-block btn-primary'>Открыть</button>" +
                        "<button  class='btnDelete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                        "</div>" +
                        "</div>" +
                        "</div>";
                    $("#list").append(element);
                   
                });
                $(".btnEdit").click(edit);
                $(".btnDelete").click(deleting);
                $(".update").click(update);
                $(".btnAuthor").click(getAuthorWorkout);
            }
        });
    }

}

function deleting() {
    //var usernameId= $(this).getParent().attr("id");
    alert("usernameId");

}
function edit() {
    //var usernameId= $(this).getParent().attr("id");
    alert("usernEDitameId");

}

