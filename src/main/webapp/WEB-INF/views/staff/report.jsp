<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2018
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Report</title>
</head>
<body>
<table border="1" class="table table-hover">
    <tr>
        <th>Mã NV</th>
        <th>Tổng thành tích</th>
        <th>Tổng kỷ luật</th>
        <th>Tổng kết</th>
    </tr>
    <c:forEach var="a" items="${arrays}">
    <tr>
        <td>${a[0]}</td>
        <td>${a[1] }</td>
        <td>${a[2]}</td>
        <td>${a[1] * a[2]}</td>
    </tr>
    </c:forEach>
</body>
</html>
