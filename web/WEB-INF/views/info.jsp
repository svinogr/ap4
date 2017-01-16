<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<info>
    <div class="container">
        <div class="row bs-calltoaction bs-calltoaction-primary">
            <div class="col-md-6">
                <img class="avatar btn btn-primary" src="data:image/jpeg;base64,${author.image}"/>
                <h1 class="btn btn-primary form-control" id="nameAuthor" name="${author.login}">${author.login}</h1>
                <textarea maxlenght="20"  rows="5"  class="btn btn-primary form-control">${author.description}</textarea>

            </div>
            <div class="col-md-6">
                <h3 id="nameAuthorAge" class="btn btn-primary form-control" name="${author}">Возраст: ${author.age}</h3>
                <h3 id="nameAuthorWeight" class="btn btn-primary form-control" name="${author.weight}">Вес: ${author.weight}</h3>
                <h3 id="nameAuthorHeight" class="btn btn-primary form-control" name="${author.height}">Рост: ${author.height}</h3>
                <h3 id="nameAuthorExperience" class="btn btn-primary form-control" name="${author.experience}">Стаж: ${author.experience}</h3>
            </div>

        </div>
    </div>
</info>
</body>
</html>