<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<info>
    <div class="container">
        <div class="row bs-calltoaction bs-calltoaction-primary">
            <div class="col-md-6">
                <img class="avatar btn btn-primary" src="data:image/jpeg;base64,${userInfo.image}"/>
                <h1 class="cta-title" id="nameAuthor">Имя: ${userInfo.name}</h1>
                <h2 maxlenght="20" rows="5" class="cta-title">${userInfo.description}</h2>
            </div>
            <div class="col-md-6">
                <h3 id="nameAuthorAge" class="cta-title">Возраст: ${userInfo.age}</h3>
                <h3 id="nameAuthorWeight" class="cta-title">Вес: ${userInfo.weight}</h3>
                <h3 id="nameAuthorHeight" class="cta-title">Рост: ${userInfo.height}</h3>
                <h3 id="nameAuthorExperience" class="cta-title">Стаж: ${userInfo.experience}</h3>
            </div>
        </div>
    </div>
</info>
</body>
</html>