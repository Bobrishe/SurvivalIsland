package entity;

import map.Island;
import map.Location;
import types.AnimalClass;
import util.AnimalProperties;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public abstract class Animal {
    protected final double weight;
    protected final int maxCount;
    protected final int speed;
    protected final double foodNeeded;
    protected final String icon;
    protected Location location;
    protected final int REPRODUCE_PROBABILITY = 5;
    protected static AnimalProperties animalProperties = AnimalProperties.getInstance();

    public Animal(double weight, int maxCount, int speed, double foodNeeded, String icon) {
        this.weight = weight;
        this.maxCount = maxCount;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.icon = icon;
    }

    public static AnimalProperties getAnimalProperties() {
        return animalProperties;
    }

    public void move(Island island) {
        Location current = location;

        // use anyMath to break, after successful result
        IntStream.range(0, 10).anyMatch(i -> {

            Location newLocation = island.getRandomLocation(current, speed);

            if (newLocation != null && newLocation.getAnimals(getClass()).size() < maxCount) {
                this.setLocation(newLocation);
                newLocation.addAnimal(this);
                current.removeAnimal(this);
                return true;
            }

            return false;
        });
    }

    public void reproduce() {
        Map<Class<? extends Animal>, List<Animal>> animalsMap = location.getAnimalsMap();

        animalsMap.keySet().forEach(className -> {
            int probablyReproduce = ThreadLocalRandom.current().nextInt(10);// Added prob to reproduce
            List<Animal> animals = animalsMap.get(className);
            if (animals.size() > 1 && probablyReproduce > REPRODUCE_PROBABILITY && animals.size() < maxCount) {
                try {
                    Animal justBorn = className.getDeclaredConstructor().newInstance();
                    justBorn.setLocation(location);
                    location.addAnimal(justBorn);
                } catch (Exception e) {
                    //animal wasn't born;
                }
            }
        });
    }

    public double getWeight() {
        return weight;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public String getIcon() {
        return icon;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract void eat();

    public abstract AnimalClass getAnimalClass();

    public abstract boolean isCanEatMeat();

}
