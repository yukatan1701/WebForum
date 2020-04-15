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
        	<div class="user-list">
        		<h3><a href="${pageContext.servletContext.contextPath}/">Главная</a> | Списки пользователей<h3>
                <h4>По активности в период ${dateBegin} - ${dateEnd} (<button class="date-change" onclick="hideOrShowShadowing('date-form')">изменить</button>): </h4>
                
                <div class="popup form-popup date-popup" id="date-form">
                	<h3>Выберите дату начала и дату конца периода:</h3>
                	<form action="user_lists" method="post">
		            	<input type="date" name="date_begin" value="2015-01-01">
		            	<input type="date" name="date_end" value="2016-01-01">
		            	<div class="buttons">
                            <button type="submit" class="add">Отправить</button>
                            <button type="reset" class="cancel" onclick="closeForm('date-form')">Отмена</button>
                        </div>
                	</form>
                </div>
                
                <ol>
                	<c:forEach var="pair" items="${userMap}" varStatus="status">
	                    <li><a href="user?id=${pair.key.userId}">${pair.key.login}</a> (${pair.value} сообщений)</li>
                    </c:forEach>
                </ol>
                <h4>По участию в разделах:</h4>
                <ul>
                	<c:forEach var="pair" items="${usersBySectionsList}" varStatus="status">
                    	<li><b>${pair.key.title}</b>:
                    		<c:forEach var="userInSection" items="${pair.value}" varStatus="loop">
                    			<a href="user?id=${userInSection.userId}">${userInSection.login}${!loop.last ? ',' : ''}</a>
                    		</c:forEach>
                    	</li>
                    </c:forEach>
                </ul>
                <h4>Все пользователи:</h4>
                <p class="link-container">
	                <c:forEach var="user" items="${userList}" varStatus="status">
	                	<a href="user?id=${user.userId}">${user.login}</a>
	                </c:forEach>
                </p>
            </div>
        </div>
    </body>
</html>