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
        	<div class="user-page">
                <h3>${user.login}</h3>
                <p><b>Дата регистрации: </b>${user.dateOfRegistration}</p>
                <p><b>Права: </b>${permissions}</p>
                <p><b>Статус: </b>${status}</p>
                <sec:authentication var="principal" property="principal" />
                <form method="post" action="user/block">
                	<input type="hidden" value="${user.userId}" name="id">
                	<input type="hidden" value="${principal.username}" name="login">
                    <button type="submit">${blockButtonText}</button>
                </form>
                <c:if test="${not empty block_user_error}">
                	<p class="error-message">Не удалось заблокировать пользователя. Причина: ${block_user_error}</p>
                </c:if>
                <a href="${pageContext.servletContext.contextPath}/">На главную</a>
            </div>
        </div>
    </body>
</html>