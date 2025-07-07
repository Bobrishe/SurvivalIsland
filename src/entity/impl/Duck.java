package entity.impl;

import entity.Herbivore;

public class Duck extends Herbivore {

    public Duck(double weight, int maxCount, int speed, double foodNeeded) {
        super(weight, maxCount, speed, foodNeeded, "\uD83E\uDD86");
        setCanEatMeat(true);
    }

    @Override
    public void move() {

    }

    @Override
    public void eat() {

    }

    @Override
    public void reproduce() {

    }
}
