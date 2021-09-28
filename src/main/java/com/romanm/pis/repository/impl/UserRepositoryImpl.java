package com.romanm.pis.repository.impl;

import com.romanm.pis.domain.User;
import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.jdbc.JdbcConnectionOptions;
import com.romanm.pis.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private ConnectionPool connectionPool;
    private JdbcConnectionOptions jdbcConnectionOptions;

    public UserRepositoryImpl(ConnectionPool connectionPool, JdbcConnectionOptions jdbcConnectionOptions) {
        this.connectionPool = connectionPool;
        this.jdbcConnectionOptions = jdbcConnectionOptions;
    }

    @Override
    public User save(User user) {
        Connection connection = null;
        try {
            connectionPool.getResource();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try (Statement stmt = connection.createStatement()) {
            String tableSql = "CREATE TABLE IF NOT EXISTS employees"
                    + "(emp_id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
                    + "position varchar(30), salary double)";
            stmt.execute(tableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
