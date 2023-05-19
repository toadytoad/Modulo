package Game;

import java.awt.*;

/**
 * <h1>Tile</h1>
 * Class to represent a tile of the level map.
 *
 * @author Luke Mathieu
 */

public class Tile {
    private Image sprite;
    private final boolean isWalkable;

    public Tile (Image sprite, boolean isWalkable) {
        this.sprite = sprite;
        this.isWalkable = isWalkable;
    }

    public void interact() {

    }

    public void setImage (Image sprite) {
        this.sprite = sprite;
    }

    public boolean getWalkable() {return isWalkable;}

    public void scaleImage(int width, int height) {
        sprite = sprite.getScaledInstance(width,height,Image.SCALE_DEFAULT);

    }

    public void paint (Graphics g, Coordinate coordinate) {
        g.drawImage(sprite, coordinate.x, coordinate.y, null);
    }

    public void paint (Graphics g, int x, int y, int width, int height) {
        g.drawImage(sprite, x, y, width, height, null);
    }
}
