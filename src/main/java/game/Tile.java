package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Class to represent a tile of the level map. Also lists all the possible Images
 * that Tiles can have through the tileSprites HashMap.
 *
 * @author Luke Mathieu
 */

public class Tile {
    private Image sprite;
    private boolean isWalkable;
    private final static HashMap<String, Image> tileSprites;

    /*
        Initializes the tileSprites HashMap to hold
        all the Tile sprites.
     */
    static {
        tileSprites = new HashMap<>();
        tileSprites.put("WORLD1_GRASSTILE01", Toolkit.getDefaultToolkit().getImage("assets/world1/GrassTile01.png"));
        tileSprites.put("WORLD1_GRASSTILE02", Toolkit.getDefaultToolkit().getImage("assets/world1/GrassTile02.png"));
        tileSprites.put("WORLD1_GRASSTILE03", Toolkit.getDefaultToolkit().getImage("assets/world1/GrassTile03.png"));
        tileSprites.put("WORLD1_GRASSTILE04", Toolkit.getDefaultToolkit().getImage("assets/world1/GrassTile04.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMEDGE", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_BottomEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMLEFTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_BottomLeftSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMLEFTWIDECORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_BottomLeftWideCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMRIGHTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_BottomRightSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_BOTTOMRIGHTWIDECORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_BottomRightWideCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_FULLPATH", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_FullPath.png"));
        tileSprites.put("WORLD1_PATHTILE_LEFTEDGE", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_LeftEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_RIGHTEDGE", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_RightEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPEDGE", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_TopEdge.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPLEFTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_TopLeftSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPLEFTWIDECORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_TopLeftWideCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPRIGHTSMALLCORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_TopRightSmallCorner.png"));
        tileSprites.put("WORLD1_PATHTILE_TOPRIGHTWIDECORNER", Toolkit.getDefaultToolkit().getImage("assets/world1/PathTile_TopRightWideCorner.png"));
        tileSprites.put("WORLD2_MAZETILE", Toolkit.getDefaultToolkit().getImage("assets/world2/MazeTile.png"));
        tileSprites.put("WORLD2_MAZETILE_LIT", Toolkit.getDefaultToolkit().getImage("assets/world2/MazeTile_Fire.png"));
        tileSprites.put("TEXTURENOTFOUND_ERRORTILE", Toolkit.getDefaultToolkit().getImage("assets/TextureNotFound_ErrorTile.png"));
        tileSprites.put("TEXTURENOTFOUND_ERRORTILE02", Toolkit.getDefaultToolkit().getImage("assets/TextureNotFound_ErrorTile02.png"));
        tileSprites.put("WORLD3_DOORTILE", Toolkit.getDefaultToolkit().getImage("assets/MazeDoor.png"));
        {
            BufferedImage black = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = black.createGraphics();
            g2d.setBackground(Color.black);
            g2d.clearRect(0, 0, black.getWidth(), black.getHeight());
            tileSprites.put("BLACK", black);
        }
        {
            BufferedImage gray = new BufferedImage(32,32,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = gray.createGraphics();
            g2d.setBackground(new Color(89, 89, 92));
            g2d.clearRect(0, 0, gray.getWidth(), gray.getHeight());
            tileSprites.put("WORLD2_SMOOTHTILE", gray);
        }
        tileSprites.put("EMPTY", null);

    }

    /**
     * Constructs a new Tile.
     * @param tileImage The tileImage this Tile should have
     * @param isWalkable Whether this Tile is walkable (can be traversed by the player).
     */
    public Tile (String tileImage, boolean isWalkable) {
        this.sprite = tileSprites.get(tileImage);
        this.isWalkable = isWalkable;
    }

    /**
     * Default method that is called when the player runs into a Tile.
     * Can be overridden to attach behaviours to a Tile.
     */
    public void interact() {}

    public void setImage(String tileImage){
        this.sprite = tileSprites.get(tileImage);
    }
    public void setWalkable(boolean isWalkable) {this.isWalkable = isWalkable;}
    public boolean getWalkable() {return isWalkable;}

    /**
     * Paints this Tile at the specified coordinates and screen offset.
     * @param g The Graphics context to be painted on.
     * @param x This Tile's x-position on the map.
     * @param y This Tile's y-position on the map.
     * @param screenOffset The current screen offset.
     */
    public void paint (Graphics g, int x, int y, Coordinate screenOffset) {
        g.drawImage(sprite, (x-screenOffset.x)*World.TILE_LENGTH, (y-screenOffset.y)*World.TILE_LENGTH, World.TILE_LENGTH, World.TILE_LENGTH, null);
    }
}
