package util;

import entity.Animal;
import exception.PropertyException;
import exception.PropertyFileException;

import java.util.*;

public class AnimalProperties {

    private static Map<String, AnimalProperties> instance = new HashMap<>();
    private final PropertyStore store;

    private static final String WEIGHT = "weight";
    private static final String MAX_COUNT = "maxCount";
    private static final String FOOD_NEED = "foodNeed";
    private static final String ICON = "icon";
    private static final String SPEED = "speed";


    private AnimalProperties(Animal animal) {

        store = PropertyStore.getInstance(animal.getName());

        if (store == null || store.getProperties().isEmpty()) {
            throw new PropertyFileException(animal.getName());
        }

    }

    public static AnimalProperties getInstance(Animal animal) {
        String name = animal.getName();

        if (instance.get(name) == null) {
            synchronized (AnimalProperties.class) {
                if (instance.get(name) == null) {
                    instance.put(name, new AnimalProperties(animal));
                }
            }
        }
        return instance.get(name);
    }

    public int getEatProbability(Animal food) {
        return store.getInt(food.getName());
    }

    public double getWeight() throws PropertyException {
        return store.getDouble(WEIGHT);
    }

    public int getMaxCount() throws PropertyException {
        return store.getInt(MAX_COUNT);
    }

    public int getSpeed() throws PropertyException {
        return store.getInt(SPEED);
    }

    public double getFoodNeeded() throws PropertyException {
        return store.getDouble(FOOD_NEED);
    }

    public String getIcon() {
        return store.getProperty(ICON);
    }

}
