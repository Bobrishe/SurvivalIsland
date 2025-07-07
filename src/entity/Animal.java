package entity;

import types.AnimalGroup;

public abstract class Animal {
    protected double weight;
    protected int maxCount;
    protected int speed;
    protected double foodNeeded;
    protected AnimalGroup group;
    protected String symbol;
    private boolean canEatMeat = false;
    private boolean canEatPlant = false;

    public Animal(double weight, int maxCount, int speed, double foodNeeded, String symbol) {
        this.weight = weight;
        this.maxCount = maxCount;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
        this.symbol = symbol;
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

    public void setCanEatMeat(boolean eatMeat) {
        canEatMeat = eatMeat;
    }

    public void setCanEatPlant(boolean eatPlant) {
        canEatPlant = eatPlant;
    }

    public boolean isCanEatMeat() {
        return canEatMeat;
    }

    public boolean isCanEatPlant() {
        return canEatPlant;
    }
}
