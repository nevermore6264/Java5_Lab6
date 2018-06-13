<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/1/2018
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Insert</title>
</head>
<body>

<form action="/user/insert" method="get">
    <button>Insert</button>
</form>

<table class="table table-hover" border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Fullname</th>
        <th></th>
    </tr>

    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.getUserName()}</td>
            <td>${u.getPassWord()}</td>
            <td>${u.getFullName()}</td>
            <td><a href="/delete/${u.getUserName()}">Delete</a></td>
        </tr>
    </c:forEach>

</table>

</body>