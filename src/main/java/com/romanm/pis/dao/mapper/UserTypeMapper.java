package com.romanm.pis.dao.mapper;

import com.romanm.pis.domain.Report;
import com.romanm.pis.domain.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserTypeMapper {
    public UserType extractUserTypeFromResultSet(ResultSet resultSet) throws SQLException {
        UserType userType = new UserType();
        while (resultSet.next()) {
            userType.setId(resultSet.getLong("id"));
            userType.setName(resultSet.getString("name"));
            userType.setDescription(resultSet.getString("description"));
        }
        return userType;
    }

    public List<UserType> extractUserTypesFromResultSet(ResultSet resultSet) throws SQLException {
        List<UserType> userTypes = new ArrayList<>();
        UserType userType;
        while (resultSet.next()) {
            userType = new UserType();
            userType.setId(resultSet.getLong("id"));
            userType.setName(resultSet.getString("name"));
            userType.setDescription(resultSet.getString("description"));
            userTypes.add(userType);
        }
        return userTypes;
    }
}
