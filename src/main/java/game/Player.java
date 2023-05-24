package game;

import java.awt.*;

public class Player {
    private final Image sprite = Toolkit.getDefaultToolkit().getImage("src/main/java/assets/TextureNotFound_ErrorTile02.png");
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

    public void setMovement(int x, int y) {
        xMovement = x;
        yMovement = y;
    }

    public void paint (Graphics g, int x, int y, int width, int height) {
        g.drawImage(sprite, x, y, width, height, null);
    }
}
