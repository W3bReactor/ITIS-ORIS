package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ITIS_homework";

    private static final String UPDATE_CONSTS_COUNT_USERS_ADD = "UPDATE consts SET count_users_add = ?";

    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            // ищем драйвер из томката
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
//           Если драйвера нет - выкидываем ошибку
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newCountAddUsers = req.getParameter("count_users_add");
        if(newCountAddUsers != null && !newCountAddUsers.trim().isEmpty()) {
            int newCountAddUsersInt = Integer.parseInt(newCountAddUsers);
            if(newCountAddUsersInt > 0) {
                try {
                    PreparedStatement statement = connection.prepareStatement(UPDATE_CONSTS_COUNT_USERS_ADD);
                    statement.setInt(1, newCountAddUsersInt);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("Введите число больше 0");
            }
        }
        resp.sendRedirect("/settings");
    }

}
