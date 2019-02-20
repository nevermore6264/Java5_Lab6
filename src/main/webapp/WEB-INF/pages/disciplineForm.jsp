<%@ page contentType="text/html; UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--Set configuration depending on the attribute "id"--%>
<c:choose>
    <c:when test="${empty discipline.id}">
        <c:url var="actionUrl" value="submitDisciplineAdd"/>
        <c:set var="button" value="Создать"/>
        <c:set var="text" value="Для того чтобы создать дисциплину заполните все поля и нажмите кнопку \"Создать\""/>
    </c:when>
    <c:when test="${!empty discipline.id}">
        <c:url var="actionUrl" value="submitDisciplineUpdate"/>
        <c:set var="button" value="Применить"/>
        <c:set var="text" value="Для того чтобы модифицировать дисциплину, введите новое значение поля и нажмите кнопку \"Применить\""/>
    </c:when>
</c:choose>
<%-- End Configurations--%>

<t:template>
    <t:navbar>
        <li><a href="disciplines">Назад</a></li>
    </t:navbar>

    <div class="content-wrap">
        <main>
            <div class="service-text info">
                <c:out value="${text}"/>
            </div>

            <form:form  cssClass="createOrUpdate" modelAttribute="discipline" method="post" action="${actionUrl}">
                <form:hidden path="id" value="${discipline.id}"/>
                <form:label path="name">Название</form:label>
                <form:input path="name" type="text" value="${discipline.name}"/>

                <input type="submit" name="${button}" value="${button}"/>

                <form:errors cssClass="service-text error" path="name"/>
            </form:form>
        </main>
    </div>
</t:template>
