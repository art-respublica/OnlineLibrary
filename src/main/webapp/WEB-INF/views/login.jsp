
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<form action="users?action=signin" method="post">
    Email: <input type="text" name="email"/>
    Password: <input type="password" name="password"/>
    <button type="submit">Sing In</button>
</form>
</body>
</html>
