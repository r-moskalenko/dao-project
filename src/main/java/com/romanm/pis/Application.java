package com.romanm.pis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
//        Properties applicationProperties = loadPropertiesFromResource("application.properties");
//
//        JdbcConnectionOptions jdbcConnectionOptions = new JdbcConnectionOptions(
//                applicationProperties.getProperty("mysql.url.connection.string"),
//                applicationProperties.getProperty("mysql.user"),
//                applicationProperties.getProperty("mysql.password"));
//
//        ConnectionPool connectionPool = new ConnectionPoolImpl(jdbcConnectionOptions);
//        UserTypeDAO userTypeDAO = new UserTypeDAOImpl(connectionPool.getResource());
//        UserDAO userDAO = new UserDAOImpl(connectionPool.getResource());
//        EventDAO eventDAO = new EventDAOImpl(connectionPool.getResource());
//
//        UserType adminType = new UserType();
//        adminType.setName("admin");
//        adminType.setDescription("Has all privileges");
//        adminType = userTypeDAO.save(adminType);
//
//        UserType regularUser = new UserType();
//        regularUser.setName("regular_user");
//        regularUser.setDescription("Has regular privileges");
//        regularUser = userTypeDAO.save(regularUser);
//
//        User user1 = new User();
//        user1.setUserType(adminType);
//        user1.setName("Oliver Cromwell");
//
//        User user2 = new User();
//        user2.setUserType(regularUser);
//        user2.setName("Robert Cromwell");
//
//        user1 = userDAO.save(user1);
//        user2 = userDAO.save(user2);
//
//        logger.info(adminType);
//        logger.info(user1);
//
//        logger.info(regularUser);
//        logger.info(user2);
//
//        Event event1 = new Event();
//        event1.setLongDescription("PIS lecture");
//        event1.setDateAndTime(LocalDateTime.now());
//        event1 = eventDAO.save(event1);
//
//        logger.info(event1);
    }

    private static Properties loadPropertiesFromResource(String resourceName) throws IOException {
        Properties applicationProperties = new Properties();
        InputStream applicationPropertiesStream = Application.class.getClassLoader()
                .getResourceAsStream(resourceName);
        applicationProperties.load(applicationPropertiesStream);
        return applicationProperties;
    }
}
