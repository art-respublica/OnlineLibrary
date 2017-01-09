
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <dd><input type="datetime-local" value="${user.getRegistered()}" name="registered"></dd>
        </dl>
        <dl>
            <dt>Role:</dt>
            <dd>
                <select size="1" name="role">
                    <c:choose>
                        <c:when test="${isLibrarian && 'ROLE_LIBRARIAN'.equals(user.getRole())}">
                            <option selected value="ROLE_LIBRARIAN">Librarian</option>
                            <option value="ROLE_READER">Reader</option>
                        </c:when>
                        <c:when test="${isLibrarian && 'ROLE_READER'.equals(user.getRole())}">
                            <option selected value="ROLE_READER">Reader</option>
                            <option value="ROLE_LIBRARIAN">Librarian</option>
                        </c:when>
                        <c:otherwise>
                            <option selected value="ROLE_READER">Reader</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
