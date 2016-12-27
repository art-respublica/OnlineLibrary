
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <style>
        ul.hr {
            margin: 0; /* Обнуляем значение отступов */
            padding: 4px; /* Значение полей */
        }
        ul.hr li {
            display: inline; /* Отображать как строчный элемент */
            margin-right: 5px; /* Отступ слева */
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${user != null}">
        <%--<jsp:useBean id="user" scope="page" type="ru.innopolis.uni.course3.model.User"/>--%>
        <ul class="hr">
            <li><a href="users?action=profile&userId=${user.getId()}">${user.getName()} (${user.getEmail()})</a></li>
            <li><span> | </span></li>
            <c:if test="${isLibrarian}">
                <li><a href="users">Users</a></li>
                <li><span> | </span></li>
            </c:if>
            <li><a href="users?action=logout">Logout</a></li>
        </ul>
    </c:when>
    <c:otherwise>
        <ul class="hr">
            <li>
            <form action="users?action=signin" method="post">
                <li><span>Email:</span></li>
                <li><input type="text" size=20 name="email"></li>
                <li><span>Password:</span></li>
                <li><input type="password" size=20 name="password"></li>
                <button type="submit">Sign In</button>
                <a href="users?action=signup">Sign Up</a>
            </form>
            </li>
        </ul>
    </c:otherwise>
</c:choose>



</body>
</html>
