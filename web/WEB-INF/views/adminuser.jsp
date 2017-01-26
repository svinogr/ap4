<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<div id="myModalBox" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content bs-calltoaction bs-calltoaction-primary">
            <!-- Заголовок модального окна -->
            <div class="modal-header ">
                <h4 id="modal-title" class="modal-title"></h4>
            </div>
            <!-- Основное содержимое модального окна -->
            <div class="modal-body"></div>
            <!-- Футер модального окна -->
            <div class="modal-footer cta-button">
                <button id="save" type="button" class="btn btn-primary">Cохранить</button>
                <button id="dismiss" type="button" data-dismiss="modal" class="btn btn-primary">Отмена</button>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div id="loading"></div>
    <div id="row">

        <div class="bs-calltoaction bs-calltoaction-primary">
            <div> <input id="search" type="text" class="form-control"/></div>
            <div><input id="searchsubmit" type="submit" class="btn btn-lg btn-primary btn-block" value="поиск"/></div>
            <div class="cta-contents"><label><input name="searchType" value="login" class="searchType" type="radio"> по логину <input value="email" class="searchType" name="searchType" type="radio"> по логину</label></div>

        </div>
    </div>
    <div id="title" class="cta-contents">
        <h3 class="cta-title" id="topTitle">Спиcок пользователей:</h3>
    </div>
</div>
<div id="list"></div>
</div>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script charset="utf-8" src="${pageContext.request.contextPath}/js/spin.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jsadminuser.js"></script>
<%--
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/getxmladminuser.js"></script>
--%>

<jsp:include page="footer.jsp"/>