
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3>Edit user</h3>
    <hr>
    <jsp:useBean id="user" type="ru.innopolis.uni.course3.model.User" scope="request"/>
    <form method="post" action="users?action=save">
        <input type="hidden" name="id" value="${user.getId()}">
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
            <dd><input type="datetime-local" value="${meal.getRegistered()}" name="registered"></dd>
        </dl>
        <dl>
            <dt>Enabled:</dt>
            <dd><input type="checkbox" value="${user.isEnabled()}" name="enabled"></dd>
        </dl>
        <dl>
            <dt>Role:</dt>
            <dd><input type="text" value="${user.getRole()}" size=40 name="role"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
