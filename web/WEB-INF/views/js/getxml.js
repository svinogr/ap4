$(document).ready(function () {
    getXmlUser();
});
function getXmlUser() {
    var opts = {
            lines: 13, // Число линий для рисования
            length: 0, // Длина каждой линии
            width: 10, // Толщина линии
            radius: 30, // Радиус внутреннего круга
            corners: 1, // Скругление углов (0..1)
            rotate: 0, // Смещение вращения
            direction: 1, // 1: по часовой стрелке, -1: против часовой стрелки
            color: "#000", // #rgb или #rrggbb или массив цветов
            speed: 2.2, // Кругов в секунду
            trail: 17, // Послесвечение
            shadow: false, // Тень(true - да; false - нет)
            hwaccel: false, // Аппаратное ускорение
            className: "spinner", // CSS класс
            zIndex: 2e9, // z-index (по-умолчанию 2000000000)
            top: "50%", // Положение сверху относительно родителя
            left: "50%" // Положение слева относительно родителя
        }
    var target = document.getElementById("loading");
    var spinner = new Spinner(opts).spin(target);
    $.ajax({
        url: "getxmlUser",
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list_workout").empty();
                  $(xml).find("workout").each(function () {
                var name = $(this).find("name:first").text();
                var id = $(this).find("id:first").text();
                var element = "<div class='workout' id='" + id + "'><button class='edit'>Edit</button><button class='delete'>Delete</button><span id='name_workout'>" + name + "</span></div>";
                $("#list_workout").append(element);
            });
            $(".delete").click(deleting);
            $(".edit").click(edit);
            spinner.stop();
        }
        
    });

    
}
function getXmlWorkout(id) {
    var opts = {
        lines: 13, // Число линий для рисования
        length: 0, // Длина каждой линии
        width: 10, // Толщина линии
        radius: 30, // Радиус внутреннего круга
        corners: 1, // Скругление углов (0..1)
        rotate: 0, // Смещение вращения
        direction: 1, // 1: по часовой стрелке, -1: против часовой стрелки
        color: "#000", // #rgb или #rrggbb или массив цветов
        speed: 2.2, // Кругов в секунду
        trail: 17, // Послесвечение
        shadow: false, // Тень(true - да; false - нет)
        hwaccel: false, // Аппаратное ускорение
        className: "spinner", // CSS класс
        zIndex: 2e9, // z-index (по-умолчанию 2000000000)
        top: "50%", // Положение сверху относительно родителя
        left: "50%" // Положение слева относительно родителя
    }
    var target = document.getElementById("loading");
    var spinner = new Spinner(opts).spin(target);
    $.ajax({
        url: "getXmlWorkout?id="+id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list_workout").empty();
            $(xml).find("exercise").each(function () {
                var name = $(this).find("name:first").text();
                var id = $(this).find("id:first").text();
                var element = "<div class='exercise' id='" + id + "'><button class='edit'>Edit</button><button class='delete'>Delete</button><span id='name_exercise'>" + name + "</span></div>";
                $("#list_workout").append(element);
            });
            $(".delete").click(deleting);
            $(".edit").click(edit);
            spinner.stop();
        }

    });


}

