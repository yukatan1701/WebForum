<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/styles.css" />
        <title>Главная страница</title>
    </head>
    <body>
        <div class="content">
            <header>
                <div class="header-title"><h1>Главная страница</h1></div>
                <form class="sign-in">
                    <label for="login">Логин:</label><br>
                    <input type="text" id="login" name="login" value="admin"><br>
                    <label for="password">Пароль:</label><br>
                    <input type="password" id="password" name="password" value="password"><br>
                    <button type="submit">Войти</button>
                    <button type="submit">Регистрация</button>
                </form>
            </header>
            <div class="sections">
                <h3>Разделы форума</h3>
                <ul>
                    <li>
                        <a href="topics.html">Раздел 1</a>
                        <button type="submit"></button>
                    </li>
                    <li>
                        <a>Раздел 2</a>
                        <button type="submit"></button>
                    </li>
                    <li>
                        <a>Раздел 3</a>
                        <button type="submit"></button>
                    </li>
                </ul>
                <button type="submit">Добавить раздел</button>
            </div>
            <div class="user-list">
                <h3>Пользователи</h3>
                <h4>Топ активных за все время: </h4>
                <ol>
                <c:forEach var="user" items="${userList}" varStatus="status">
                    <li>${user.login} (${user.dateOfRegistration})</li>
                </c:forEach>   
                </ol>
                <a href="users.html">Подробнее...</a>
            </div>
        </div>
    </body>
</html>