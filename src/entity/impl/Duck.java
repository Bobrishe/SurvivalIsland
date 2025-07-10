package entity.impl;

import entity.Herbivore;

public class Duck extends Herbivore {

    public Duck() {
        super(1, 200, 4, 0.15, "\uD83E\uDD86");
        setCanEatMeat(true);
    }
}
