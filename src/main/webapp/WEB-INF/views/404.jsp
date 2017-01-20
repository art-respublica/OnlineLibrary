<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>404!</title>
</head>
<body>
<div class="http-error-container">
    <h4>HTTP Status 404 - Page Not Found</h4>
    <p class="message-text">The page you requested is not available. You might try returning to the <a href="<c:url value="/books"/>">Books</a> page.</p>
</div>
</body>
</html>
