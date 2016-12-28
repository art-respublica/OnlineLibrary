
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Reading...</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br>
<h2>${title} - ${author}</h2>
<br>
<span>${text}</span>
<br>
<br>
<br>
<button onclick="window.history.back()">Cancel</button>
</body>
</html>
