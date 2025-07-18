package util;

import entity.Animal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AnimalProperties {

    private static AnimalProperties instance;

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
            instance = new AnimalProperties();
        }
        return instance;
    }

    public int getProbability(Animal predator, Animal food) {
        if (properties.isEmpty()) {
            return 0;
        }

        String predatorName = predator.getClass().getSimpleName().toLowerCase();
        String foodName = food.getClass().getSimpleName().toLowerCase();
        String property = properties.getProperty(predatorName + '.' + foodName);

        if (property == null) return 0;

        return Integer.parseInt(property);
    }

}
