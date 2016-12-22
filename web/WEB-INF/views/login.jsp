<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="bs-calltoaction bs-calltoaction-primary">
            <span class="alert">${result}</span> <br/>

            <form class="form-signin" method="post" action="${pageContext.request.contextPath}/login">
                <p class="field">
                    <input type="text" class="form-control" name="username" id="username" placeholder="Логин"/>
                    <%--<i class="icon-user icon-large"></i>--%>
                </p>
                <p class="field">
                    <input type="password" class="form-control" name="password" id="password" placeholder="Пароль">
                    <%--<i class="icon-lock icon-large"></i>--%>
                </p>
                <p class="submit cta-button">

                    <input class="btn btn-lg btn-block btn-primary" type="submit" value="Войти" name="submit"/>
                </p>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

        </div>
    </div>
    <div class="row">
        <div align="right">
            <div class="btn-group cta-button" >
                <a id="fogetlogin" href="${pageContext.request.contextPath}/forgetPass"
                   class="btn btn-primary btn-xs">Востановить пароль</a>
            </div>
        </div>
    </div>
</div>
</div>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script charset="utf-8" src="${pageContext.request.contextPath}/js/spin.js"></script>

<jsp:include page="footer.jsp"/>