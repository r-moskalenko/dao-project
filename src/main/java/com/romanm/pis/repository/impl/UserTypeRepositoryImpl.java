package com.romanm.pis.repository.impl;

import com.romanm.pis.domain.User;
import com.romanm.pis.domain.UserType;
import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.repository.UserTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserTypeRepositoryImpl implements UserTypeRepository {

    private static final Logger logger = LogManager.getLogger(UserTypeRepositoryImpl.class);

    private final ConnectionPool connectionPool;

    public UserTypeRepositoryImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public UserType save(UserType userType) {
        Connection connection = null;
        try {
            connection = connectionPool.getResource();
        } catch(Exception e) {
            e.printStackTrace();
        }

        String SQL_INSERT;
        Long userId = 0L;
        String SQL_SELECT;

        if (userType.getDescription() != null) {
            SQL_INSERT = "insert into user_types (name, description) values (?, ?)";

            executeUpdate(connection, SQL_INSERT, userType.getName(), userType.getDescription());
            SQL_SELECT = String.format("select id from user_types where name = '%s' and description = '%s'", userType.getName(), userType.getDescription());
        } else {
            SQL_INSERT = "insert into user_types (name) values (?)";
            executeUpdate(connection, SQL_INSERT, userType.getName());
            SQL_SELECT = String.format("select id from user_types where name = '%s' and description is null", userType.getName());
        }

        logger.info("insert query = " + SQL_INSERT);

        SQL_SELECT += " order by id desc";

        logger.info("select query = " + SQL_SELECT);
        try {
            userId = selectUserIdByQuery(SQL_SELECT);
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
        userType.setId(userId);

        return userType;
    }

    private Long selectUserIdByQuery(String query) throws Exception {
        Connection connection = connectionPool.getResource();
        try(Statement stmt = connection.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                } else {
                    String message = "Inserted element not found.";
                    logger.error(message);
                    throw new Exception(message);
                }
            }
        }
    }

    private void executeUpdate(Connection connection, String query, Object... params) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i=0; i < params.length; i++) {
                checkParamTypeAndSet(preparedStatement, params[i], i+1);
            }
            int row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkParamTypeAndSet(PreparedStatement preparedStatement, Object param, int index) throws SQLException {
        if (param instanceof String) {
            preparedStatement.setString(index, (String) param);
        } else if(param instanceof Long) {
            preparedStatement.setLong(index, (Long) param);
        } else if (param instanceof Boolean) {
            preparedStatement.setBoolean(index, (Boolean) param);
        }
    }

    @Override
    public Optional<User> findUserTypeById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
