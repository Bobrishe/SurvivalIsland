package util;

import entity.Animal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AnimalProperties {

    private static volatile AnimalProperties instance;

    private Properties properties = new Properties();

    private AnimalProperties() {
        try (FileInputStream fileInputStream = new FileInputStream("src/resources/animal.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            // No property file
        }
    }

    public static AnimalProperties getInstance() {
        if (instance == null) {
            synchronized (AnimalProperties.class) {
                if (instance == null) {
                    instance = new AnimalProperties();
                }
            }
        }
        return instance;
    }

    public int getIntProperty(String property) {
        if (properties.isEmpty() || property == null) {
            return 0;
        }
        int value;

        try {
            value = Integer.parseInt(property);
        } catch (NumberFormatException e) {
            value = 0;
        }

        return value;
    }

}
