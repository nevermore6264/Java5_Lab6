<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/8/2018
  Time: 8:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Records</title>
</head>
<body>
${message}
<form:form action="/record/insert" modelAttribute="record">
    <div>
        <label>Nhân viên</label>
        <form:select path="staffs.id"
                     items="${staffs}" itemValue="id" itemLabel="name"/>
    </div>
    <div>
        <label>Loại</label>
        <form:radiobutton path="type" value="true" label="Thành tích"/>
        <form:radiobutton path="type" value="false" label="Kỷ luật"/>
    </div>
    <div>
        <label>Lý do</label>
        <form:textarea path="reason" rows="3"/>
    </div>
    <div>
        <button>Insert</button>
    </div>
</form:form>

</body>
</html>
