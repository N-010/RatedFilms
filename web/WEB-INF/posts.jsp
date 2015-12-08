<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 25.10.15
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guestbook posts</title>
</head>
<body>
<table border="1 ">
    <c:forEach items="${posts}" var="post">
        <tr>
            <td>${post.id}</td>
            <td><c:out value="${post.text}" /></td>
            <td>
                <a href="/delete?id=${post.id}">
                    delete
                </a>
            </td>
        </tr>
    </c:forEach>
    <form action="/add" method="POST">
        <tr>
            <td colspan="2">
                <input name="text" type="text">
            </td>
            <td>
                <input type="submit">
            </td>
        </tr>
    </form>

</table>
</body>
</html>
