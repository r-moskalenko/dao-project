package com.romanm.pis;

import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.jdbc.ConnectionPoolImpl;
import com.romanm.pis.jdbc.JdbcConnectionOptions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Application {
    public static void main(String args) throws IOException {
        Properties applicationProperties = loadPropertiesFromResource("application.properties");

        JdbcConnectionOptions jdbcConnectionOptions = new JdbcConnectionOptions(
                applicationProperties.getProperty("database.url"),
                applicationProperties.getProperty("database.user"),
                applicationProperties.getProperty("database.password"));

        ConnectionPool connectionPool = new ConnectionPoolImpl(5, 3L);


    }

    private static Properties loadPropertiesFromResource(String resourceName) throws IOException {
        Properties applicationProperties = new Properties();
        InputStream applicationPropertiesStream = Application.class.getClassLoader()
                .getResourceAsStream(resourceName);
        applicationProperties.load(applicationPropertiesStream);
        return applicationProperties;
    }
}
