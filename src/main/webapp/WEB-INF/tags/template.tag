<%@ tag description="Template Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Student manager</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
    <link href="<c:url value="/resources/c/style.css"/>" rel="stylesheet">
</head>
<body>
<div class="header-wrap">
    <header>
        <div class="appName">Система управления студентами и их успеваемостью</div>
        <div class="auth">
            <sec:authorize access="isAnonymous()">
                <a href="spring_security_login">LogIn</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <h4>Welcome, ${pageContext.request.userPrincipal.name}!</h4>
                <a href="j_spring_security_logout">LogOut</a>
            </sec:authorize>
        </div>
    </header>
</div>

<jsp:doBody/>

</body>
</html>
