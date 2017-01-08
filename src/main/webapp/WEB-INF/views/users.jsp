
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
<a href="books">Return to Books</a>
<br>
<h2>Users</h2>
<a href="users/create/new">Add User</a>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Registered</th>
        <th>Enabled</th>
        <th>Role</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" scope="page" type="ru.innopolis.uni.course3.model.User"/>
        <tr>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getRegistered()}</td>
            <td>${user.isEnabled()}</td>
            <td>${user.getRole()}</td>
            <td><a href="users/update/${user.getId()}">Update</a></td>
            <td><a href="users/delete/${user.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

