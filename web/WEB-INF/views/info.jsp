<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<info>
    <div class="container">
        <div class="row bs-calltoaction bs-calltoaction-primary">
            <div class=" col-md-6">
                <img src="${pageContext.request.contextPath}/jpg/logo.gif"/>

                <h1 id="nameAuthor" name="${author.login}"> Автор: ${author.login}</h1>
            </div>
            <div class="col-md-6">
                <h3 id="nameAuthorAge" name="${author}">Возраст: ${author.age}</h3>
                <h3 id="nameAuthorWeight" name="${author.weight}">Вес: ${author.weight}</h3>
                <h3 id="nameAuthorHeight" name="${author.height}">Рост: ${author.height}</h3>
                <h3 id="nameAuthorExperience" name="${author.experience}">Стаж: ${author.experience}</h3>
            </div>

        </div>
    </div>
</info>
</body>
</html>