package com.romanm.pis.dao.mapper;

import com.romanm.pis.domain.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportMapper {
    public Report extractReportFromResultSet(ResultSet resultSet) throws SQLException {
        Report report = new Report();
        while (resultSet.next()) {
            report.setId(resultSet.getLong("id"));
            report.setTopic(resultSet.getString("topic"));
        }
        return report;
    }

    public List<Report> extractReportsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Report> reports = new ArrayList<>();
        Report report;
        while (resultSet.next()) {
            report = new Report();
            report.setId(resultSet.getLong("id"));
            report.setTopic(resultSet.getString("topic"));
            reports.add(report);
        }
        return reports;
    }
}
