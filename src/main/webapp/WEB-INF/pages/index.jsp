<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
    <t:navbar/>
  <div class="content-wrap">
    <main>
      <div class="category students"><a href="students">students</a></div>
      <div class="category disciplines"><a href="disciplines">disciplines</a></div>
      <div class="category semesters"><a href="semesters">semesters</a></div>
    </main>
  </div>
</t:template>