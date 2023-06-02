package game;

import java.awt.*;
import java.util.HashMap;

public class Decoration {
    public final DecorationImage decorationImage;
    public final Coordinate coordinate;
    private final static HashMap<String, DecorationImage> decorationSprites;

    static {
        decorationSprites = new HashMap<>();
        decorationSprites.put("TREE", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/tree.png"), new Coordinate(3,4)));
    }

    public Decoration (String decorationType, Coordinate coordinate) {
        decorationImage = decorationSprites.get(decorationType);
        this.coordinate = coordinate;
    }

    public void paint (Graphics g, Coordinate screenOffset) {
        g.drawImage(decorationImage.sprite, (coordinate.x - screenOffset.x)*World.TILE_LENGTH, (coordinate.y- screenOffset.y)*World.TILE_LENGTH, World.TILE_LENGTH* decorationImage.size.x, World.TILE_LENGTH* decorationImage.size.y, null);
    }

    static class DecorationImage {
        public final Image sprite;
        public final Coordinate size;

        DecorationImage(Image sprite, Coordinate size) {
            this.sprite = sprite;
            this.size = size;
        }
    }
}
