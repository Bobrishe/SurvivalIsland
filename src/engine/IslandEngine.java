package engine;

import entity.Animal;
import map.Island;
import map.Location;
import util.AnimalUtil;
import util.Statistics;

import java.util.List;
import java.util.concurrent.*;

public class IslandEngine {
    private final Island island;
    private final int ANIMAL_IN_START = 20;
    private final int PLANTS_IN_START = 10;
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public IslandEngine(Island island) {
        this.island = island;
    }

    public void startSimulation() {
        initIsland();
        executorService.scheduleWithFixedDelay(new IslandRunner(), 0, 1, TimeUnit.SECONDS);
    }

    //Optional. If we don't need to generate random map, this method and some other methods/class could be removed
    private void initIsland() {
        for (int i = 0; i < ANIMAL_IN_START; i++) {
            island.getRandomEmptyLocation().addAnimal(AnimalUtil.getRandomAnimal());
        }
        for (int i = 0; i < PLANTS_IN_START; i++) {
            island.getRandomEmptyLocation().growPlant();
        }
    }

    private class IslandRunner implements Runnable {
        @Override
        public void run() {
            int nThreads = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);

            for (Location location : island.getLocationsWithAnimals()) {
                executor.submit(() -> {
                    List<Animal> animals = location.getAnimals();
                    // use FOR , because location is changing during animal activities, and foreach loop could be broken (I've checked)
                    for (int i = 0; i < animals.size(); i++) {
                        Animal animal = animals.get(i);
                        animal.move(island);
                        animal.eat();
                        animal.reproduce();
                    }
                });
            }

            // Need this to be sure that all threads are finished, or it can lead to a loop
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
            while (threadPoolExecutor.getActiveCount() > 0) {
            }

            Statistics.print(island);
        }
    }

}
