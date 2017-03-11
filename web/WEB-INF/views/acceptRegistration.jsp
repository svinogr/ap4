<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html ng-app="UPump" >
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="/static/js/jquery-3.1.0.min.js"></script>
    <link href="/static/css/mdb.css" rel="stylesheet">
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <script src="/static/js/bootstrap.js"></script>
    <link href="/static/css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <script>
        function redirect() {
            window.location.href="http://localhost:8080/";
        }
    </script>>
</head>
<body  ng-cloak ng-controller="UrlCtrl">

<div class="container" ng-app="UPump">
    <div class="row">
        <div class="btn-group btn-group-justified">
            <div class="container btn-group">
                <span class="alert">${result}</span> <br/>
                <button class="add btn btn-primary" onclick="redirect()"> Перейти на сайт
                </button>
            </div>
        </div>
    </div>
</div>
</body>

</html>