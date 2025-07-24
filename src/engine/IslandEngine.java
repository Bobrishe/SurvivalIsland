package engine;

import entity.Animal;
import exception.EngineThreadException;
import map.Island;
import map.Location;
import util.AnimalUtil;
import util.Statistics;

import java.util.List;
import java.util.concurrent.*;

public class IslandEngine {
    private final Island island;
    private final int ANIMAL_IN_START = 20;
    private final int DELAY = 1;
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public IslandEngine(Island island) {
        this.island = island;
    }

    public void startSimulation() {
        initIsland();
        executorService.scheduleWithFixedDelay(new IslandRunner(), 0, DELAY, TimeUnit.SECONDS);
    }

    private void initIsland() {
        for (int i = 0; i < ANIMAL_IN_START; i++) {
            island.getRandomEmptyLocation().addAnimal(AnimalUtil.getRandomAnimal());
        }
    }

    private class IslandRunner implements Runnable {
        @Override
        public void run() {
            int nThreads = Runtime.getRuntime().availableProcessors();
            island.getRandomEmptyLocation().growPlant();

            ExecutorService executor = Executors.newFixedThreadPool(nThreads);

            for (Location location : island.getLocationsWithAnimals()) {
                executor.submit(() -> {
                    List<Animal> animals = location.getAnimals();
                    for (int i = 0; i < animals.size(); i++) {
                        Animal animal = animals.get(i);
                        animal.move(island);
                        animal.eat();
                        animal.reproduce();
                    }
                });
            }

            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;

            while (threadPoolExecutor.getActiveCount() > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new EngineThreadException(e.getMessage());
                }
            }

            Statistics.print(island);
        }
    }

}
