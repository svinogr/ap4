<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Material Design Bootstrap</title>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">

    <link href="css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"><script type="text/javascript" src="js/mdb.min.js"></script>

</head>

<body>

<!-- HTML-код модального окна -->
Здесь ничего интересного. Идет разработка!

<button id="n" value="открыть окно" class="btn btn-primary"></button>
<div id="myModalBox" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Заголовок модального окна -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="modal-title" class="modal-title">Заголовок модального окна</h4>
            </div>
            <!-- Основное содержимое модального окна -->
            <div class="modal-body">
               <input type="text" id="1" alt="сюда"/>

            </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <button id="3" type="button" class="btn btn-primary" data-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-primary">Сохранить</button>
            </div>
        </div>
    </div>
</div>

<!-- Скрипт, вызывающий модальное окно после загрузки страницы -->
<script>
    $(document).ready(function() {


        $("#n").click(function () {
            $("#myModalBox").modal('show');

        });
        $("#3").click(ad);



    });
</script>
<script>
    function ad() {
        var d=$("#1").val();
        alert(d);
    }

</script>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

<!-- Material Design Bootstrap -->
<link href="${pageContext.request.contextPath}/css/mdb.min.css" rel="stylesheet">

<!-- Your custom styles (optional) -->



</body>

</html>