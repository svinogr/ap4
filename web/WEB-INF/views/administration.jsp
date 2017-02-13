<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <div class="btn-group cta-button">
            <a class="btn btn-primary btn-xs" style="color: #fdfdfe"
               href="${pageContext.request.contextPath}/administration/users">пользователи</a>

            <a class="btn btn-primary btn-xs" style="color: #fdfdfe"
               href="${pageContext.request.contextPath}/administration/post">посты</a>
        </div>
    </div>
</div>

<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<jsp:include page="footer.jsp"/>