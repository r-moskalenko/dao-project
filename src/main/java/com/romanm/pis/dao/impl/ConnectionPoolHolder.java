package com.romanm.pis.dao.impl;

import com.romanm.pis.util.ApplicationConstants;
import com.romanm.pis.util.Utility;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

class ConnectionPoolHolder {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);

    private final static String MYSQL_URL_CONNECTION_STRING =
            Utility.getApplicationProperty("mysql.url.connection.string");
    private final static String MYSQL_USER = Utility.getApplicationProperty("mysql.user");
    private final static String MYSQL_PASSWORD = Utility.getApplicationProperty("mysql.password");
    private final static int MYSQL_MIN_IDLE =
            Utility.tryParseInteger(Utility.getApplicationProperty("mysql.min.idle"),
                    ApplicationConstants.MYSQL_DEFAULT_MIN_IDLE);
    private final static int MYSQL_MAX_IDLE =
            Utility.tryParseInteger(Utility.getApplicationProperty("mysql.max.idle"),
                    ApplicationConstants.MYSQL_DEFAULT_MAX_IDLE);
    private final static int MYSQL_MAX_OPEN_PREPARED_STATEMENTS =
            Utility.tryParseInteger(Utility.getApplicationProperty("mysql.max.open.prepared.statements"),
                    ApplicationConstants.MYSQL_DEFAULT_MAX_OPEN_PREPARED_STATEMENTS);
    private static DataSource dataSource;

    static {
        try{
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(MYSQL_URL_CONNECTION_STRING);
            ds.setUsername(MYSQL_USER);
            ds.setPassword(MYSQL_PASSWORD);
            ds.setMinIdle(MYSQL_MIN_IDLE);
            ds.setMaxIdle(MYSQL_MAX_IDLE);
            ds.setMaxOpenPreparedStatements(MYSQL_MAX_OPEN_PREPARED_STATEMENTS);
            dataSource = ds;
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}