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
import java.util.Scanner;


// Задание 2
@WebServlet("/search-console")
public class SearchConsoleServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "123123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ITIS_homework";


    private static final String SELECT_ALL_FROM_USERS_WHERE_FAVORITE_CAR = "SELECT * FROM users WHERE favorite_car = ?";
    private static final String SELECT_ALL_FROM_USERS_WHERE_FAVORITE_FOOD = "SELECT * FROM users WHERE favorite_food = ?";
    private static final String SELECT_ALL_FROM_USERS_WHERE_PROFESSION = "SELECT * FROM users WHERE profession = ?";

    private Connection connection;

    @Override
    public void init() throws ServletException {


        // Явно переустанавливаем стандартный вывод консоли на UTF-8
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
            System.setErr(new java.io.PrintStream(System.err, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

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


    public List<User> getUsersByProfession(String profession) throws SQLException {
        List<User> usersByProfession = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USERS_WHERE_PROFESSION);
        preparedStatement.setString(1, profession);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usersByProfession.add(
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
        return usersByProfession;
    }


    public List<User> getUsersByFavoriteCar(String car) throws SQLException {
        List<User> usersByCar = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USERS_WHERE_FAVORITE_CAR);
        preparedStatement.setString(1, car);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usersByCar.add(
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
        return usersByCar;
    }

    public List<User> getUsersByFavoriteFood(String food) throws SQLException {
        List<User> usersByFavoriteFood = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USERS_WHERE_FAVORITE_FOOD);
        preparedStatement.setString(1, food);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usersByFavoriteFood.add(
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
        return usersByFavoriteFood;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Устанавливаем кодировку, чтобы в браузере корректно отображался русский текст
        resp.setContentType("text/plain; charset=UTF-8");

        try {
            List<User> engineers = getUsersByProfession("Разработчик");
            System.out.println("Пользователи с профессией Разработчик:");
            engineers.forEach(user -> System.out.println(user));

            System.out.println("-----------------------------------");

            List<User> bmwDrivers = getUsersByFavoriteCar("BMW");
            System.out.println("Пользователи, любящие BMW:");
            bmwDrivers.forEach(user -> System.out.println(user));

            System.out.println("-----------------------------------");

            List<User> pizzaLovers = getUsersByFavoriteFood("Суши");
            System.out.println("Пользователи, любящие Суши:");
            pizzaLovers.forEach(user -> System.out.println(user));

            System.out.println("=== ПОИСК ЗАВЕРШЕН ===");

            // Выводим сообщение в браузер, что всё прошло успешно
            resp.getWriter().println("Готово! Проверьте вкладку Console в вашей IDE (IntelliJ IDEA).");

        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении SQL-запроса в консоли: " + e.getMessage());
            e.printStackTrace();

            resp.getWriter().println("Произошла ошибка базы данных: " + e.getMessage());
        }
    }

}
