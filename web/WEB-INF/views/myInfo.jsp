<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<info>
    <div class="container">
        <div class="row bs-calltoaction bs-calltoaction-primary">
            <span class="alert">${result}</span> <br/>
            <div class=" col-md-6">
                <img src="${pageContext.request.contextPath}/jpg/logo.gif"/>

                <h1 id="nameAuthor" name="${author.login}"> Автор: ${author.login}</h1>
            </div>
            <form:form class="form-signin" modelAttribute="author"
                       action="${pageContext.request.contextPath}/confidential/myInfo"
                       method="post"
                       acceptCharset="UTF-8">
            <div class="col-md-6">
                <p class="field">
                    <form:input path="login" type="hidden"  class="form-control" name="login" id="login"
                                placeholder="Логин"/>
                    <c:if test="${error eq 'error'}">
                        <form:errors path="login"/>
                    </c:if>
                </p>
                <p class="field">
                    <label>Возраст: </label>
                <form:input path="age" type="number"  class="form-control" name="age" id="age"
                              placeholder=""/>
                    <c:if test="${error eq 'error'}">
                        <form:errors path="age"/>
                    </c:if>
                </p>
                <p class="field">
                    <label>Вес:</label>
                    <form:input path="weight"  type="number" class="form-control" name="age" id="age"
                                placeholder="" />
                    <c:if test="${error eq 'error'}">
                        <form:errors path="weight"/>
                    </c:if>
                </p>
                <p class="field">
                    <label>Рост:</label>
                    <form:input path="height" type="number"  class="form-control" name="age" id="age"
                                placeholder="" />
                    <c:if test="${error eq 'error'}">
                        <form:errors path="height"/>
                    </c:if>
                </p>
                <p class="field">
                    <label>Стаж</label>
                    <form:input path="experience"  type="number" class="form-control" name="age" id="age"
                                placeholder="" />
                    <c:if test="${error eq 'error'}">
                        <form:errors path="experience"/>
                    </c:if>
                </p>

            </div>
                <p class="submit cta-button">
                    <input class="btn btn-lg btn-primary btn-block" type="submit" value="Сохранить"
                           name="submit"/>
                </p>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form:form>

        </div>
    </div>
</info>
</body>
</html>