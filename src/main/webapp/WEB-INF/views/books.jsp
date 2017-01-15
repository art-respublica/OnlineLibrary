<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Books</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Books</h2>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="books/create/new">Add Book</a>
</sec:authorize>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Author</th>
        <th>Title</th>
        <th>Year</th>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <th></th>
            <th></th>
        </sec:authorize>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${books}" var="book">
        <jsp:useBean id="book" scope="page" type="ru.innopolis.uni.course3.model.Book"/>
        <tr>
            <td>${book.getAuthor()}</td>
            <td>${book.getTitle()}</td>
            <td>${book.getYear()}</td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td><a href="books/update/${book.getId()}">Update</a></td>
                <td><a href="books/delete/${book.getId()}">Delete</a></td>
            </sec:authorize>
            <td><a href="books/read/${book.getId()}">Read</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

