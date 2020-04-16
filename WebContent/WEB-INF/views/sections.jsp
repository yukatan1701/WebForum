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
        <script src="${pageContext.servletContext.contextPath}/scripts/forms.js"></script>
        <title>Главная страница</title>
    </head>
    <body>
    	<div id="shadowing" onclick="hideAll()"></div>
        <div class="content">
        	<%@ include file="parts/header.jsp" %>
            <div class="sections">
                <h3>Разделы форума</h3>
                <ul>
                	<c:forEach var="section" items="${sectionList}" varStatus="status">
	                    <li>
	                        <a href="topics?section_id=${section.sectionId}">${section.title}</a>
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
                <h4>Топ-5 активных за все время: </h4>
                <ol>
	                <c:forEach var="pair" items="${userMap}" varStatus="status">
	                    <li><a href="user?id=${pair.key.userId}">${pair.key.login}</a> (${pair.value} сообщений)</li>
	                </c:forEach>   
                </ol>
                <br>
                <p class="link-container">
                	<a href="user_lists">Подробнее...</a>
                </p>
            </div>
        </div>
    </body>
</html>