<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.10.15
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="CSS/style.css">
    <title>Rated films</title>
</head>
<body>
<header></header>
<div id="body">
    <c:forEach begin="0" end="5" step="1">
        <div class="rated">
            <div class="img">
                <img width="166" height="235" src="/img/tmp.png">
            </div>
            <div class="title">
                Побег из Шоушенка (1994)
            </div>
            <div class="description">
                Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего
                фабриканта, спасшего во время Второй мировой войны почти 1200 евреев.
                Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего
                фабриканта, спасшего во время Второй мировой войны почти 1200 евреев.
                Фильм рассказывает реальную историю загадочного Оскара Шиндлера, члена нацистской партии, преуспевающего
                фабриканта, спасшего во время Второй мировой войны почти 1200 евреев.
            </div>
        </div>
    </c:forEach>

</div>
</body>
</html>
