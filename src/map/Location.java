package map;

import entity.Animal;
import entity.Plant;
import types.AnimalClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Location {

    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public synchronized void growPlant() {
        plants.add(new Plant());
    }

    public synchronized void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public synchronized List<Animal> getAnimals() {
        return animals;
    }

    public synchronized Map<Class<? extends Animal>, List<Animal>> getAnimalsMap() {
        return getAnimals().stream()
                .collect(Collectors.groupingBy(Animal::getClass));
    }

    public synchronized List<Animal> getAnimals(Class<? extends Animal> clazz) {
        List<Animal> sameAnimals = new ArrayList<>();
        for (Animal animal : getAnimals()) {
            if (animal.getClass().equals(clazz))
                sameAnimals.add(animal);

        }

        return sameAnimals;
    }

    public synchronized List<Animal> getAnimals(AnimalClass animalClass) {
        List<Animal> sameAnimals = new ArrayList<>();
        for (Animal animal : getAnimals()) {
            if (animal.getAnimalClass().equals(animalClass))
                sameAnimals.add(animal);

        }

        return sameAnimals;
    }

    public synchronized List<Plant> getPlants() {
        return plants;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
