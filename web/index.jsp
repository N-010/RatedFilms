<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.10.15
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="CSS/style.css">
    <link rel="stylesheet" href="CSS/search.css">
    <title>Rated films</title>
</head>
<body>
<header>
    <form action="/result" method="post" id="search" class="cf">
        <button type="submit">Найти</button>
        <input name="search" placeholder="Поиск" type="text">
    </form>
</header>
<div id="body">
    <c:forEach items="${requestScope.filmList}" var="filmInf">
        <div class="rated">
            <div class="img">
                <img width="166" height="235" src=<c:out value="${filmInf.getImg()}"/>>
            </div>
            <div class="title">
                <a href="/film?id=<c:out value="${filmInf.getId()}"/>">
                    <c:out value="${filmInf.getTitle()}"/>
                </a>
            </div>
            <div class="description">
                <c:out value="${filmInf.getDescription()}"/>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
