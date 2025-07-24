package util;

import entity.Animal;
import exception.PropertyException;
import exception.PropertyFileException;

import java.util.*;

public class AnimalProperties {

    private static volatile Map<String, AnimalProperties> instance = new HashMap<>();
    private List<String> propertyList;
    protected static BaseProperties baseProperties = BaseProperties.getInstance();

    private AnimalProperties(Animal animal) {

        if (baseProperties.getProperties().isEmpty() || animal == null) {
            throw new PropertyFileException();
        }

        String animalName = animal.getName();
        String property = baseProperties.getProperties().getProperty(animalName);
        if (property == null) {
            throw new PropertyException();
        }
        String[] props = property.split(",");

        propertyList = Arrays.stream(props)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        if (propertyList.size() != 5) {
            throw new PropertyException();
        }
    }

    public static AnimalProperties getInstance(Animal animal) {
        if (instance.get(animal.getName()) == null) {
            synchronized (AnimalProperties.class) {
                if (instance.get(animal.getName()) == null) {
                    instance.put(animal.getName(), new AnimalProperties(animal));
                }
            }
        }
        return instance.get(animal.getName());
    }

    public int getEatProbability(Animal predator, Animal food) {
        String predatorName = predator.getName();
        String foodName = food.getName();
        String property = predatorName + '.' + foodName;

        return baseProperties.getIntProperty(property);
    }

    public double getWeight() throws PropertyException {
        return Double.parseDouble(propertyList.get(0));
    }

    public int getMaxCount() throws PropertyException {
        return Integer.parseInt(propertyList.get(1));
    }

    public int getSpeed() throws PropertyException {
        return Integer.parseInt(propertyList.get(2));
    }

    public double getFoodNeeded() throws PropertyException {
        return Double.parseDouble(propertyList.get(3));
    }

    public String getIcon() {
        return propertyList.get(4);
    }


    public synchronized boolean hasProperties() {
        return !propertyList.isEmpty();
    }

}
