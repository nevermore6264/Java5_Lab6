<%@ page contentType="text/html; UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--Set configuration depending on the attribute "id"--%>
<c:choose>
    <c:when test="${empty semester.id}">
        <c:url var="actionUrl" value="submitSemesterAdd"/>
        <c:set var="button" value="Создать"/>
        <c:set var="text" value="Для создания семестра заполните следующие данные и нажмите кнопку \"Создать\""/>
    </c:when>
    <c:when test="${!empty semester.id}">
        <c:url var="actionUrl" value="submitSemesterUpdate"/>
        <c:set var="button" value="Применить"/>
        <c:set var="text" value="Для модификации семестра отредактируйте данные и нажмите кнопку \"Применить\""/>
    </c:when>
</c:choose>
<%-- End Configurations--%>

<t:template>
    <t:navbar>
        <li><a href="semesters">Назад</a></li>
    </t:navbar>

    <div class="content-wrap">
        <main>
            <div class="service-text info">
                <c:out value="${text}"/>
            </div>

            <form:form  cssClass="createOrUpdate" modelAttribute="semester" method="post" action="${actionUrl}">
                <form:hidden path="id" value="${semester.id}"/>

                <form:label path="name">Название семестра</form:label>
                <form:input path="name" type="text" value="${semester.name}"/>

                <form:label path="duration">Длительность(в неделях)</form:label>
                <form:input path="duration" type="text" value="${semester.duration}"/>

                <form:label path="disciplineList">Дисциплины в семестре</form:label>
                <form:select path="disciplineList" multiple="true" size="8">
                    <c:forEach items="${disciplines}" var="discipline">
                        <c:if test="${fn:contains(selected, discipline.name)}">
                            <form:option value="${discipline.id}" selected="selected">${discipline.name}</form:option>
                        </c:if>
                        <c:if test="${!fn:contains(selected, discipline.name)}">
                            <form:option value="${discipline.id}">${discipline.name}</form:option>
                        </c:if>
                    </c:forEach>
                </form:select>

                <input type="submit" name="${button}" value="${button}"/>

                <form:errors cssClass="service-text error" path="name"/>
                <form:errors cssClass="service-text error" path="duration"/>
                <form:errors cssClass="service-text error"  path="disciplineList"/>
            </form:form>

        </main>
    </div>
</t:template>
