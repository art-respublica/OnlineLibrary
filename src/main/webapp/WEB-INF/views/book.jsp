
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3>Edit book</h3>
    <hr>
    <jsp:useBean id="book" type="ru.innopolis.uni.course3.model.Book" scope="request"/>
    <form method="post" action="books">
        <input type="hidden" name="id" value="${book.getId()}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${book.getAuthor()}" size=40 name="author"></dd>
        </dl>
        <dl>
            <dt>Title:</dt>
            <dd><input type="text" value="${book.getTitle()}" size=40 name="title"></dd>
        </dl>
        <dl>
            <dt>Year:</dt>
            <dd><input type="number" value="${book.getYear()}" name="year"></dd>
        </dl>
        <dl>
            <dt>Text:</dt>
            <dd><input type="text" value="${book.getText()}" size=40 name="text"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
