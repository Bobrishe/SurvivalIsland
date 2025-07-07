package factory;

import entity.Animal;
import entity.impl.*;
import types.AnimalType;

public class AnimalFactory {

    private static AnimalFactory INSTANCE;

    public static AnimalFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AnimalFactory();
        }

        return INSTANCE;
    }

    public Animal getAnimal(AnimalType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }

        Animal animal = switch (type) {
            case WOLF -> new Wolf(50, 30, 3, 8);
            case RABBIT -> new Rabbit(2, 150, 2, 8);
            case FOX -> new Fox(8, 30, 2, 2);
            case BOA -> new Boa(15, 30, 1, 3);
            case BEAR -> new Bear(500, 5, 2, 80);
            case HORSE -> new Horse(400, 20, 4, 60);
            case CATERPILLAR -> new Caterpillar(0.01, 1000, 0, 0);
            case DUCK -> new Duck(1, 200, 4, 0.15);
        };

        return animal;
    }
}
