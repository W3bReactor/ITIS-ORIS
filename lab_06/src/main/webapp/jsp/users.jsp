<%--
  Created by IntelliJ IDEA.
  User: W3bRe
  Date: 25.09.2026
  Time: 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Age</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${UsersForJsp}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
  </body>
</html>
