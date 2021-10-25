package com.romanm.pis.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestUtil {
    private static final Logger logger = LogManager.getLogger(RequestUtil.class);

    public static Long getLastInsertId(Connection connection, String object) {
        long newId = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             "select last_insert_id();")) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            newId = resultSet.getLong(1);
        } catch (SQLException e) {
            logger.error("Create new " + object + ": " + e.getMessage());
        }
        logger.trace("new " + object + " was created with id=" + newId);
        return newId;
    }
}
