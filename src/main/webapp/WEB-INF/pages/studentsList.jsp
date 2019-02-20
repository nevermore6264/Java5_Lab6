<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url var="base" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>


<t:template>
    <t:navbar>
        <li><a href="${base}">Назад</a></li>
    </t:navbar>
    <div class="content-wrap">
        <main>
            <c:choose>
                <c:when test="${!empty info}">
                    <div class="service-text info">${info}</div>
                </c:when>
                <c:when test="${!empty error}">
                    <div class="service-text error">${error}</div>
                </c:when>
            </c:choose>


            <form:form id="selectStudent" action="" method="GET">

                <!-- Buttons container -->
                <div class="button-container full">
                    <input type="submit" formaction="progress" value="Просмотреть успеваемость выбранного студента" >
                    <sec:authorize access="hasRole('admin')">
                        <input type="submit" formaction="addStudent" value="Создать студента...">
                        <input type="submit" formaction="editStudent" value="Модифицировать выбранного студента..." >
                        <input type="submit" formaction="deleteStudent" value="Удалить выбранных студентов" >
                    </sec:authorize>
                </div>

                <c:if test="${!empty students}">
                <!-- Table with student's information and checkboxes -->
                <div class="table student-table with-radio-input">
                    <h1 class="caption">Список студентов</h1>
                    <div class="thead trow">
                        <div></div>
                        <div>Фамилия</div>
                        <div>Имя</div>
                        <div>Группа</div>
                        <div>Дата поступления</div>
                    </div>
                    <div class="tbody">
                        <c:forEach items="${students}" var="student">
                            <div class="trow">
                                <div><input type="radio" name="idStud" value="${student.id}"/></div>
                                <div>${student.secondName}</div>
                                <div>${student.firstName}</div>
                                <div>${student.group}</div>
                                <div><fmt:formatDate value="${student.entranceDate}" type="date" pattern='dd/MM/yyyy'/></div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                </c:if>
            </form:form>
        </main>
    </div>
</t:template>

