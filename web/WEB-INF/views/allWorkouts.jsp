<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
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
                        <button id="all" count="${count}" class="unhiden btn btn-primary">Все</button>
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

<div class="container">
<div id="pagination"  count="${count}" class="row" align="center">

    <ul  class="pagination">
        <li><a id="firstPage" class="pag" href="#">&laquo;</a></li>
        <li><a  class="points" >...</a></li>
        <li><a id="left" class="pag" href="#">1</a></li>
        <li><a id="middle" class="pag" href="#">2</a></li>
        <li><a id="right"  class="pag" href="#">3</a></li>
        <li><a  class="points" >...</a></li>
        <li><a id="lastPage" class="pag" chref="#">&raquo;</a></li>
    </ul>

</div>
</div>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
<script charset="utf-8" src="${pageContext.request.contextPath}/js/spin.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jsbestall.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/getxmlbestall.js"></script>
<jsp:include page="footer.jsp"/>