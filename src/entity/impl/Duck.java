package entity.impl;

import entity.Herbivore;

public class Duck extends Herbivore {

    public Duck() {
        super();
    }

    @Override
    public boolean isCanEatMeat() {
        return true;
    }
}
