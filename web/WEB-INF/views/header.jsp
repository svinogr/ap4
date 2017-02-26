<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>U-PUMP</title>
    <link href="${pageContext.request.contextPath}/css/mdb.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/loginstatus.js"></script>
    <script src="${pageContext.request.contextPath}/js/device.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="navbar navbar-default bs-calltoaction bs-calltoaction-primary ">
                <div class="row">
                    <div class="navbar-header">
                        <button type="button" style="background-color: #337ab7" class="btn navbar-toggle"
                                data-toggle="collapse"
                                data-target="#responsive-menu">
                            <span style="background: white" class="icon-bar"></span>
                            <span style="background: white; color: white" class="icon-bar"></span>
                            <span style="background: white; color: white" class="icon-bar"></span>
                        </button>
                        <a class="btn logo" style="color: #fdfdfe" href="${pageContext.request.contextPath}/instruction"><img class="logo"
                                 src="${pageContext.request.contextPath}/jpg/logo.gif" alt="U-PUMP"/></a>
                    </div>
                    <div class="row collapse navbar-collapse navbar-width cta-button" id="responsive-menu">
                        <div class="navbarmini">
                            <ul class="nav navbar-nav btn-group navbarmini">
                                <li class="navbarmini" >
                                    <button href="angular/myWorkout.html"
                                       class="btn btn-primary">Новости</button>
                                </li>
                                <li class="navbarmini">
                                    <button href="${pageContext.request.contextPath}/confidential/myworkout"
                                       class="btn btn-primary ">Мои тренировки</button>
                                </li>
                                <li class="navbarmini">
                                    <button href="${pageContext.request.contextPath}/best"
                                       class="btn btn-primary">Лучшие</button>
                                </li>
                                <li class="navbarmini">
                                    <button href="${pageContext.request.contextPath}/allWorkouts"
                                       class="btn btn-primary">Все тренировки</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div id="blocklogin" align="right">
                <div class="btn-group cta-button" role="group">
                    <a id="login" href="${pageContext.request.contextPath}/login"
                       class="btn btn-primary btn-xs">войти</a>
                    <a id="registration" href="${pageContext.request.contextPath}/registration"
                       class="btn btn-primary btn-xs">зарегистрироваться</a>
                    <a id="info" href="${pageContext.request.contextPath}/confidential/myInfo"
                       class="btn btn-primary btn-xs">обо мне</a>
                    <a id="logout" href="${pageContext.request.contextPath}/logout"
                       class="btn btn-primary btn-xs">выйти</a>
                </div>
            </div>
        </div>
    </div>
</header>
</body>
</html>