<%--<!DOCTYPE html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery.dataTables.css"/>"/>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Books</h2>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="books/create/new">Add Book</a>
    <br>
    <br>
</sec:authorize>
<table id="books" class="display">
    <thead>
    <tr>
        <th>Author</th>
        <th>Title</th>
        <th>Year</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <jsp:useBean id="book" scope="page" type="ru.innopolis.uni.course3.model.Book"/>
        <tr>
            <td>${book.getAuthor()}</td>
            <td>${book.getTitle()}</td>
            <td>${book.getYear()}</td>
            <td><a href="books/read/${book.getId()}">Read</a></td>
            <td><a href="books/update/${book.getId()}">Update</a></td>
            <td><a href="books/delete/${book.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/js/jquery-1.12.4.js"/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <script>var access=true;</script>
</sec:authorize>
<sec:authorize access="!hasRole('ROLE_ADMIN')">
    <script>var access=false;</script>
</sec:authorize>
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/js/bookDatatables.js"/>"></script>
</body>
</html>

