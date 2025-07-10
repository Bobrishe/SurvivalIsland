package util;

import entity.Animal;
import entity.impl.*;
import types.AnimalType;

import java.util.Random;

//Basically, this is helper to generate random animals for filling the startup map
public class AnimalUtil {

    private static final Random random = new Random();

    public static Animal getAnimalByType(AnimalType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }

        Animal animal = switch (type) {
            case WOLF -> new Wolf();
            case RABBIT -> new Rabbit();
            case FOX -> new Fox();
            case BOA -> new Boa();
            case BEAR -> new Bear();
            case HORSE -> new Horse();
            case CATERPILLAR -> new Caterpillar();
            case DUCK -> new Duck();
        };

        return animal;
    }

    public static Animal getRandomAnimal() {
        AnimalType[] types = AnimalType.values();
        return getAnimalByType(types[random.nextInt(types.length)]);
    }
}
