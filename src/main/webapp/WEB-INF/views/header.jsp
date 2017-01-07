
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<c:choose>
    <c:when test="${user != null}">
        <ul class="hr">
            <li><a href="${pageContext.request.contextPath}/users/profile/${user.getId()}">${user.toString()}</a></li>
            <li><span> | </span></li>
            <c:if test="${isLibrarian}">
                <li><a href="users">Users</a></li>
                <li><span> | </span></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/users/logout">Logout</a></li>
        </ul>
    </c:when>
    <c:otherwise>
        <ul class="hr">
            <li>
            <form action="${pageContext.request.contextPath}/users/signin" method="post">
                <li><span>Email:</span></li>
                <li><input type="text" size=20 name="email"></li>
                <li><span>Password:</span></li>
                <li><input type="password" size=20 name="password"></li>
                <button type="submit">Sign In</button>
                <a href="${pageContext.request.contextPath}/users/signup/new">Sign Up</a>
            </form>
            </li>
        </ul>
    </c:otherwise>
</c:choose>
</body>
</html>
