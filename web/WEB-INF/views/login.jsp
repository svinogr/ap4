<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container" style="width: 300px">
    <form class="form-1" method="post"  action="${pageContext.request.contextPath}/login">
        <p class="field">
            <input type="text" name="username" id="username" placeholder="Логин">
            <i class="icon-user icon-large"></i>
        </p>
        <p class="field">
            <input type="password" name="password" id="password" placeholder="Пароль">
            <i class="icon-lock icon-large"></i>
        </p>
        <p class="submit">
            <input type="submit" name="submit"></i></input>
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>