<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<section>
    <h3>Edit user</h3>
    <hr>
    <jsp:useBean id="user" type="ru.innopolis.uni.course3.model.User" scope="request"/>
    <form method="post" action="save">
        <input type="hidden" name="id" value="${user.getId()}">
        <input type="hidden" name="version" value="${user.getVersion()}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${user.getName()}" size=40 name="name"></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${user.getEmail()}" size=40 name="email"></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="password" value="${user.getPassword()}" size=40 name="password"></dd>
        </dl>
        <dl>
            <dt>Registered:</dt>
            <dd><input type="datetime" value="${user.getRegistered()}" name="registered" readonly></dd>
        </dl>
        <dl>
            <dt>Role:</dt>
            <dd>
                <select size="1" name="role">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:choose>
                            <c:when test="${user.isAdmin()}">
                                <option selected value="ROLE_ADMIN">Librarian</option>
                                <option value="ROLE_USER">Reader</option>
                            </c:when>
                            <c:otherwise>
                                <option selected value="ROLE_USER">Reader</option>
                                <option value="ROLE_ADMIN">Librarian</option>
                            </c:otherwise>
                        </c:choose>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('ROLE_ADMIN')">
                        <option selected value="ROLE_USER">Reader</option>
                    </sec:authorize>
                </select>
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</section>
</body>
</html>
