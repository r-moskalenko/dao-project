package com.romanm.pis.dao.mapper;

import com.romanm.pis.domain.Report;
import com.romanm.pis.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
        }
        return user;
    }
}
