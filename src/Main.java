import engine.IslandEngine;
import map.Island;

public class Main {
    public static void main(String[] args) {
        new IslandEngine(new Island(10,10)).startSimulation();
    }
}