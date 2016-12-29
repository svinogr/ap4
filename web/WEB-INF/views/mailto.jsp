<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class=" bs-calltoaction bs-calltoaction-primary">

            <span class="alert">${result}</span> <br/>
            <div class="">
                <form:form class="form-signin" modelAttribute="form"
                           action="${pageContext.request.contextPath}/mailto"
                           method="post"
                           acceptCharset="UTF-8">

                    <p class="field">
                        <form:input path="from" type="text" class="form-control" name="from" id="from"
                                    placeholder="Адрес электронной почты для ответа"/>
                        <c:if test="${error eq 'error'}">
                            <form:errors path="from"/>
                        </c:if>
                    </p>
                    <p class="field">
                        <form:input path="body" type="text" class="form-control" name="body" id="body"
                                    placeholder="Текст сообщения"/>
                        <c:if test="${error eq 'error'}">
                            <form:errors path="body"/>
                        </c:if>
                    </p>

                    <p class="submit cta-button">
                        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Отправить сообщение"
                               name="submit"/>
                    </p>


                </form:form>
            </div>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
