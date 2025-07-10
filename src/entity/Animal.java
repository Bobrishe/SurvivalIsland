package entity;

import map.Island;
import map.Location;
import types.AnimalClass;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {
    //initialise table parameters as final, because they are constant during the live cycle
    protected final double weight;
    protected final int maxCount;
    protected final int speed;
    protected final double foodNeeded;
    protected final String symbol;
    protected boolean canEatMeat = false;
    protected boolean canEatPlant = false;
    protected AnimalClass animalClass;
    protected Location location;

    public Animal(double weight, int maxCount, int speed, double foodNeeded, String symbol) {
        this.weight = weight;
        this.maxCount = maxCount;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.symbol = symbol;
    }

    public void move(Island island) {
        //We can use `while` , but if animal can't find the way in 10 attempt, we should continue
        for (int i = 0; i < 10; i++) {
            Location current = location;
            Location newLocation = island.getRandomLocation(current, speed);

            if (newLocation != null && newLocation.getAnimals(getClass()).size() < maxCount) {
                this.setLocation(newLocation);
                newLocation.addAnimal(this);
                current.removeAnimal(this);
                break;
            }
        }
    }

    public abstract void eat();

    public void reproduce() {
        Map<Class, List<Animal>> animalsMap = location.getAnimalsMap();

        for (Class className : animalsMap.keySet()) {
            int probablyReproduce = ThreadLocalRandom.current().nextInt(10);// Added prob to reproduce
            List<Animal> animals = animalsMap.get(className);
            if (animals.size() > 1 && probablyReproduce > 5 && animals.size() < maxCount) {
                try {
                    Animal justBorn = (Animal) className.newInstance();
                    justBorn.setLocation(location);
                    location.addAnimal(justBorn);
                } catch (Exception e) {
                    //animal wasn't born;
                }
            }
        }
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getSpeed() {
        return speed;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setCanEatMeat(boolean eatMeat) {
        canEatMeat = eatMeat;
    }

    public void setCanEatPlant(boolean eatPlant) {
        canEatPlant = eatPlant;
    }

    public boolean isCanEatMeat() {
        return canEatMeat;
    }

    public boolean isCanEatPlant() {
        return canEatPlant;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAnimalClass(AnimalClass animalClass) {
        this.animalClass = animalClass;
    }

    public AnimalClass getAnimalClass() {
        return animalClass;
    }
}
