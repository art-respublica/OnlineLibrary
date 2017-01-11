
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error</title>
</head>
<body>
Something went wrong. Try guess!
<br>
<br>
<span>Hint: ${message}</span>
<br>
<br>
<br>
<button onclick="window.history.back()">Return</button>
</body>
</html>
