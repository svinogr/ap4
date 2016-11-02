<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div id="test2" class="container">
    <h1 id="test">U-PUMP v.0.1.</h1>
</div>
<div id="myModalBox" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Заголовок модального окна -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="modal-title" class="modal-title"></h4>
            </div>
            <!-- Основное содержимое модального окна -->
            <div class="modal-body">
                         </div>
            <!-- Футер модального окна -->
            <div class="modal-footer">
                <button id="save" type="button" class="btn btn-primary">Cохранить</button>
                <button id="dismiss" type="button" data-dismiss="modal" class="btn btn-primary">Отмена</button>
            </div>
        </div>
    </div>
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
                        <button id="add" class="add btn btn-primary">Add</button>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script charset="utf-8" src="${pageContext.request.contextPath}/js/spin.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/js.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/getxml.js"></script>

<jsp:include page="footer.jsp"/>