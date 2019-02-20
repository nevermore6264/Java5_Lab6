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

            <form:form id="selectDiscipline" action="" method="GET">
                <!-- Buttons container -->
                <div class="button-container right-aside">
                    <sec:authorize access="hasRole('admin')">
                        <input type="submit" formaction="addDiscipline" value="Создать дисциплину...">
                        <input type="submit" formaction="editDiscipline" value="Модифицировать выбранную дисциплину..." >
                        <input type="submit" formaction="deleteDiscipline" value="Удалить выбранную дисциплину" >
                    </sec:authorize>
                </div>

                <c:if test="${!empty disciplines}">
                    <!-- Discipline's list for not Admin users or anonimous-->
                    <sec:authorize access="not hasRole('admin')">
                    <div class="discipline-list">
                        <h3>Список предметов</h3>
                        <ul>
                            <li>Наименование</li>
                            <c:forEach items="${disciplines}" var="discipline">
                                <li>${discipline.name}</li>
                            </c:forEach>
                        </ul>
                    </div>
                    </sec:authorize>

                    <!-- Table with discipline's information and checkboxes for admin-->
                    <sec:authorize access="hasRole('admin')">
                    <div class="table discipline-table with-radio-input">
                        <h1 class="caption">Список предметов</h1>
                        <div class="thead trow">
                            <div></div>
                            <div>Наименование дисциплины</div>
                        </div>
                        <div class="tbody">
                            <c:forEach items="${disciplines}" var="discipline">
                                <div class="trow">
                                        <div><input type="radio" name="idDiscipline" value="${discipline.id}"/></div>
                                    <div>${discipline.name}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    </sec:authorize>
                </c:if>
            </form:form>
        </main>
    </div>
</t:template>
