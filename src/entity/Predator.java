package entity;

import map.Location;
import types.AnimalClass;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxCount, int speed, double foodNeeded, String symbol) {
        super(weight, maxCount, speed, foodNeeded, symbol);
        setCanEatMeat(true);
        setAnimalClass(AnimalClass.PREDATOR);
    }

    // Basic implementation. Next step, add variables with probabilities of eating each other.
    @Override
    public void eat() {
        Location current = getLocation();

        List<Animal> animals = current.getAnimals(AnimalClass.HERBIVORE);

        double eaten = 0;

        if (animals.size() > 0) {
            for (Animal food : animals) {
                int probabilityToEat = ThreadLocalRandom.current().nextInt(10); // without variables, using 30%
                if (eaten <= getFoodNeeded() - food.getWeight() && probabilityToEat <= 3) {
                    eaten += food.getWeight();
                    current.removeAnimal(food);
                }
            }
        }
    }
}
