package entity;

import entity.impl.Caterpillar;
import map.Location;
import types.AnimalClass;

public abstract class Herbivore extends Animal {

    public Herbivore() {
        super();
    }

    @Override
    public boolean isCanEatMeat() {
        return false;
    }

    @Override
    public AnimalClass getAnimalClass() {
        return AnimalClass.HERBIVORE;
    }

    @Override
    public void eat() {
        Location current = getLocation();
        final double[] eaten = {0};

        if (isCanEatMeat()) {
            current.getAnimals(Caterpillar.class).stream().forEach(animal -> {
                if (eaten[0] <= getFoodNeeded() - animal.getWeight()) {
                    eaten[0] += animal.getWeight();
                    current.removeAnimal(animal);
                }
            });
        }

        current.getPlants().stream().anyMatch(plant -> {
            if (eaten[0] <= getFoodNeeded() - Plant.WEIGHT) {
                eaten[0] += Plant.WEIGHT;
                current.removePlant(plant);
                return false;
            }

            return true;
        });
    }
}
