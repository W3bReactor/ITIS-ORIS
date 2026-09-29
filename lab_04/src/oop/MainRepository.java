package oop;

import oop.entity.User;
import oop.repository.UserRepository;
import oop.repository.UserRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainRepository {

    private static final String DB_USERNAME = "postgres";

    private static final String DB_PASSWORD = "123123";

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ITIS_testdb";


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        UserRepository userRepository = new UserRepositoryJdbcImpl(connection);


        List<User> users = userRepository.findAll();

        System.out.println(users);
    }
}
