package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("app.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getProperty(String key){
        return  properties.getProperty(key);
    }



}
