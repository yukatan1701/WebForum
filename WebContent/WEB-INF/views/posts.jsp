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
	<title>Тема</title>
</head>

<body>
	<div id="shadowing" onclick="hideAll()"></div>
    <div class="content">
    	<%@ include file="parts/header.jsp" %>
    	<div class="sections">
            <h3><a href="${pageContext.servletContext.contextPath}/">Главная</a> | <a href="${pageContext.servletContext.contextPath}/topics?section_id=${topic.section.sectionId}">${topic.section.title}</a> | Сообщения в "${topic.title}"</h3>
            <ul>
                <c:forEach var="post" items="${postList}" varStatus="status">
	    			<li class="row">
	    				<div class="column left">
                            <div class="user">
                                <p class="username">${post.user.login}</p>
                                <p class="usertype">обычный</p>
                            </div>
                        </div>
                        <div class="column middle">
                            <div class="message-date">${post.datetime}</div>
                            <div class="message-text">${post.text}
                            	<!-- <img class="img-attachment" src="eclipse/WebForum/WebContent/files/krug.jpg" width="70%"> -->
                            </div>
                        </div>
                        <div class="column right">
	                		<button type="submit" class="message-delete" onclick="hideOrShowShadowing('delete-confirm-${post.postId}')"></button>
						</div>
						
	                   	<div class="popup delete-popup" id="delete-confirm-${post.postId}">
	                       	<h3>Подтверждение</h3>
	                       	<p>Вы действительно хотите удалить этот пост?</p>
	                       	<div class="buttons">
	                           	<button type="submit" class="a-delete"><a href="posts/delete_post?topic_id=${topic.topicId}&amp;post_id=${post.postId}">Да</a></button>
	                           	<button type="reset" class="cancel" onclick="closeForm('delete-confirm-${post.postId}')">Отмена</button>
	                       	</div>
	                   	</div>
	               </li>
              	</c:forEach>
            </ul>
            <div class="new-post">
                <h4>Добавить сообщение</h4>
                <form action="posts/add_post" method="post">
                	<input type="hidden" name="topic_id" value="${topic.topicId}"/>
                	<sec:authentication var="principal" property="principal" />
                	<input type="hidden" name="username" value="${principal.username}"/>
	                <textarea name="text" placeholder="Введите текст для нового сообщения."></textarea>
	                <button type="submit">Отправить</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>