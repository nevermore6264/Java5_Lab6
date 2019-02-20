<%@ page contentType="text/html; UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--Set configuration depending on the attribute "id"--%>
<c:choose>
    <c:when test="${empty progress.id}">
        <c:url var="actionUrl" value="submitProgressAdd"/>
        <c:set var="button" value="Создать"/>
        <c:set var="text" value="Для создания успеваемости заполните все поля и нажмите кнопку \"Создать\""/>
    </c:when>
    <c:when test="${!empty progress.id}">
        <c:url var="actionUrl" value="submitProgressAdd"/>
        <c:set var="button" value="Применить"/>
        <c:set var="text" value="Для модификации, введите новые значения и нажмите кнопку \"Применить\""/>
    </c:when>
</c:choose>
<%-- End Configurations--%>

<t:template>
    <t:navbar>
        <li><a href="progress?idStud=${student.id}&idSem=${semester.id}">Назад</a></li>
    </t:navbar>

    <div class="content-wrap">
        <main>

            <div class="service-text info">
                <c:out value="${text}"/>
            </div>

            <form:form  cssClass="createOrUpdate" modelAttribute="progress" method="post" action="${actionUrl}">
                <fieldset>
                    <legend>${semester.name}</legend>
                    <form:hidden path="id" value="${progress.id}"/>
                    <form:hidden path="student.id" value="${student.id}"/>
                    <input type="hidden" name="idSem" value="${semester.id}"/>
                    <label for="studentName" >Фамилия и Имя студента</label>
                    <input id="studentName" type="text" value="${student.secondName} ${student.firstName}" disabled="true"/>

                    <form:label path="discipline.id">Наименование предмета</form:label>
                    <form:select path="discipline.id" multiple="false">
                        <c:forEach items="${disciplineList}" var="discipline">
                            <form:option value="${discipline.id}">${discipline.name}</form:option>
                        </c:forEach>
                    </form:select><br/>

                    <form:label path="value">Укажите оценку</form:label>
                    <form:input path="value" type="number" value="4" min="1" max="5" step="1"/><br/>

                    <input type="submit" name="${button}" value="${button}"/>
                </fieldset>

                <form:errors path="student.id" cssClass="service-text error"/>
                <form:errors path="discipline.id" cssClass="service-text error"/>
                <form:errors path="value" cssClass="service-text error"/>
            </form:form>
        </main>
    </div>
</t:template>