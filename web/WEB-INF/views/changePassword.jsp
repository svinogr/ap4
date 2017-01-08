<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class=" bs-calltoaction bs-calltoaction-primary">
            <span class="alert">Ваш логин: ${user.login}</span> <br/>
            <span class="alert">${result}</span> <br/>

            <c:if test="${user.login != null}">
                <div>
                    <form:form class="form-signin" modelAttribute="user"
                               action="${pageContext.request.contextPath}confidential/reset"
                               method="post"
                               acceptCharset="UTF-8">


                        <p class="field">
                            <form:input path="login" type="hidden" class="form-control" name="login" id="login"
                                        placeholder="${user.login}" />
                            <c:if test="${error eq 'error'}">
                                <form:errors path="login"/>
                            </c:if>
                        </p>
                        <p class="field">
                            <form:input path="password" type="password" class="form-control" name="password"
                                        id="password"
                                        placeholder="Пароль"/>
                            <c:if test="${error eq 'error'}">
                                <form:errors path="password"/>
                            </c:if>
                        </p>


                        <p class="submit cta-button">
                            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Сменить пароль"
                                   name="submit"/>
                        </p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    </form:form>
                </div>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>