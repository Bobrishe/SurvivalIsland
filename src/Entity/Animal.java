package Entity;

import Types.AnimalGroup;

public abstract class Animal {
    protected double weight;
    protected int maxCount;
    protected int speed;
    protected double foodNeeded;
    protected AnimalGroup group;
    protected String symbol;

    public Animal(double weight, int maxCount, int speed, double foodNeeded) {
        this.weight = weight;
        this.maxCount = maxCount;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
    }

    public abstract void move();

    public abstract void eat();

    public abstract void reproduce();

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
}
