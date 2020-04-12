<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/styles.css" />
        <title>Авторизация</title>
    </head>
    <body>
        <div class="login-content">
            <h1>Добро пожаловать на форум!</h1>
            <h2>Авторизуйтесь, чтобы продолжить</h2>
            <c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
            <form name="login" method="POST" action="<c:url value='j_spring_security_check'/>">
                <input type="text" name="j_username" placeholder="Логин" /><br>
                <input type="password" name="j_password" placeholder="Пароль" /><br>
                <button name="submit" type="submit">Войти</button>
                <!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> -->
            </form>
        </div>
    </body>
</html>