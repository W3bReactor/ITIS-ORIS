package oop.repository;

import oop.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRepository<User> {

    List<User> findAllOverYears(Integer age) throws SQLException;
    void save67(List<User> users) throws SQLException;
}
