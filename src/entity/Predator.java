package entity;

import map.Location;
import types.AnimalClass;
import util.AnimalProperties;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    protected static AnimalProperties animalProperties = AnimalProperties.getInstance();

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

        String predatorName = this.getClass().getSimpleName().toLowerCase();
        String foodName = food.getClass().getSimpleName().toLowerCase();
        String property = predatorName + '.' + foodName;

        return animalProperties.getIntProperty(property);
    }

}
