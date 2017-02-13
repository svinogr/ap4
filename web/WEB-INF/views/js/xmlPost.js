function reger() {
    var login = $("#login").val();
    var email = $("#email").val();
    var pass = $("#pass").val();
    var o="<?xml version='1.0' encoding='UTF8' standalone='true'?>";
    var xml="<user><login>"+login+"</login><email>"+email+"</email><password>"+pass+"</password></user>";
    var xmlDoc = new DOMParser().parseFromString("<user><login>login</login><email>email</email><password>pass+</password></user>", "text/xml");

   /* var xmlR  = new XMLHttpRequest();
    xmlR.open("POST","/user/", true);
    xmlR.setRequestHeader("Content-Type", "application/xml")
    xmlR.send(xml);*/
    
    alert(xml);
    $.ajax({
        type: "POST",
        url: "/users/user/",
        data: xml,
        contentType: "application/xml",
        cache: false,
        scriptCharset: "UTF-8",
        dataType: "xml",
        success: function (xml) {
        }
    });

}
$(document).ready(function () {
    $("#h").click(reger);
});