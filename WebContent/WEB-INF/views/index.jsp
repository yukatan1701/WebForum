<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/styles.css" />
        <script>
            function openForm() {
                element = document.getElementById("add-section-form");
                style = element.style;
                style.display = "block";
                width = element.clientWidth;
                height = element.clientHeight;
                style.marginTop = Math.floor(-height / 2) + "px";
                style.marginLeft = Math.floor(-width / 2)+ "px";
            }
            
            function closeForm() {
                document.getElementById("add-section-form").style.display = "none";
                document.getElementById("shadowing").style.display = "none";
                document.getElementById("section-title").value = '';
                
            }

            function hideOrShow() {
                if (document.getElementById("add-section-form").style.display === "block") {
                    closeForm();
                } else {
                    openForm();
                }
            }

            function hideOrShowShadowing() {
                element = document.getElementById("shadowing");
                if (element.style.display === "block") {
                    element.style.display = "none";
                    closeForm();
                } else {
                	element.style.display = "block";
                	openForm();
                }
            }
        </script>
        <title>Главная страница</title>
    </head>
    <body>
    	<div id="shadowing" onclick="hideOrShowShadowing()"></div>
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
                	<c:forEach var="section" items="${sectionList}" varStatus="status">
	                    <li>
	                        <a href="topics.html">${section.title}</a>
	                        <button type="submit"></button>
	                    </li>
                    </c:forEach>
                </ul>
                <button type="submit" onclick="hideOrShowShadowing()">Добавить раздел</button>
                <div class="form-popup" id="add-section-form">
                    <form class="form-container">
                        <h3>Добавить раздел</h3>
                        <input type="text" placeholder="Введите название" id="section-title"
                            name="section-title" required><br>
                        <div class="buttons">
                            <button type="submit" class="add">Добавить</button>
                            <button type="reset" class="cancel" onclick="closeForm()">Отмена</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="user-list">
                <h3>Пользователи</h3>
                <h4>Топ активных за все время: </h4>
                <ol>
	                <c:forEach var="pair" items="${userMap}" varStatus="status">
	                    <li>${pair.key.login} (${pair.value} сообщений)</li>
	                </c:forEach>   
                </ol>
                <a href="users.html">Подробнее...</a>
            </div>
        </div>
    </body>
</html>