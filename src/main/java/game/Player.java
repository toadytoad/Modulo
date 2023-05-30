package game;

import java.awt.*;

public class Player {
private final Image sprite = Toolkit.getDefaultToolkit().getImage("src/main/java/assets/wizardIdle.gif");
    public Coordinate coordinate;
    int xMovement, yMovement;

    public Player (int x, int y) {
        coordinate = new Coordinate(x, y);
        xMovement = 0;
        yMovement = 0;
    }

    public void move() {
        coordinate = new Coordinate(coordinate.x + xMovement, coordinate.y + yMovement);
    }

    public Coordinate movingTo() {
        return new Coordinate(coordinate.x + xMovement, coordinate.y + yMovement);
    }

    public void setMovement(int x, int y) {
        xMovement = x;
        yMovement = y;
    }

    public void paint (Graphics g, Coordinate screenOffset) {
        g.drawImage(sprite, (coordinate.x- screenOffset.x)*World.TILE_LENGTH, (coordinate.y-screenOffset.y)*World.TILE_LENGTH-World.TILE_LENGTH/2, World.TILE_LENGTH, World.TILE_LENGTH *3/2, null);
    }
}
