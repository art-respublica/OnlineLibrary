
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Reading...</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
<p>We are reading the book ${title} by ${author}</p>
<br>
<button onclick="window.history.back()">Cancel</button>
</body>
</html>
