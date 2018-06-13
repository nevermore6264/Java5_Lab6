<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2018
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Staff</title>
</head>
<body>
<table border="1" class="table table-hover">
    <tr>
        <td>Mã NV</td>
        <td>Họ và tên</td>
        <td>Giới tính</td>
        <td>Phòng</td>
    </tr>
    <c:forEach var="s" items="${staffs}">
        <tr>
            <td>${s.getId()}</td>
            <td>${s.getName()}</td>
            <td>${s.isGender()?'Nam':'Nữ'}</td>
            <td>${s.getDeparts().getName()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
