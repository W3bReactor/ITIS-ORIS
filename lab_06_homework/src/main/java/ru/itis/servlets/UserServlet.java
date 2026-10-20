package ru.itis.servlets;

import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/save")
public class UserServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ITIS_homework";

    private static final String SELECT_ALL_FROM_USERS = "SELECT * FROM users";
    private static final String SELECT_COUNT_USERS_ADD_FROM_CONSTS = "SELECT count_users_add FROM consts";
    private static final String INSERT_INTO_USERS = "INSERT INTO users (name, surname, age, favorite_food, favorite_car, profession) VALUES (?, ?, ?, ?, ?, ?);";

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
        // Получаем пользователей
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USERS);
            while (resultSet.next()) {
                users.add(
                        User.builder()
                                .id(resultSet.getLong("id"))
                                .name(resultSet.getString("name"))
                                .surname(resultSet.getString("surname"))
                                .age(resultSet.getInt("age"))
                                .favoriteFood(resultSet.getString("favorite_food"))
                                .favoriteCar(resultSet.getString("favorite_car"))
                                .profession(resultSet.getString("profession"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // вставляем в атрибут для jsp
        req.setAttribute("Users", users);
        // получаем кол-во пользователей, которое надо добавить
        Integer currentUsersAdd = 1;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_COUNT_USERS_ADD_FROM_CONSTS);
            while (resultSet.next()) {
                currentUsersAdd = resultSet.getInt("count_users_add");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("CountUsersAdd", currentUsersAdd);
        req.getRequestDispatcher("/jsp/save.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String[] names = req.getParameterValues("name");
        String[] surnames = req.getParameterValues("surname");
        String[] ages = req.getParameterValues("age");
        String[] favoriteFoods = req.getParameterValues("favorite_food");
        String[] favoriteCars = req.getParameterValues("favorite_car");
        String[] professions = req.getParameterValues("profession");

        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                String surname = surnames[i];
                Integer age = Integer.parseInt(ages[i]);
                String favoriteFood = favoriteFoods[i];
                String favoriteCar = favoriteCars[i];
                String profession = professions[i];

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setInt(3, age);
                    preparedStatement.setString(4, favoriteFood);
                    preparedStatement.setString(5, favoriteCar);
                    preparedStatement.setString(6, profession);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }

        }


        req.getRequestDispatcher("/jsp/successfull.jsp").forward(req, resp);

    }

}
