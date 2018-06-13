<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2018
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Insert</title>
</head>
<body>
<form:form action="/user/index">
    <button>Back to index</button>
</form:form>
<form:form action="/user/insert" modelAttribute="user">
    <div>
        <label>Username</label>
        <form:input path="userName"/>
    </div>
    <div>
        <label>Password</label>
        <form:input path="passWord"/>
    </div>
    <div>
        <label>Fullname</label>
        <form:input path="fullName"/>
    </div>
    <div>
        <button class="btn btn-default">Insert</button>
    </div>
    <h4>${message}</h4>
</form:form>
</body>
</html>
