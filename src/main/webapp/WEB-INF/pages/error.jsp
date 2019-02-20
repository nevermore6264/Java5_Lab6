<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@page isErrorPage="true" %>--%>


<t:template>
  <div class="content"><h1>Ошибка ${pageContext.errorData.statusCode}!</h1>
    <h4>
      <c:forEach items="${pageContext.exception.stackTrace}" var="stack">
        <h4>${stack}</h4>
      </c:forEach>
    </h4>
  </div>


</t:template>