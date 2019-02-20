<%@ tag description="Navigation Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="base" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>


<div class="nav-wrap">
    <nav>
        <ul>
            <li><a href="${base}">На главную</a></li>
            <jsp:doBody/>
            <li><a href="students">Students</a></li>
            <li><a href="disciplines">Disciplines</a></li>
            <li><a href="semesters">Semesters</a></li>
        </ul>
    </nav>
</div>
