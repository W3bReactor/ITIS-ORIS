<%--
  Created by IntelliJ IDEA.
  User: W3bRe
  Date: 16.10.2026
  Time: 0:30
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
            <h1>Настройки</h1>
            <form action="/settings" method="post">
                <label for="count">Кол-во пользователей которое можно будет добавить за раз (в <a href="/save">/save</a>):</label>
                <input type="text" id="count" name="count_users_add">
                <input type="submit" value="Отправить">
            </form>
        </section>
    </main>
</body>
</html>
