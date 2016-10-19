<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>New Web Project</title>
    <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
</head>
<body>
<div id="main">
    <div id="loading"></div>
    <div id="title">
        <span id="topTitle">List of Workouts</span>
        <span id="middleTitle" class="hiden">Exercises</span>
        <span id="bottomTitle" class="hiden">Repeats</span>
        <div class="button"></div>
        <button id="add" class="add">Add</button>
        <button id="back" class="unhiden">Back</button>
    </div>
    <div id="list"></div>
</div>
<script src="${pageContext.request.contextPath}/js/spin.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/js.js"></script>
<script src="${pageContext.request.contextPath}/js/getxml.js"></script>



</body>
</html>

