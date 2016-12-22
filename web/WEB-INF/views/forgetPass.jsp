<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class="bs-calltoaction bs-calltoaction-primary">
            <span class="alert" id="alert"></span> <br/>
                <p class="field">
                    <input type="email" class="form-control" name="email" id="email" placeholder="Email"/>

                </p>

                <p class="submit cta-button">

                    <input id="emailbtn" class="btn btn-lg btn-block btn-primary" type="submit" value="Востановить пароль"/>
                </p>

        </div>
    </div>

</div>
</div>
<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script charset="utf-8" src="${pageContext.request.contextPath}/js/spin.js"></script>
<script  src="${pageContext.request.contextPath}/js/passbtn.js"></script>

<jsp:include page="footer.jsp"/>