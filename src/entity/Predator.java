package entity;

import map.Location;
import types.AnimalClass;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    public Predator() {
        super();
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
                int probabilityToEat = animalProperties.getEatProbability(this, food);

                if (probabilityToEat > 0) {
                    if (eaten <= getFoodNeeded() - food.getWeight() && randomProbability <= probabilityToEat) {
                        eaten += food.getWeight();
                        current.removeAnimal(food);
                    }
                }
            }
        }
    }

}
