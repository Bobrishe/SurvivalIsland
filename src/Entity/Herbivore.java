package Entity;

import Types.AnimalGroup;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, int maxCount, int speed, double foodNeeded) {
        super(weight, maxCount, speed, foodNeeded);
        group = AnimalGroup.HERBIVORE;
    }
}
