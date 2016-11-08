function getXmlAllWorkoutsComplete() {
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
        url: "getXmlAllWorkoutsComplete",
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
                    "<button id='btnCopy' class='btnCopy btn btn-lg btn-block btn-primary'>Скопировать</button>" +
                    "</div>" +

                    "</div>" +

                    "</div>";

                $("#list").append(element);
            });
            $(".btnEdit").click(edit);
           
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
        url: "getXmlWorkoutComplete?id=" + id,
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
    });spinner.stop();
}

