package com.romanm.pis.dao.impl;

import com.romanm.pis.dao.mapper.ReportMapper;
import com.romanm.pis.domain.Report;
import com.romanm.pis.dao.ReportDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReportDAOImpl implements ReportDAO {

    private static final Logger logger = LogManager.getLogger(ReportDAOImpl.class);

    private final Connection connection;

    private final static String INSERT_INTO_REPORTS =
            "insert into reports(topic, user_id, event_id) value(?, ?, ?);";
    private final static String UPDATE_REPORT =
            "update reports set topic=?, user_id=?, event_id=? where id=?;";
    private final static String REPORT_FIND_BY_ID_QUERY =
            "select * from reports where id=?;";
    private final static String REPORT_FIND_ALL_QUERY =
            "select * from reports;";

    public ReportDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Report save(Report report) {
        if (report.getId() == null) {
            Long newId;
            insertReport(report);
            newId = RequestUtil.getLastInsertId(connection, "report");
            logger.debug("Report (id = "
                    + newId
                    + ") was created: " + report.getTopic());
            report.setId(newId);
        } else {
            updateReport(report);
        }
        return report;
    }

    private void insertReport(Report report) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             INSERT_INTO_REPORTS)) {
            statement.setString(1, report.getTopic());
            statement.setLong(2, report.getUser().getId());
            statement.setLong(3, report.getEvent().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new report: " + e.getMessage());
        }
        logger.debug("New report : " + report.getTopic() + " was successfully created");
    }

    private void updateReport(Report report) {
        try (PreparedStatement statement =
                     connection.prepareStatement(
                             UPDATE_REPORT)) {
            statement.setString(1, report.getTopic());
            statement.setLong(2, report.getUser().getId());
            statement.setLong(3, report.getEvent().getId());
            statement.setLong(4, report.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Create new report: " + e.getMessage());
        }
        logger.debug("New report : " + report.getTopic() + " was successfully created");
    }

    @Override
    public Optional<Report> findById(Long id) {
        Report report = null;
        logger.info("Try to find report by id");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             REPORT_FIND_BY_ID_QUERY)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                report = loadReport(rs);
            }
        } catch (SQLException e) {
            logger.debug("Get report by id=" + id + " sql exception : " + e.getMessage());
        }
        return Optional.ofNullable(report);
    }

    private Report loadReport(ResultSet rs) throws SQLException {
        ReportMapper reportMapper = new ReportMapper();
        return reportMapper.extractFromResultSet(rs);
    }

    @Override
    public List<Report> findAll() {
        List<Report> reports = new ArrayList<>();
        logger.info("Try to find all reports");
        try (PreparedStatement pst =
                     connection.prepareStatement(
                             REPORT_FIND_ALL_QUERY)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Report report = loadReport(rs);
                reports.add(report);
            }
        } catch (SQLException e) {
            logger.debug("Get all reports sql exception : " + e.getMessage());
        }
        return reports;
    }
}
