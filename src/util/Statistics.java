package util;

import entity.Animal;
import map.Island;
import map.Location;
import types.AnimalClass;

import java.util.HashMap;

public class Statistics {
    public static void print(Island island) {
        HashMap<String, Integer> animalCounts = new HashMap<>();
        HashMap<String, String> icons = new HashMap<>();
        int totalAnimalSize = 0;
        int totalPredators = 0;
        int totalHerbivore = 0;
        int totalPlants = 0;

        for (Location location : island.getAllLocations()) {
            for (Animal animal : location.getAnimals()) {
                String name = animal.getClass().getSimpleName();
                icons.put(name, animal.getIcon());
                animalCounts.put(name, animalCounts.getOrDefault(name, 0) + 1);
                totalAnimalSize++;
                totalPredators += animal.getAnimalClass().equals(AnimalClass.PREDATOR) ? 1 : 0;
                totalHerbivore += animal.getAnimalClass().equals(AnimalClass.HERBIVORE) ? 1 : 0;
            }

            totalPlants += location.getPlants().size();
        }

        System.out.println("Total animals amount - " + totalAnimalSize);
        System.out.println("Total plants - " + totalPlants);
        System.out.println("Predators amount - " + totalPredators);
        System.out.println("Herbivores amount - " + totalHerbivore);
        for (String animalName : animalCounts.keySet()) {
            System.out.printf("%s %s population - %s\n", icons.get(animalName), animalName, animalCounts.get(animalName));
        }
        System.out.println();
    }
}
