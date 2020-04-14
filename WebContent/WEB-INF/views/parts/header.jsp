<%@page contentType="text/html" pageEncoding="UTF-8"%>
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