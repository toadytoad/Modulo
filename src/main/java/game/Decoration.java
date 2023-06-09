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
        decorationSprites.put("X_AXIS_BAR", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/XAxisBar.png"),new Coordinate(9, 1)));
        decorationSprites.put("Y_AXIS_BAR_0_4", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/YAxisBar_0_4.png"),new Coordinate(1, 5)));
        decorationSprites.put("Y_AXIS_BAR_5_9", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/YAxisBar_5_9.png"),new Coordinate(1, 5)));
        decorationSprites.put("Y_AXIS_BAR_10_14", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/YAxisBar_10_14.png"),new Coordinate(1, 5)));
        decorationSprites.put("Y_AXIS_BAR_15_19", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/YAxisBar_15_19.png"),new Coordinate(1, 5)));
        decorationSprites.put("Y_AXIS_BAR_20_24", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/YAxisBar_20_24.png"),new Coordinate(1, 5)));
        decorationSprites.put("STARTER_HOUSE_INTERIOR", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/StarterHouseInterior.png"), new Coordinate(16, 10)));
        decorationSprites.put("SCHOOL_INTERIOR", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/LearningHouseInterior.png"), new Coordinate(11, 9)));
        decorationSprites.put("SCHOOL_TEACHER_1", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/Teacher01.gif"), new Coordinate(1, 2)));
        decorationSprites.put("HOUSE_EXTERIOR", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/StarterHouse.png"), new Coordinate(5,6)));
        decorationSprites.put("SCHOOL_EXTERIOR", new DecorationImage(Toolkit.getDefaultToolkit().getImage("src/main/java/assets/decorations/StarterHouse.png"), new Coordinate(8,6)));
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
