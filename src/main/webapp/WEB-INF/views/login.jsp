
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/users/signin" method="post">
    Email: <input type="text" name="email"/>
    Password: <input type="password" name="password"/>
    <button type="submit">Sing In</button>
</form>
</body>
</html>
