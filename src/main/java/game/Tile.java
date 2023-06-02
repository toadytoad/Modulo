package game;

import java.awt.*;
import java.util.HashMap;

/**
 * <h1>Tile</h1>
 * Class to represent a tile of the level map.
 *
 * @author Luke Mathieu
 */

public class Tile {
    private Image sprite;
    private final boolean isWalkable;
    private final static HashMap<String, Image> tileSprites;

    static {
        tileSprites = new HashMap<>();
        tileSprites.put("WORLD1_GRASSTILE01", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/GrassTile01.png"));
        tileSprites.put("WORLD1_GRASSTILE02", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/GrassTile02.png"));
        tileSprites.put("WORLD1_GRASSTILE03", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/GrassTile03.png"));
        tileSprites.put("WORLD1_GRASSTILE04", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/GrassTile04.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMEDGE", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_BottomEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMLEFTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_BottomLeftSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMLEFTWIDECORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_BottomLeftWideCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMRIGHTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_BottomRightSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMRIGHTWIDECORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_BottomRightWideCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_FULLPATH", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_FullPath.png"));
        tileSprites.put("WORLD1_PATHTILE_LEFTEDGE", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_LeftEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_RIGHTEDGE", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_RightEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPEDGE", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_TopEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPLEFTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_TopLeftSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPLEFTWIDECORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_TopLeftWideCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPRIGHTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_TopRightSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPRIGHTWIDECORNER", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/world1/PathTile_TopRightWideCorner.png"));
        tileSprites.put("TEXTURENOTFOUND_ERRORTILE", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/TextureNotFound_ErrorTile.png"));
        tileSprites.put("TEXTURENOTFOUND_ERRORTILE02", Toolkit.getDefaultToolkit().getImage("src/main/java/assets/TextureNotFound_ErrorTile02.png"));
    }

    public Tile (String tileImage, boolean isWalkable) {
        this.sprite = tileSprites.get(tileImage);
        this.isWalkable = isWalkable;
    }

    public void interact() {

    }
    public void setImage(String tileImage){
        this.sprite = tileSprites.get(tileImage);
    }
    public boolean getWalkable() {return isWalkable;}

    public void paint (Graphics g, Coordinate coordinate) {
        g.drawImage(sprite, coordinate.x, coordinate.y, null);
    }

    public void paint (Graphics g, int x, int y, int width, int height) {
        g.drawImage(sprite, x, y, width, height, null);
    }
}
