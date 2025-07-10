package util;

import entity.Animal;
import map.Island;
import map.Location;

import java.util.HashMap;

public class Statistics {
    public static void print(Island island) {
        HashMap<String, Integer> animalCounts = new HashMap<>();

        for (Location location : island.getAllLocations()) {
            for (Animal animal : location.getAnimals()) {
                String name = animal.getClass().getSimpleName();
                animalCounts.put(name, animalCounts.getOrDefault(name, 0) + 1);
            }
        }

        System.out.println("Statistic");
        System.out.println(animalCounts);

    }
}
