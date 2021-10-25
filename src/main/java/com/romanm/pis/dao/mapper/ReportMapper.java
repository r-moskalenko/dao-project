package com.romanm.pis.dao.mapper;

import com.romanm.pis.domain.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper {
    public Report extractFromResultSet(ResultSet resultSet) throws SQLException {
        Report report = new Report();
        while (resultSet.next()) {
            report.setId(resultSet.getLong("id"));
            report.setTopic(resultSet.getString("topic"));
        }
        return report;
    }
}
