package com.romanm.pis;

import com.romanm.pis.domain.User;
import com.romanm.pis.domain.UserType;
import com.romanm.pis.jdbc.ConnectionPool;
import com.romanm.pis.jdbc.ConnectionPoolImpl;
import com.romanm.pis.jdbc.JdbcConnectionOptions;
import com.romanm.pis.repository.UserRepository;
import com.romanm.pis.repository.UserTypeRepository;
import com.romanm.pis.repository.impl.UserRepositoryImpl;
import com.romanm.pis.repository.impl.UserTypeRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        Properties applicationProperties = loadPropertiesFromResource("application.properties");

        JdbcConnectionOptions jdbcConnectionOptions = new JdbcConnectionOptions(
                applicationProperties.getProperty("database.url"),
                applicationProperties.getProperty("database.user"),
                applicationProperties.getProperty("database.password"));

        ConnectionPool connectionPool = new ConnectionPoolImpl(jdbcConnectionOptions);
        UserTypeRepository userTypeRepository = new UserTypeRepositoryImpl(connectionPool);
        UserRepository userRepository = new UserRepositoryImpl(connectionPool, userTypeRepository);

        UserType adminType = new UserType();
        adminType.setName("admin");

        UserType regularUser = new UserType();
        regularUser.setName("regular_user");

        User user1 = new User();
        user1.setUserType(adminType);
        user1.setName("Oliver Cromwell");

        User user2 = new User();
        user2.setUserType(adminType);
        user2.setName("Robert Cromwell");

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);

        logger.info(adminType);
        logger.info(user1);

        logger.info(regularUser);
        logger.info(user2);
    }

    private static Properties loadPropertiesFromResource(String resourceName) throws IOException {
        Properties applicationProperties = new Properties();
        InputStream applicationPropertiesStream = Application.class.getClassLoader()
                .getResourceAsStream(resourceName);
        applicationProperties.load(applicationPropertiesStream);
        return applicationProperties;
    }
}
