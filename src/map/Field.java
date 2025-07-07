package map;

import entity.Animal;
import entity.Plant;
import factory.AnimalFactory;
import types.AnimalType;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();
    private AnimalFactory factory = AnimalFactory.getInstance();

    public synchronized void addAnimal(AnimalType type) {
        animals.add(factory.getAnimal(type)); // just as example. going to complete in another branch
    }

    public synchronized void growPlants() {
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

}
