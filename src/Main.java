import engine.IslandEngine;
import map.Island;

public class Main {

    private static final int ISLAND_WIDTH = 10;
    private static final int ISLAND_HEIGHT = 10;

    public static void main(String[] args) {
        new IslandEngine(new Island(ISLAND_WIDTH, ISLAND_HEIGHT)).startSimulation();
    }
}