package com.romanm.pis.dao;

import com.romanm.pis.dao.impl.JDBCDAOFactory;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;

    public static DAOFactory getInstance() {
        synchronized (DAOFactory.class) {
            if (daoFactory == null) {
                daoFactory = new JDBCDAOFactory();
            }
        }
        return daoFactory;
    }

    public abstract ReportDAO createReportDao();

    public abstract UserDAO createUserDao();

    public abstract UserTypeDAO createUserTypeDao();
}
