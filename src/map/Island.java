package map;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Island {
    private final Location[][] map;

    public Island(int width, int height) {
        map = new Location[width][height];
        //Initialize, all elements are null
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[j][i] = new Location(i, j);
            }
        }
    }

    public List<Location> getAllLocations() {
        return Arrays.stream(map).flatMap(Arrays::stream).toList();
    }

    public List<Location> getLocationsWithAnimals() {
        return getAllLocations().stream()
                .filter(location -> !location.getAnimals().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Location> getEmptyLocations() {
        return getAllLocations().stream()
                .filter(location -> location.getAnimals().isEmpty())
                .collect(Collectors.toList());
    }

    public Location getLocation(int x, int y) {
            if (x < 0 || y < 0) return null;
            if (x < map[0].length && y < map.length)
                return map[y][x];

        return null;
    }

    public Location getRandomEmptyLocation() {
        List<Location> emptyLocations = getEmptyLocations();
        if (emptyLocations.isEmpty()) {
            return getRandomLocation();
        }
        int position = ThreadLocalRandom.current().nextInt(emptyLocations.size());

        return emptyLocations.get(position);
    }

    public Location getRandomLocation() {
        List<Location> allLocations = getAllLocations();
        int position = ThreadLocalRandom.current().nextInt(allLocations.size());

        return allLocations.get(position);
    }

    public Location getRandomLocation(Location current, int speed) {
        int x = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int y = ThreadLocalRandom.current().nextInt(-speed, speed + 1);

        return getLocation(x + current.getX(), y + current.getY());
    }

}
