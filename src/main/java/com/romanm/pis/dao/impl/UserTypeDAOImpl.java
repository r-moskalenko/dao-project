package com.romanm.pis.dao.impl;

import com.romanm.pis.dao.mapper.UserTypeMapper;
import com.romanm.pis.domain.UserType;
import com.romanm.pis.dao.UserTypeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserTypeDAOImpl implements UserTypeDAO {

    private static final Logger logger = LogManager.getLogger(UserTypeDAOImpl.class);

    private final static String INSERT_INTO_USER_TYPES =
            "insert into user_types(name, description) value(?, ?);";
    private final static String UPDATE_USER_TYPES =
            "update user_types set name=?, description=? where id=?;";
    private final static String USER_TYPE_FIND_BY_ID_QUERY =
            "select * from user_types where id=?;";
    private final static String USER_TYPE_FIND_ALL_QUERY =
            "select * from user_types;";

    private final Connection connection;

    public UserTypeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserType save(UserType userType) {
        if (userType.getId() == null) {
            Long newId;
            insertUserType(userType);
            newId = RequestUtil.getLastInsertId(connection, "user type");
            logger.debug("UserType (id = "
                    + newId
                    + ") was created: " + userType.getName());
            userType.setId(newId);
        } else {
            updateUserType(userType);
        }
        return userType;
    }

    private void insertUserType(UserType userType) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             INSERT_INTO_USER_TYPES)) {
            statement.setString(1, userType.getName());
            statement.setString(2, userType.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new user type: " + e.getMessage());
        }
        logger.debug("New user type: " + userType.getName() + " was successfully created");
    }

    private void updateUserType(UserType userType) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             UPDATE_USER_TYPES)) {
            statement.setString(1, userType.getName());
            statement.setString(2, userType.getDescription());
            statement.setLong(3, userType.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new user type: " + e.getMessage());
        }
        logger.debug("New user type : " + userType.getName() + " was successfully created");
    }

    @Override
    public Optional<UserType> findById(Long id) {
        UserType userType = null;
        logger.info("Try to find user type by id");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             USER_TYPE_FIND_BY_ID_QUERY)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                userType = loadUserType(rs);
            }
        } catch (SQLException e) {
            logger.debug("Get user type by id=" + id + " sql exception : " + e.getMessage());
        }
        return Optional.ofNullable(userType);
    }

    private UserType loadUserType(ResultSet rs) throws SQLException {
        UserTypeMapper userMapper = new UserTypeMapper();
        return userMapper.extractFromResultSet(rs);
    }

    @Override
    public List<UserType> findAll() {
        List<UserType> userTypes = new ArrayList<>();
        logger.info("Try to find all user types");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             USER_TYPE_FIND_ALL_QUERY)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                UserType userType = loadUserType(rs);
                userTypes.add(userType);
            }
        } catch (SQLException e) {
            logger.debug("Get all user types sql exception : " + e.getMessage());
        }
        return userTypes;
    }
}
