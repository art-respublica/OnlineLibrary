<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<c:if test="${error}">
    <div id="error">
        <br>Invalid email or password</br>
        <br>
        <%--${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}--%>
        <%--<%=((Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage()%>;--%>
    </div>
</c:if>
<c:if test="${not empty message}">
    <div class="message">
        <spring:message code="${message}"/>
    </div>
</c:if>
<c:url var="loginUrl" value="users/signin" />
<form action="${loginUrl}" method="post">
    Email: <input type="text" name="email"/>
    Password: <input type="password" name="password"/>
    <button type="submit">Sing In</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>
