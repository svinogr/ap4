$(document).ready(function () {
    var x = new XMLHttpRequest();
    x.open("GET", "/loginstatus", true);
    x.send();
    x.onreadystatechange = function () {
        if (x.readyState == 4) {
            if (x.status == 200) {
                $("#login").remove();
                $("#registration").remove();
            }
           else {
               $("#logout").remove();
               $("#info").remove();
            }
        }
    } 
});
