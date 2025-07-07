package entity;

import types.AnimalGroup;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxCount, int speed, double foodNeeded, String symbol) {
        super(weight, maxCount, speed, foodNeeded, symbol);
        group = AnimalGroup.HERBIVORE;
        setCanEatPlant(true);
    }
}
