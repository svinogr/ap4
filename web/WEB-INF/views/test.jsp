
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TEST</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/xmlPost.js"></script>
</head>
<body>
<h1> Это тестовая JSP</h1>
<a href="/login">Тест Залогиниться</a><p/>
<a href="/registration">Тест Зарегиться</a>
<p/><a href="/logout">Разлогиниться</a>
<p/><a href="/aut">вывод в консоль айтентификации</a>
<p/><a href="/XML">проверка парсинга хмл</a>
<form  method="post" action="${pageContext.request.contextPath}/user" >
    <input type="submit" value="ГО">

</form>


    <input id = "login" type="text">
    <input  id="email" type="text">
    <input id="pass" type="text">
    <input id="h" type="submit">


</body>
</html>
