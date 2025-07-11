package entity;

import entity.impl.Caterpillar;
import map.Location;
import types.AnimalClass;

import java.util.List;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxCount, int speed, double foodNeeded, String symbol) {
        super(weight, maxCount, speed, foodNeeded, symbol);
        setCanEatPlant(true);
        setAnimalClass(AnimalClass.HERBIVORE);
    }

    @Override
    public void eat() {
        Location current = getLocation();
        double eaten = 0;

        if (isCanEatMeat()) {
            List<Animal> caterpillars = current.getAnimals(Caterpillar.class);

            if (!caterpillars.isEmpty()) {
                for (Animal food : caterpillars) {
                    if (eaten <= getFoodNeeded() - food.getWeight()) {
                        eaten += food.getWeight();
                        current.removeAnimal(food);
                    } else break;
                }
            }
        }

        while (!current.getPlants().isEmpty()) {
            Plant plant = current.getPlants().get(0);
            if (eaten <= getFoodNeeded() - Plant.WEIGHT) {
                eaten += Plant.WEIGHT;
                current.removePlant(plant);
            } else break;
        }
    }
}
