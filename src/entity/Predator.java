package entity;

import map.Location;
import types.AnimalClass;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxCount, int speed, double foodNeeded, String icon) {
        super(weight, maxCount, speed, foodNeeded, icon);
    }

    @Override
    public boolean isCanEatMeat() {
        return true;
    }

    @Override
    public AnimalClass getAnimalClass() {
        return AnimalClass.PREDATOR;
    }

    // Basic implementation. Next step, add variables with probabilities of eating each other.
    @Override
    public void eat() {
        Location current = getLocation();

        List<Animal> animals = current.getAnimals(AnimalClass.HERBIVORE);

        double eaten = 0;

        if (!animals.isEmpty()) {
            for (Animal food : animals) {
                int randomProbability = ThreadLocalRandom.current().nextInt(100);
                int probabilityToEat = getEatProbability(food);

                if (probabilityToEat > 0) {
                    if (eaten <= getFoodNeeded() - food.getWeight() && randomProbability <= probabilityToEat) {
                        eaten += food.getWeight();
                        current.removeAnimal(food);
                    }
                }
            }
        }
    }

    public int getEatProbability(Animal food) {
        return getAnimalProperties().getProbability(this, food);
    }

}
