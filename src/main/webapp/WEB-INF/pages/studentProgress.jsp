<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
    <t:navbar>
        <li><a href="students">Назад</a></li>
    </t:navbar>

    <div class="content-wrap">
        <main>
            <c:if test="${!empty info}">
                <div class="service-text info">${info}</div>
            </c:if>

            <form id="showStudentProgress" action="" method="GET">
                <input type="hidden" name="idStud" value="${student.id}"/>
                <!-- Current student's data at the table -->
                <div class="table student-table">
                    <h1 class="caption">Отображена успеваемость для следующего студента:</h1>
                    <div class="thead trow">
                        <div>Фамилия</div>
                        <div>Имя</div>
                        <div>Группа</div>
                        <div>Дата поступления</div>
                    </div>
                    <div class="tbody">
                        <div class="trow">
                            <div>${student.secondName}</div>
                            <div>${student.firstName}</div>
                            <div>${student.group}</div>
                            <div>
                                <fmt:formatDate var = "formatDateEntrance" value="${student.entranceDate}" pattern="dd/MM/yyyy"/>
                                <c:out value="${formatDateEntrance}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${!empty semesters}">
                <div class="service-block student-progress-service">
                    <input class="selectButton" type="submit" formaction="progress" value="Выбрать">
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

                    <c:if test="${!empty avaragePoint}">
                        <div class="avarage-data">Средняя оценка за семестр: <span>${avaragePoint}</span> ${wordBally}</div>
                    </c:if>
                    <sec:authorize access="hasRole('admin')">
                    <input type="submit" value="Добавить успеваемость..." formaction="addProgress">
                    </sec:authorize>
                </div>
                </c:if>
                <c:if test="${!empty progressPoints}">
                    <div class="table discipline-table">
                        <h1 class="caption">Список предметов</h1>
                        <div class="thead trow">
                            <div>Наименование дисциплины</div>
                            <div>Оценка</div>
                        </div>
                        <div class="tbody">
                            <c:forEach items="${progressPoints}" var="points">
                                <div class="trow">
                                    <div>${points.key.name}</div>
                                    <div>${points.value}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </form>
        </main>
    </div>
</t:template>