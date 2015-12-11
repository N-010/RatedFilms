<%@ page import="com.mysoft.Main" %>
<%@ page import="com.mysoft.FilmInformation" %>
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

    <c:forEach items="<%=Main.test()%>" var="fi">


        <!--
        <div class="rated">
            <div class="img">
                <img width="166" height="235" src="/img/tmp.png">
            </div>
            <div class="title">

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
        -->
    </c:forEach>

</div>
</body>
</html>
