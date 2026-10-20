package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
ДЗ_3:
1) Используя ООП, сервлеты и WEB реализовать добавление нескольких пользователей в таблицу, с указанием количества добавляемых пользователей из БД (способ откуда вы это число возьмете придумайте сами, главное чтобы из БД).
3)Добавить 3 доп. поля в таблицу и реализовать 3 метода по выводу select запросов по каждому из этих полей, с методом WHERE (используя отдельный сервлет с консолью)
Дедлайн 21.10.2026 00:15

После сохранения сведения о том, сколько пользователей было добавлено не надо на странице выводить, достаточно выполнить редирект на другую страницу, любую

То есть,
1)перешли на страницу /save
2) взяли число из бд и создали столько полей ввода
3) заполнили и сохранили
4) выполнили редирект, можно даже на заготовку html где будет написано :"Пользователи добавлены"

*/

@WebServlet("/")
public class RootServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/root.jsp").forward(req, resp);
    }
}
