package entity.impl;

import entity.Herbivore;

public class Horse extends Herbivore {

    public Horse(double weight, int maxCount, int speed, double foodNeeded) {
        super(weight, maxCount, speed, foodNeeded, "\uD83D\uDC0E");
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
