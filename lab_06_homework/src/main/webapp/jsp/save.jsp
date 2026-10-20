<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: W3bRe
  Date: 15.10.2026
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <header>
        <nav>
            <a href="/save">Создание пользователей</a>
            <a href="/settings">Настройки</a>
        </nav>
    </header>
    <main>
        <section>
            <h1>Текущие пользователи:</h1>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Age</th>
                    <th>Profession</th>
                    <th>Favorite car</th>
                    <th>Favorite food</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${Users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.age}</td>
                        <td>${user.profession}</td>
                        <td>${user.favoriteCar}</td>
                        <td>${user.favoriteFood}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </section>
        <section>
            <h2>Добавление пользователей</h2>
            <form action="/save" method="post">
                <c:forEach begin="1" end="${CountUsersAdd}" varStatus="status">
                    <div>
                        <h3>Пользователь №${status.count}</h3>
                        <input type="text" name="name" placeholder="Имя">
                        <input type="text" name="surname" placeholder="Фамилия">
                        <input type="text" name="age" placeholder="Возраст">
                        <input type="text" name="profession" placeholder="Профессия">
                        <input type="text" name="favorite_car" placeholder="Любимая машина">
                        <input type="text" name="favorite_food" placeholder="Любимая еда">
                    </div>
                </c:forEach>
                <input type="submit" value="Отправить">
            </form>
        </section>
    </main>

</body>
</html>
