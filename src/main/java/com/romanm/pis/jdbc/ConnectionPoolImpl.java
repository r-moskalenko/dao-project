package com.romanm.pis.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolImpl implements ConnectionPool {
    private BasicDataSource ds = new BasicDataSource();

    public ConnectionPoolImpl(JdbcConnectionOptions jdbcConnectionOptions) {
        ds.setUrl(jdbcConnectionOptions.getUrl());
        ds.setUsername(jdbcConnectionOptions.getUser());
        ds.setPassword(jdbcConnectionOptions.getPassword());
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public Connection getResource() throws SQLException {
        return ds.getConnection();
    }
}
