<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/styles.css" />
        <script>
            function openForm(id) {
                element = document.getElementById(id);
                style = element.style;
                style.display = "block";
                width = element.clientWidth;
                height = element.clientHeight;
                style.marginTop = Math.floor(-height / 2) + "px";
                style.marginLeft = Math.floor(-width / 2)+ "px";
            }
            
            function closeForm(id) {
                document.getElementById(id).style.display = "none";
                document.getElementById("shadowing").style.display = "none";
            }

            function hideOrShowShadowing(id) {
                element = document.getElementById("shadowing");
                if (element.style.display === "block") {
                    element.style.display = "none";
                    closeForm(id);
                } else {
                	element.style.display = "block";
                	openForm(id);
                }
            }

            function hideAll() {
           		cols = document.getElementsByClassName('popup');
           		for(i = 0; i < cols.length; i++) {
           			cols[i].style.display = "none";
           		}
           		document.getElementById("shadowing").style.display = "none";
            }
        </script>
        <title>Главная страница</title>
    </head>
    <body>
    	<div id="shadowing" onclick="hideAll()"></div>
        <div class="content">
            <header>
                <div class="header-title"><h1>Главная страница</h1></div>
				<div class="user-info">
                    <div class="user-info-container">
                    	<sec:authentication var="principal" property="principal" />
                        <p class="username"><b><security:authentication property="principal.username" /></b></p>
                        <p>Тип:
                        	<b>
	                        	<c:choose>
									<c:when test = "${principal.authorities == '[ROLE_USER]'}">
										обычный
									</c:when>
									<c:otherwise>модератор</c:otherwise>
								</c:choose>
							</b>
						</p>
                        <p>Статус: <b>нормальный</b></p>
                        <c:url value="/j_spring_security_logout" var="logoutUrl" />
                        <a class="logout" href="${logoutUrl}">Выйти</a>
                    </div>
                </div>
            </header>
            <div class="sections">
                <h3>Разделы форума</h3>
                <ul>
                	<c:forEach var="section" items="${sectionList}" varStatus="status">
	                    <li>
	                        <a href="topics.html">${section.title}</a>
	                        <!-- <a href="sections/delete_section?id=${section.sectionId}" class="delete"></a> -->
	                        <button type="submit" class="delete" onclick="hideOrShowShadowing('delete-confirm-${section.sectionId}')"></button>

	                        <div class="popup delete-popup" id="delete-confirm-${section.sectionId}">
	                            <h3>Подтверждение</h3>
	                            <p>Вы действительно хотите удалить раздел <b>${section.title}?</b></p>
	                            <div class="buttons">
	                                <button type="submit" class="a-delete"><a href="sections/delete_section?id=${section.sectionId}">Да</a></button>
	                                <button type="reset" class="cancel" onclick="closeForm('delete-confirm-${section.sectionId}')">Отмена</button>
	                            </div>
	                        </div>
	                    </li>
                    </c:forEach>
                </ul>
                <button type="submit" onclick="hideOrShowShadowing('add-section-form')">Добавить раздел</button>
                <div class="popup form-popup" id="add-section-form">
                    <form:form class="form-container" action="sections/add_section" method="post" modelAttribute="section">
                        <h3>Добавить раздел</h3>
                        <input type="text" placeholder="Введите название" id="section-title"
                            name="title" path="title" required><br>
                        <div class="buttons">
                            <button type="submit" class="add">Добавить</button>
                            <button type="reset" class="cancel" onclick="closeForm('add-section-form')">Отмена</button>
                        </div>
                    </form:form>
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