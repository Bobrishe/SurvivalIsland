package Factory;

import Entity.Animal;
import Types.AnimalType;
import Entity.Rabbit;
import Entity.Wolf;

public class AnimalFactory {

    public Animal getAnimal(AnimalType type) {
        Animal animal = null;

        if (type == null) {
            throw new IllegalArgumentException();
        }

        switch (type) {
            case WOLF -> {
                animal = new Wolf(50, 30, 3, 8);
            }
            case RABBIT -> {
                animal = new Rabbit(2, 150, 2, 8);
            }
        }

        return animal;
    }
}
