package com.romanm.pis.repository.impl;

import com.romanm.pis.domain.User;
import com.romanm.pis.domain.UserType;
import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.repository.UserRepository;
import com.romanm.pis.repository.UserTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    private final ConnectionPool connectionPool;
    private final UserTypeRepository userTypeRepository;

    public UserRepositoryImpl(ConnectionPool connectionPool, UserTypeRepository userTypeRepository) {
        this.connectionPool = connectionPool;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public User save(User user) {
        Connection connection = null;
        try {
            connection = connectionPool.getResource();
        } catch(Exception e) {
            e.printStackTrace();
        }

        String SQL_INSERT;
        Long userId = 0L;
        String SQL_SELECT;

        if (user.getUserType() != null) {
            UserType userType = user.getUserType();
            userType = userTypeRepository.save(userType);

            SQL_INSERT = "insert into users (name, user_type_id) values (?, ?)";
            executeUpdate(connection, SQL_INSERT, user.getName(), userType.getId());
            SQL_SELECT = String.format("select id from users where name = '%s' and user_type_id = '%d'", user.getName(), userType.getId());
        } else {
            SQL_INSERT = "insert into users (name) values (?)";
            executeUpdate(connection, SQL_INSERT, user.getName());
            SQL_SELECT = String.format("select id from users where name = '%s' and user_type_id is null", user.getName());
        }

        logger.info("insert query = " + SQL_INSERT);

        SQL_SELECT += " order by id desc";

        logger.info("select query = " + SQL_SELECT);
        try {
            userId = selectUserIdByQuery(SQL_SELECT);
        } catch(Exception e) {
            logger.error(e.getMessage());
        }
        user.setId(userId);

        return user;
    }

    private Long selectUserIdByQuery(String query) throws Exception {
        Connection connection = connectionPool.getResource();
        try(Statement stmt = connection.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                } else {
                    throw new Exception("Inserted element not found.");
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
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
