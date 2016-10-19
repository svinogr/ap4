
function getXmlAllWorkouts() {
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
    };
    var target = document.getElementById("loading");
    var spinner = new Spinner(opts).spin(target);
    $.ajax({
        url: "getXmlAllWorkouts",
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("workout").each(function () {
                var name = $(this).find("name:last").text();
                var id = $(this).find("id:last").text();
                var element = "<div class='workout' id='" + id + "' name='"+name+"'><button id='btnEdit' class='btnEdit'>Edit</button>" +
                    "<button class='delete'>Delete</button><span id='name_workout'>" + name + "</span></div>";
                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            spinner.stop();
        }

    });
}

function getXmlThisWorkout(id) {
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
    };
    var target = document.getElementById("loading");
    var spinner = new Spinner(opts).spin(target);
    $.ajax({
        url: "getXmlWorkout?id=" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("exercise").each(function () {
                var name = $(this).find("name:first").text();
                var id = $(this).find("id:first").text();
                var element = "<div class='exercise' id='" + id + "' name='"+name+"'><button class='btnEdit'>Edit</button><button class='delete'>Delete</button><span id='"+id+"'>" + name + "</span></div>";
                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            spinner.stop();
        }
    });
}
function getXmlThisExercise(id) {
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
    };
    var target = document.getElementById("loading");
    var spinner = new Spinner(opts).spin(target);
    $.ajax({
        url: "getXmlExercise?id=" + id,
        cache: false,
        dataType: "xml",
        success: function (xml) {
            $("#list").empty();
            $(xml).find("tryes").each(function () {
                var name = $(this).find("weight:first").text();
                var id = $(this).find("id:first").text();
                var repeat = $(this).find("repeat:first").text();
                var element = "<div class='try' id='" + id + "' name='"+name+"'><button class='delete'>Delete</button><span id='"+id+"'>Weight is"+ name +"  Repeat are"+repeat+ "</span></div>";
                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            spinner.stop();
        }

    });


}

