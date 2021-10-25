package com.romanm.pis.dao.impl;

import com.romanm.pis.dao.DAOFactory;
import com.romanm.pis.dao.EventDAO;
import com.romanm.pis.dao.ReportDAO;
import com.romanm.pis.dao.UserDAO;
import com.romanm.pis.dao.UserTypeDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDAOFactory extends DAOFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EventDAO createEventDao() {
        return new EventDAOImpl(getConnection());
    }

    @Override
    public ReportDAO createReportDao() {
        return new ReportDAOImpl(getConnection());
    }

    @Override
    public UserDAO createUserDao() {
        return null;
    }

    @Override
    public UserTypeDAO createUserTypeDao() {
        return null;
    }
}
