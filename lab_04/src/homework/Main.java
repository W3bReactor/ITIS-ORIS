package homework;

/*
ДЗ_2:
1) Использая код в каталоге OOP, реализовать возможность отправки единым запросом 6 пользователей в таблицу, не используя preparedStatement;
2) Реализовать вывод пользователей с определенным условием;
3) Соблюдать принципы ООП;
Дедлайн: 30.09.2026 00:15.
p.s.
Разделять все по папкам не требуется
*/


import oop.entity.User;
import oop.repository.UserRepository;
import oop.repository.UserRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String DB_USERNAME = "postgres";

    private static final String DB_PASSWORD = "123123";

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ITIS_testdb";


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        UserRepository userRepository = new UserRepositoryJdbcImpl(connection);
        List<User> users = new ArrayList<>(
                List.of(
                        new User(1L, "Ilhan", "Sabirov", 18),
                        new User(2L, "Ivan", "Osipov", 18),
                        new User(3L, "Rinat", "Shafikov", 19),
                        new User(4L, "Insaf", "Garifullin", 18),
                        new User(5L, "Rif", "Muslimov", 19),
                        new User(6L, "John", "Doe", 30)
                )
        );
        userRepository.save67(users);
        System.out.println(userRepository.findAllOverYears(18));
    }
}
