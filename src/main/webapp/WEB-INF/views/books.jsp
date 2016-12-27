
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Books</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Books</h2>
<c:if test="${isLibrarian}">
    <a href="books?action=create">Add Book</a>
</c:if>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Author</th>
        <th>Title</th>
        <th>Year</th>
        <c:if test="${isLibrarian}">
            <th></th>
            <th></th>
        </c:if>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${books}" var="book">
        <jsp:useBean id="book" scope="page" type="ru.innopolis.uni.course3.model.Book"/>
        <tr>
            <td>${book.getAuthor()}</td>
            <td>${book.getTitle()}</td>
            <td>${book.getYear()}</td>
            <c:if test="${isLibrarian}">
                <td><a href="books?action=update&id=${book.getId()}">Update</a></td>
                <td><a href="books?action=delete&id=${book.getId()}">Delete</a></td>
            </c:if>
            <td><a href="books?action=read&id=${book.getId()}">Read</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

