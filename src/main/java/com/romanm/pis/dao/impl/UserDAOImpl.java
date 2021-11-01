package com.romanm.pis.dao.impl;

import com.romanm.pis.dao.mapper.UserMapper;
import com.romanm.pis.domain.User;
import com.romanm.pis.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private final static String INSERT_INTO_USERS =
            "insert into users(name, user_type_id) value(?, ?);";
    private final static String UPDATE_USERS =
            "update users set name=?, user_type_id=? where id=?;";
    private final static String USER_FIND_BY_ID_QUERY =
            "select * from users where id=?;";
    private final static String USER_FIND_ALL_QUERY =
            "select * from users;";

    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public User save(User user) {
        if (user.getId() == null) {
            Long newId;
            insertUser(user);
            newId = RequestUtil.getLastInsertId(connection, "user");
            logger.debug("User (id = "
                    + newId
                    + ") was created: " + user.getName());
            user.setId(newId);
        } else {
            updateUser(user);
        }
        return user;
    }

    private void insertUser(User user) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             INSERT_INTO_USERS)) {
            statement.setString(1, user.getName());
            statement.setLong(2, user.getUserType().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new user: " + e.getMessage());
        }
        logger.debug("New user : " + user.getName() + " was successfully created");
    }

    private void updateUser(User user) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             UPDATE_USERS)) {
            statement.setString(1, user.getName());
            statement.setLong(2, user.getUserType().getId());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new user: " + e.getMessage());
        }
        logger.debug("New user : " + user.getName() + " was successfully created");
    }

    @Override
    public Optional<User> findUserById(Long id) {
        User user = null;
        logger.info("Try to find user by id");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             USER_FIND_BY_ID_QUERY)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            UserMapper userMapper = new UserMapper();
            user = userMapper.extractUserFromResultSet(rs);
        } catch (SQLException e) {
            logger.debug("Get user by id=" + id + " sql exception : " + e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        logger.info("Try to find all users");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             USER_FIND_ALL_QUERY)) {
            ResultSet rs = pst.executeQuery();
            UserMapper userMapper = new UserMapper();
            users = userMapper.extractUsersFromResultSet(rs);
        } catch (SQLException e) {
            logger.debug("Get all users sql exception : " + e.getMessage());
        }
        return users;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("cannot close connection " + e.getMessage());
        }
    }
}
