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
	<title>Темы в разделе</title>
</head>
<body>
	<div id="shadowing" onclick="hideAll()"></div>
    <div class="content">
    	<%@ include file="parts/header.jsp" %>
    	<div class="sections">
        	<h3> <a href="${pageContext.servletContext.contextPath}/">Главная</a> | Темы в "${section.title}"</h3>
            <ul>
            	<c:forEach var="topic" items="${topicList}" varStatus="status">
	    			<li>
	                   <a href="posts?topic_id=${topic.topicId}">${topic.title}</a>
	                   <button type="submit" class="delete" onclick="hideOrShowShadowing('delete-confirm-${topic.topicId}')"></button>
	
	                   <div class="popup delete-popup" id="delete-confirm-${topic.topicId}">
	                       <h3>Подтверждение</h3>
	                       <p>Вы действительно хотите удалить тему <b>${topic.title}?</b></p>
	                       <div class="buttons">
	                       	   <sec:authentication var="principal" property="principal" />
	                           <button type="submit" class="a-delete"><a href="topics/delete_topic?section_id=${section.sectionId}&amp;topic_id=${topic.topicId}&amp;login=${principal.username}">Да</a></button>
	                           <button type="reset" class="cancel" onclick="closeForm('delete-confirm-${topic.topicId}')">Отмена</button>
	                       </div>
	                   </div>
	               </li>
              	</c:forEach>
            </ul>
            <button type="submit" onclick="hideOrShowShadowing('add-topic-form')">Добавить тему</button>
            <div class="popup form-popup" id="add-topic-form">
                <form:form class="form-container" action="topics/add_topic" method="post">
                    <h3>Добавить тему</h3>
                    <input type="hidden" name="section_id" value="${section.sectionId}">
                    <input type="text" placeholder="Введите название" id="topic-title"
                        name="title" required><br>
                    <div class="buttons">
                        <button type="submit" class="add">Добавить</button>
                        <button type="reset" class="cancel" onclick="closeForm('add-topic-form')">Отмена</button>
                    </div>
                </form:form>
            </div>
            <c:if test="${not empty delete_topic_error}">
            	<p class="error-message">Не удалось удалить тему. Причина: ${delete_topic_error}</p>
            </c:if>
            <c:if test="${not empty add_topic_error}">
            	<p class="error-message">Не удалось создать тему. Причина: ${add_topic_error}</p>
            </c:if>
        </div>
    </div>
</body>
</html>