<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <div class=" bs-calltoaction bs-calltoaction-primary">
            <span class="alert">Ваш логин1: ${user.login}</span> <br/>
            <span class="alert">${result}</span> <br/>


                <div>
                    <form:form class="form-signin" enctype="multipart/form-data"
                               action="${pageContext.request.contextPath}image"
                               method="post"
                               acceptCharset="UTF-8">


                        <p class="field">
                            <input class="btn btn-primary" type="file" name="file">

                        </p>


                        <p class="submit cta-button">
                            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Сменить пароль"
                                   name="submit"/>
                        </p>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    </form:form>
                </div>



        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>