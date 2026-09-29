package oop.repository;

import oop.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private Connection connection;

    private static final String SQL_SELECT_FROM_DRIVERS = "SELECT id, firstname, lastname, age FROM drivers";
    private static final StringBuilder SQL_SELECT_OVER_YEARS_FROM_DRIVERS = new StringBuilder("SELECT id, firstname, lastname, age FROM drivers WHERE age > ");

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllOverYears(Integer age) throws SQLException {
        Statement statement = connection.createStatement();
        SQL_SELECT_OVER_YEARS_FROM_DRIVERS.append(age).append(";");
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_OVER_YEARS_FROM_DRIVERS.toString());
        List<User> result = new ArrayList<>();

        while (resultSet.next()) {
            User newUser = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getInt("age")
            );
            result.add(newUser);
        }

        return result;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVERS);
        List<User> result = new ArrayList<>();

        while (resultSet.next()) {
            User newUser = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getInt("age")
            );
            result.add(newUser);
        }

        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    public void save67(List<User> entities) throws SQLException {
        Statement statement = connection.createStatement();

        StringBuilder INSERT6DRIVER = new StringBuilder();

        for (User entity : entities) {
            INSERT6DRIVER.append("INSERT INTO drivers (firstname, lastname, age) VALUES (");
            INSERT6DRIVER.append("'").append(entity.getName()).append("'").append(",");
            INSERT6DRIVER.append("'").append(entity.getSurname()).append("'").append(",");
            INSERT6DRIVER.append(entity.getAge()).append(");");
        }
        statement.execute(INSERT6DRIVER.toString());

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(Long id) {

    }
}
