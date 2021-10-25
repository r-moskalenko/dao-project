package com.romanm.pis.dao.mapper;

import com.romanm.pis.domain.Report;
import com.romanm.pis.domain.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeMapper {
    public UserType extractFromResultSet(ResultSet resultSet) throws SQLException {
        UserType userType = new UserType();
        while (resultSet.next()) {
            userType.setId(resultSet.getLong("id"));
            userType.setDescription(resultSet.getString("description"));
        }
        return userType;
    }
}
