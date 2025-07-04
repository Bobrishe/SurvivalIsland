package Entity;

import Types.AnimalGroup;

public abstract class Predator extends Animal {
    public Predator(double weight, int maxCount, int speed, double foodNeeded) {
        super(weight, maxCount, speed, foodNeeded);
        group = AnimalGroup.PREDATOR;
    }
}
