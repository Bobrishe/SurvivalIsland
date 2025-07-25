package util;

import exception.PropertyFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyStore {

    private static Map<String, PropertyStore> instance = new HashMap<>();
    private final Properties properties = new Properties();
    private static final String BASE_PATH = "src/resources/property";

    private PropertyStore(String propertyFile) {

        File propFile = new File(BASE_PATH, propertyFile + ".properties");
        if (!propFile.exists()) {
            throw new PropertyFileException(propertyFile);
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(propFile)) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                throw new PropertyFileException(propertyFile);
            }
        }
    }

    public static PropertyStore getInstance(String propertyFile) {
        if (instance.get(propertyFile) == null) {
            synchronized (PropertyStore.class) {
                if (instance.get(propertyFile) == null) {
                    instance.put(propertyFile, new PropertyStore(propertyFile));
                }
            }
        }
        return instance.get(propertyFile);
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String property) {
        if (properties.isEmpty() || property == null) {
            return null;
        }

        return properties.getProperty(property);
    }

    public int getInt(String property) {
        return Integer.parseInt(getProperty(property));
    }

    public double getDouble(String property) {
        return Double.parseDouble(getProperty(property));
    }


}
