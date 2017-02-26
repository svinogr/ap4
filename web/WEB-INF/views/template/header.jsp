<header>
    <div class="container">
        <div class="row">
            <div class="navbar navbar-default bs-calltoaction bs-calltoaction-primary ">
                <div class="row">
                    <div class="navbar-header">
                        <button type="button" style="background-color: #337ab7" class="btn navbar-toggle"
                                data-toggle="collapse"
                                data-target="#responsive-menu">
                            <span style="background: white" class="icon-bar"></span>
                            <span style="background: white; color: white" class="icon-bar"></span>
                            <span style="background: white; color: white" class="icon-bar"></span>
                        </button>
                        <a class="btn logo" style="color: #fdfdfe"
                           href="/#"><img class="logo" src="../../../static/images/logo.gif" alt="U-PUMP"/></a>
                    </div>
                    <div class="row collapse navbar-collapse navbar-width cta-button" id="responsive-menu">
                        <div class="navbarmini">
                            <ul class="nav navbar-nav btn-group navbarmini">
                                <li class="navbarmini">
                                    <button ng-click="openPage('new.html')"
                                            class="btn btn-primary ">Новости</button>
                                </li>
                                <li class="navbarmini">
                                    <button ng-click="openPage('myWorkout.html')"
                                            class="btn btn-primary ">Мои тренировки</button>
                                </li>
                                <li class="navbarmini">
                                    <button ng-click="openPage('best.html')"
                                            class="btn btn-primary ">Лучшие</button>
                                </li>
                                <li class="navbarmini">
                                    <button ng-click="openPage('all.html')"
                                            class="btn btn-primary ">Все тренировки</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div id="blocklogin" align="right">
                <div class="btn-group cta-button" role="group">
                    <button id="login" ng-click="openPage('login.html')"
                            class="btn btn-primary btn-xs">войти</button>
                    <button id="registration" href="${pageContext.request.contextPath}/registration"
                            class="btn btn-primary btn-xs">зарегистрироваться</button>
                    <button id="info" href="${pageContext.request.contextPath}/confidential/myInfo"
                            class="btn btn-primary btn-xs">обо мне</button>
                    <button id="logout" href="${pageContext.request.contextPath}/logout"
                            class="btn btn-primary btn-xs">выйти</button>
                </div>
            </div>
        </div>
    </div>
</header>