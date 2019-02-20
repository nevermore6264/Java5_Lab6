<%@ page contentType="text/html; UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

            <form:form id="selectSemester" action="" method="GET">
                <!-- Buttons container -->
                <div class="button-container right-aside">
                    <sec:authorize access="hasRole('admin')">
                        <input type="submit" formaction="addSemester" value="Создать семестр...">
                        <input type="submit" formaction="editSemester" value="Модифицировать выбранный семестр..." >
                        <input type="submit" formaction="deleteSemester" value="Удалить выбранный семестр" >
                    </sec:authorize>
                </div>

                <c:if test="${!empty semesters}">
                <!-- Table with semester's information and checkboxes -->
                <div class="table semester-table">
                    <div class="tcol"></div>

                    <div class="tbody trow">
                        <div class="inputContains">
                            <input class="selectButton" type="submit" formaction="semesters" value="Выбрать">
                        </div>
                        <div>
                            <select name="idSem">
                                <c:forEach items="${semesters}" var="semester">
                                    <c:if test="${semester.id == selectedSemester.id}">
                                        <option value="${semester.id}" selected>${semester.name}</option>
                                    </c:if>
                                    <c:if test="${semester.id != selectedSemester.id}">
                                    <option value="${semester.id}">${semester.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="tbody trow">
                        <div>Наименование</div>
                        <div>${selectedSemester.name}</div>
                    </div>
                    <div class="tbody trow">
                        <div>Длительность</div>
                        <div>${selectedSemester.duration} ${nedeli}</div>
                    </div>
                </div>
                    <c:if test="${!empty selectedSemester.disciplineList}">
                <div class="discipline-list">
                    <h3>Список предметов</h3>
                    <ul>
                        <li>Наименование</li>
                        <c:forEach items="${selectedSemester.disciplineList}" var="discipline">
                        <li>${discipline.name}</li>
                        </c:forEach>
                    </ul>
                </div>
                    </c:if>
                </c:if>
            </form:form>
        </main>
    </div>



    <%--<div class="content">--%>
        <%--<form:form name="semesterForm" action="" method="get">--%>
            <%--<c:if test="${!empty semesters}">--%>
            <%--<div>--%>
                <%--<label>Выбрать семестр</label>--%>
                <%--<select name="idSem">--%>
                    <%--<c:forEach items="${semesters}" var="semester">--%>
                        <%--<c:if test="${semester.id != semester.id}">--%>
                            <%--<option onselect="changeSemestr(${semester.id})" value="${semester.id}">${semester.name}</option>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${semester.id == semester.id}">--%>
                            <%--<option  value="${semester.id}" selected>${semester.name}</option>--%>
                        <%--</c:if>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<input class="button choise-button" type="button" onclick="choiseSemestr()" value="Выбрать">--%>
            <%--</div>--%>
            <%--<h4>Длительность семестра: ${semester.duration} ${nedeli}</h4>--%>
            <%--<h4>Сисок дисциплин семестра</h4>--%>
            <%--<div class="discipline">--%>
                <%--<table>--%>
                    <%--<tr>--%>
                        <%--<th>Наименование дисциплины</th>--%>
                    <%--</tr>--%>
                    <%--<c:forEach items="${semester.disciplineList}" var="disc">--%>
                        <%--<tr>--%>
                            <%--<td>${disc.name}</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                <%--</table>--%>
            <%--</div>--%>
            <%--</c:if>--%>
        <%--</form:form>--%>
    <%--</div>--%>
</t:template>
