<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class=" bs-calltoaction bs-calltoaction-primary">

            <span class="alert">${result}</span> <br/>
            <div class="">
                <form:form class="form-signin" modelAttribute="user"
                           action="${pageContext.request.contextPath}/registration"
                           method="post"
                           acceptCharset="UTF-8">

                    <p class="field">
                        <form:input path="name" type="text" class="form-control" name="name" id="name"
                                    placeholder="Имя"/>
                        <c:if test="${error eq 'error'}">
                            <form:errors path="name"/>
                        </c:if>
                    </p>
                    <p class="field">
                        <form:input path="login" type="text" class="form-control" name="login" id="login"
                                    placeholder="Логин"/>
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
                    <p class="field">
                        <form:input path="email" type="email" class="form-control" name="email" id="email"
                                    placeholder="E-mail"/>
                        <c:if test="${error eq 'error'}">
                            <form:errors path="email"/>
                        </c:if>
                    </p>


                    <p class="submit cta-button">
                        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Зарегистрировать"
                               name="submit"/>
                    </p>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                </form:form>
            </div>

        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>