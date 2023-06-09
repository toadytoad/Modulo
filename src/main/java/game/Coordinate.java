package game;

import java.awt.*;

/**
 * Data class to store a single x-y coordinate pair, or any other pair of data.
 *
 * @author Luke Mathieu
 */

public class Coordinate {
    public final int x;
    public final int y;

    public Coordinate (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate (Dimension dimension) {
        this.x = dimension.width;
        this.y = dimension.height;
    }

    public String toString () {
        return "x: " + x + " y: " + y;
    }
}
