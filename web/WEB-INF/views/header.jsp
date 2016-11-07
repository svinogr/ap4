<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>U-PUMP</title>
    <link href="${pageContext.request.contextPath}/css/mdb.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="navbar navbar-default bs-calltoaction bs-calltoaction-primary">
                <div class="row">
                    <div class="navbar-header">
                        <button type="button" style="background-color: #337ab7" class="btn navbar-toggle"
                                data-toggle="collapse"
                                data-target="#responsive-menu">
                            <span style="background: white" class="icon-bar"></span>
                            <span style="background: white" style="color: white" class="icon-bar"></span>
                            <span style="background: white" style="color: white" class="icon-bar"></span>
                        </button>
                        <a class="cta-contents navbar-brand" style="color: #fdfdfe" align="center" href="#"><img src="${pageContext.request.contextPath}/jpg/logo.gif" alt="U-PUMP"/></a>
                    </div>
                    <div class="row collapse navbar-collapse navbar-width cta-button" id="responsive-menu">


                        <div class="">
                            <ul class="nav navbar-nav btn-group">
                                <li>
                                    <a href="${pageContext.request.contextPath}/"
                                       class="btn btn-primary">Главная</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/confidential/myworkout"
                                       class="btn btn-primary">Мои тренировки</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/best"
                                       class="btn btn-primary">Лучшие</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/complete"
                                       class="btn btn-primary">Готовые</a>
                                </li>


                            </ul>
                        </div>
                        <div align="right" class="">
                            <div class="btn-group cta-button" role="group">
                                <a href="${pageContext.request.contextPath}/login"
                                   class="btn btn-primary btn-xs">войти</a>
                                <a href="${pageContext.request.contextPath}/registration"
                                   class="btn btn-primary btn-xs">зарегистрироваться</a>
                                <a href="${pageContext.request.contextPath}/logout"
                                   class="btn btn-primary btn-xs">выйти</a>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</header>
</body>
</html>