function forgetPass() {
    var email =$("#email").val();
     if(email !="") {
        var x = new XMLHttpRequest();
        x.open("GET", "rememberPass?email=" + encodeURIComponent(email), true);
             x.send();
        x.onreadystatechange = function () {
            if (x.readyState == 4) {
                if (x.status == 200) {
                    $("#alert").text("пароль отправлен на Ваш электронный адрес");
                    /*window.location.href="/rememberPassRequest"*/
                } else $("#alert").text("пользователя с таким электронным адресом не найдено")
            }
        }
    }
    
}
$(document).ready(function () {
    $("#emailbtn").click(forgetPass);
});