package map;

import java.util.Arrays;
import java.util.List;

public class Island {
    private final Field[][] map;


    public Island(int width, int height) {
        map = new Field[width][height];
    }

    public List<Field> getFieldsList() {
        return Arrays.stream(map).flatMap(Arrays::stream).toList();
    }

    public Field getField(int x, int y) {
        if (x < map[0].length && y < map.length)
            return map[x][y];


        return null;
    }

    // todo in another branch
}
