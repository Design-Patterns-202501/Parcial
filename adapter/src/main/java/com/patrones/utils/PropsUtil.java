package com.patrones.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

    public  Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/application.properties")) {
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }
}
