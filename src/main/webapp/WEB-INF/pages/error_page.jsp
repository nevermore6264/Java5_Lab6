<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <t:navbar>

    </t:navbar>

    <div class="content-wrap">
        <main>
            <h2>Oops! An Error Occurred!</h2>
            <h3>The server returned  a "
                <c:if test="${not empty errMsg}">
                     ${errMsg}
                </c:if>
                ".
            </h3>
            <p>
                Something is broken. Please let us know what you were doing when this error occurred.
                We will fix it as soon as possible.
                Sorry for any inconvenience caused.
            </p>
        </main>
    </div>
</t:template>
