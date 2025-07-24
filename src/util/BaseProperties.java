package util;

import exception.PropertyException;
import exception.PropertyFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseProperties {

    private static volatile BaseProperties instance;
    private final String BASE_PROPERTY = "src/resources/animal.properties";

    private Properties properties = new Properties();

    private BaseProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(BASE_PROPERTY)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new PropertyFileException();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public static BaseProperties getInstance() {
        if (instance == null) {
            synchronized (BaseProperties.class) {
                if (instance == null) {
                    instance = new BaseProperties();
                }
            }
        }
        return instance;
    }

    public String getProperty(String property) {
        if (properties.isEmpty() || property == null) {
            return null;
        }

        return properties.getProperty(property);
    }

    public int getIntProperty(String property) {
        int value;

        try {
            value = Integer.parseInt(getProperty(property));
        } catch (PropertyException e) {
            throw new PropertyException();
        }

        return value;
    }

}
