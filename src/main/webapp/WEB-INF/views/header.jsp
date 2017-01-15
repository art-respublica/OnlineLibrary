<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <c:url var="logoutURL" value="users/logout" />
    <c:url var="userProfileURL" value="users/profile" />
    <sec:authentication var="user" property="principal" />
    <ul class="hr">
        <li><a href="${userProfileURL}">${user}</a></li>
        <li><span> | </span></li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="users">Users</a></li>
            <li><span> | </span></li>
        </sec:authorize>
        <li><a href="${logoutURL}">Logout</a></li>
    </ul>
</sec:authorize>
<sec:authorize access="isAnonymous()">
    <c:url var="loginUrl" value="users/signin" />
    <c:url var="signUpURL" value="users/signup/new" />
    <ul class="hr">
        <li>
        <form name='loginForm' action="${loginUrl}" method="post">
            <li><span>Email:</span></li>
            <li><input type="text" size=20 name="email"></li>
            <li><span>Password:</span></li>
            <li><input type="password" size=20 name="password"></li>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit">Sign In</button>
            <a href="${signUpURL}">Sign Up</a>
        </form>
        </li>
    </ul>
</sec:authorize>
</body>
</html>
