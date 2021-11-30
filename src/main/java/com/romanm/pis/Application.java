package com.romanm.pis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
    }

    private static Properties loadPropertiesFromResource(String resourceName) throws IOException {
        Properties applicationProperties = new Properties();
        InputStream applicationPropertiesStream = Application.class.getClassLoader()
                .getResourceAsStream(resourceName);
        applicationProperties.load(applicationPropertiesStream);
        return applicationProperties;
    }
}
