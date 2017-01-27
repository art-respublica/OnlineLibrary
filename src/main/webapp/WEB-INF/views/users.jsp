<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.css"/>"/>
    <%--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">--%>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
<a href="books">Return to Books</a>
<br>
<h2>Users</h2>
<a href="users/create/new">Add User</a>
<br>
<br>
<table id="users" class="display">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Registered</th>
        <th>Enabled</th>
        <th>Roles</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" scope="page" type="ru.innopolis.uni.course3.model.User"/>
        <tr>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <td><fmt:formatDate value="${user.getRegistered()}" pattern="dd.MM.yyyy hh:mm"/></td>
            <td>${user.isEnabled()}</td>
            <td>${user.getRoles()}</td>
            <td><a href="users/update/${user.getId()}">Update</a></td>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name.equals(user.getEmail())}">
                    <td/>
                </c:when>
                <c:otherwise>
                    <td><a href="users/delete/${user.getId()}">Delete</a></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/js/jquery-1.12.4.js"/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
<%--<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>--%>
<%--<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>--%>
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/js/userDatatables.js"/>"></script>
</body>
</html>

