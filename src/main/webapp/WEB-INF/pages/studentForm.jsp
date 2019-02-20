<%@ page import="com.julie.studentmanager.domain.Student" %>
<%@ page import="com.julie.studentmanager.controller.StudentsListController" %>
<%@ page contentType="text/html; UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<%--Set configuration depending on the attribute "id"--%>
<c:choose>
    <c:when test="${empty student.id}">
        <c:url var="actionUrl" value="submitStudentAdd"/>
        <c:set var="button" value="Создать"/>
        <c:set var="text" value="Для создания студента заполните все поля и нажмите кнопку \"Создать\""/>
    </c:when>
    <c:when test="${!empty student.id}">
        <c:url var="actionUrl" value="submitStudentUpdate"/>
        <c:set var="button" value="Применить"/>
        <c:set var="text" value="Для модификации, введите новые значения и нажмите кнопку \"Применить\""/>
    </c:when>
</c:choose>
<%-- End Configurations--%>

<t:template>
    <t:navbar>
        <li><a href="students">Назад</a></li>
    </t:navbar>

  <div class="content-wrap">
    <main>

      <div class="service-text info">
        <c:out value="${text}"/>
      </div>

        <form:form  cssClass="createOrUpdate" modelAttribute="student" method="post" action="${actionUrl}">

            <form:hidden path="id" value="${student.id}"/>
            <form:label path="secondName" >Фамилия</form:label>
            <form:input path="secondName" type="text" value="${student.secondName}"/><br/>

            <form:label path="firstName">Имя</form:label>
            <form:input path="firstName" type="text" value="${student.firstName}"/><br/>

            <form:label path="group">Группа</form:label>
            <form:input path="group" type="text" value="${student.group}"/><br/>

            <form:label path="entranceDate">Дата поступления</form:label>
              <fmt:formatDate value="${student.entranceDate}" var="formatDate" pattern="dd/MM/yyyy"/>
            <form:input path="entranceDate" type="date" value="${formatDate}" placeholder="dd/MM/yyyy"/>

              <input type="submit" name="${button}" value="${button}"/>



            <form:errors cssClass="service-text error" path="secondName"/>
            <form:errors cssClass="service-text error" path="firstName"/>
            <form:errors cssClass="service-text error"  path="group"/>
            <form:errors cssClass="service-text error"  path="entranceDate"/>

        </form:form>
    </main>
  </div>
</t:template>
