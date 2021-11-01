package com.romanm.pis.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class Utility implements ApplicationConstants {

    private static final String APP_PROPERTIES_BUNDLE_NAME = "application";

    private final static Logger logger = LogManager.getLogger(Utility.class);

    public static Integer tryParseInteger(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.error("Number format exception : " + e.getMessage());
            logger.info("set default value = " + defaultValue);
        }
        return defaultValue;
    }

    public static String getApplicationProperty(String property) {
        ResourceBundle bundle = ResourceBundle.getBundle(
                APP_PROPERTIES_BUNDLE_NAME,
                new Locale(APP_DEFAULT_LANGUAGE));
        try {
            return bundle.getString(property);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "";
    }
}
