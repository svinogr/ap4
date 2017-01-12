<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<info>
    <div class="container">
        <div class="row bs-calltoaction bs-calltoaction-primary">
            <span class="alert">${result}</span> <br/>
            <form:form class="form-signin" modelAttribute="author"
                       action="${pageContext.request.contextPath}/confidential/myInfo"
                       method="post"
                       acceptCharset="UTF-8"
            enctype="multipart/form-data">
            <div class="col-md-6">
             <img class="avatar btn btn-primary" src="data:image/jpeg;base64,${author.image}"/>
                <form:input class="btn btn-primary" path="" type="file" name="file"/>

                <h1 id="nameAuthor" name="${author.login}"> Автор: ${author.login}</h1>
            </div>

            <div class="col-md-6">
                <p class="field">
                    <form:input path="login" type="hidden"  class="form-control" name="login" id="login"
                                placeholder="Логин"/>
                    <c:if test="${error eq 'error'}">
                        <form:errors path="login"/>
                    </c:if>
                </p>
                <p class="field">
                    <form:input value="null" path="image" type="hidden"  class="form-control" name="image" id="image"
                                placeholder=""/>
                    <c:if test="${error eq 'error'}">
                        <form:errors path="image"/>
                    </c:if>
                </p>
                <p class="field">
                    <form:input path="description" type="hidden"  class="form-control" name="description" id="description"
                                placeholder="Описание"/>
                    <c:if test="${error eq 'error'}">
                        <form:errors path="description"/>
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