<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div id="test2" class="container">
    <h1 id="test">U-PUMP v.0.1.</h1>
</div>
<div class="container">
    <div id="main">
        <div id="loading"></div>
        <div id="title" class="cta-contents">
            <h3 class="cta-title" id="topTitle">Спиcок тренировок:</h3>
            <h3 id="middleTitle" class="hiden">Список Упражнений:</h3>
            <h3 id="bottomTitle" class="hiden">Список подходов:</h3>
            <div class="row">
                <div class="btn-group btn-group-justified">
                    <div class="container btn-group">
                        <button id="all" class="unhiden btn btn-primary">50 лучших</button>
                    </div>
                </div>
                <div class="btn-group btn-group-justified">
                    <div class="container btn-group">
                        <button id="back" class="unhiden btn btn-primary">Назад</button>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <div id="list"></div>
</div>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
<script charset="utf-8" src="${pageContext.request.contextPath}/js/spin.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jsbest.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/getxmlbest.js"></script>

<jsp:include page="footer.jsp"/>