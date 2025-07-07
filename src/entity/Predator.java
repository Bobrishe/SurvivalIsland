package entity;

import types.AnimalGroup;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxCount, int speed, double foodNeeded, String symbol) {
        super(weight, maxCount, speed, foodNeeded, symbol);
        group = AnimalGroup.PREDATOR;
        setCanEatMeat(true);
    }
}
