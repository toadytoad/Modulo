package game;

import java.awt.*;

public class Player {
    private final Image sprite = Toolkit.getDefaultToolkit().getImage("src/main/java/assets/TextureNotFound_ErrorTile02.png");
    public Coordinate coordinate;

    public void paint (Graphics g, int width, int height) {
        g.drawImage(sprite, coordinate.x, coordinate.y, width, height, null);
    }
}
