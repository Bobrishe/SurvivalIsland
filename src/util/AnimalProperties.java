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

        String animalName = animal.getAnimalName();
        String[] props = baseProperties.getProperties().getProperty(animalName).split(",");

        propertyList = Arrays.stream(props)
                .map(s -> s.trim())
                .filter(s -> !s.isEmpty())
                .toList();

        if (propertyList.size() != 5) {
            throw new PropertyException();
        }
    }

    public static AnimalProperties getInstance(Animal animal) {
        if (instance.get(animal.getAnimalName()) == null) {
            synchronized (AnimalProperties.class) {
                if (instance.get(animal.getAnimalName()) == null) {
                    instance.put(animal.getAnimalName(), new AnimalProperties(animal));
                }
            }
        }
        return instance.get(animal.getAnimalName());
    }

    public int getEatProbability(Animal predator, Animal food) {
        String predatorName = predator.getAnimalName();
        String foodName = food.getAnimalName();
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
