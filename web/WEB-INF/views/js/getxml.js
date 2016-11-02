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
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                    "</div>" +

                    "</div>" +

                    "</div>";

                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
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
                    "<button id='btnEdit' class='btnEdit btn btn-lg btn-block btn-primary'>Редактировать подходы</button>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                    "</div>" +
                    "</div>";
                $("#list").append(element);
            });
            $(".color-change").click(changeColorRepeat);
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
            spinner.stop();
        }
    });
}
function getXmlThisWorkoutOriginal(id) {
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
                $(this).find("tryes").each(function () {
                    var weight = $(this).find("weight:first").text();
                    var repeat = $(this).find("repeat:first").text();
                });
                var elementRepeat = "<div class='container'><div class='row'></div></div>";
                var element = "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div  id='" + id + "' class='col-md-9 cta-contents'>" +
                    "<h1 id='name' class='cta-title update'>" + name + "</h1>" +
                    "</div>" +

                    "<div tag='" + name + "'name ='exercise'class='col-md-3 cta-button' id='" + id + "'name='" + name + "'>" +
                    "<button id='btnEdit' class='btnEdit btn btn-lg btn-block btn-primary'>Редактировать</button>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +

                    "</div>" +

                    "</div>";
                $("#list").append(element);
            });
            $(".color-change").click(changeColorRepeat);
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
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
                var weight = $(this).find("weight:first").text();
                var id = $(this).find("id:first").text();
                var repeat = $(this).find("repeat:first").text();
                var element = "<div class='bs-calltoaction bs-calltoaction-primary'>" +
                    "<div class='row'>" +
                    "<div id='" + id + "' name ='try' class='col-md-9 cta-contents'>" +
                    "<h2 id='weight' class=' update cta-title'> Вес: " + weight + "</h2>" +
                    "<h2 id='repeat' class='update cta-title'> Повторений: " + repeat + "</h2>" +
                    "</div>" +
                    "<div id='" + id + "' name ='try' class='try col-md-3 cta-button'>" +
                    "<button class='delete btn btn-lg btn-block btn-primary'>Удалить</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>";
                $("#list").append(element);
            });
           // var buttonReset = "'<div class='row'><div class='btn-group btn-group-justified'><div class='container btn-group'>"
           //     + " <button id='reset' class='reset btn btn-danger'>Сбросить подходы</button></div></div></div>";
           // $("#list").append(buttonReset);
           // $(".reset").click(resetRepeat);
           // $(".color-change").click(changeColorRepeat);
            $(".btnEdit").click(edit);
            $(".delete").click(deleting);
            $(".update").click(update);
            spinner.stop();
        }

    });


}
function changeColorRepeat() {
    var color =$(this).attr("class");
    if(color=="color-change btn btn-primary"){
        $(this).removeClass("btn-primary");
        $(this).addClass("btn-danger");
    } else {
        $(this).removeClass("btn-danger");
        $(this).addClass("btn-primary");
    }
}
function resetRepeat() {
    $(".color-change").removeClass("bs-calltoaction-danger");
    $(".color-change").addClass("bs-calltoaction-primary");
}

