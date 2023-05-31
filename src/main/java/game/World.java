package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>World</h1>
 * Class to store and display the map of a single level of the game.
 *
 * @author Luke Mathieu
 */

public class World extends JComponent implements KeyListener {
    private final Tile[][] map;
    private final java.util.List<Decoration> decorationLayer;
    Player player = new Player(0, 0);
    private Coordinate screenOffset;
    private final Coordinate tilesOnScreen;
    public final static int TILE_LENGTH = 60;
    public final static Coordinate SCREEN_SIZE = new Coordinate(Toolkit.getDefaultToolkit().getScreenSize());

    public World (Tile[][] map, List<Decoration> decorationLayer) {
        screenOffset = new Coordinate(0, 0);
        this.map = map;
        this.decorationLayer = decorationLayer;
        tilesOnScreen = new Coordinate(SCREEN_SIZE.x / TILE_LENGTH+1, SCREEN_SIZE.y / TILE_LENGTH+1);
    }

    public void paint (Graphics g) {
        for (int i = 0; i < tilesOnScreen.x && i < map.length; i++) {
            for (int j = 0; j < tilesOnScreen.y && j < map[0].length; j++) {
                map[screenOffset.x + i][screenOffset.y + j].paint(g,i, j);
            }
        }

        for (Decoration decoration : decorationLayer) {
            if (decoration.coordinate.x + decoration.decorationImage.size.x > screenOffset.x
                    && decoration.coordinate.y + decoration.decorationImage.size.y > screenOffset.y
                    && decoration.coordinate.x < screenOffset.x + tilesOnScreen.x
                    && decoration.coordinate.x < screenOffset.x + tilesOnScreen.x) {
                decoration.paint(g, screenOffset);
            }
        }

        Coordinate movingTo = player.movingTo();
        if (movingTo.x < map.length && movingTo.x >= 0 && movingTo.y < map[0].length && movingTo.y >= 0) {
            if (map[movingTo.x][movingTo.y] instanceof Door) {
                player.setMovement(0, 0);
//                player.coordinate = new Coordinate(0, 0);
                map[movingTo.x][movingTo.y].interact();
            }
            else if (map[movingTo.x][movingTo.y].getWalkable()) {
                player.move();
            }
        }
        Coordinate playerCoordinatesOnScreen = new Coordinate(player.coordinate.x - screenOffset.x, player.coordinate.y - screenOffset.y);
        if (playerCoordinatesOnScreen.x <= 3 && screenOffset.x > 0) {
            screenOffset = new Coordinate(screenOffset.x - 1, screenOffset.y);
        }
        if (playerCoordinatesOnScreen.x >= tilesOnScreen.x - 3 && screenOffset.x < map[0].length - tilesOnScreen.x) {
            screenOffset = new Coordinate(screenOffset.x + 1, screenOffset.y);
        }
        if (playerCoordinatesOnScreen.y <= 3 && screenOffset.y > 0) {
            screenOffset = new Coordinate(screenOffset.x, screenOffset.y - 1);
        }
        if (playerCoordinatesOnScreen.y >= tilesOnScreen.y - 3 && screenOffset.y < map.length - tilesOnScreen.y) {
            screenOffset = new Coordinate(screenOffset.x, screenOffset.y + 1);
        }
        /*
        TODO: fine tune numbers in if statement
         */
        player.paint(g, screenOffset);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int x = 0, y = 0;
        switch (e.getKeyChar()) {
            case 'w':
                y -= 1;
                break;
            case 's':
                y += 1;
                break;
            case 'a':
                x -= 1;
                break;
            case 'd':
                x += 1;
                break;
        }
        player.setMovement(x, y);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.setMovement(0, 0);
    }

    /**
     * Generates a random World containing only walkable and non-walkable Tiles.
     * @return A new World object, with a random map of walkable and non-walkable Tiles.
     */
    public static World generateRandomWorld(Coordinate size) {
        Tile[][] map = new Tile[size.x][size.y];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int random = (int)(Math.random()*4);
                if (random == 0) {
                    map[i][j] = new Tile("WORLD1_GRASSTILE01", false);
                } else {
                    map[i][j] = new Tile("WORLD1_PATHTILE_FULLPATH", true);
                }
            }
        }
        return new World(map, new ArrayList<>());
    }

    /**
     * Generates a random world (see generateRandomWorld()) with a single door, which leads to the
     * world given by doorTarget
     * @param size The size of the World to be generated.
     * @param doorTarget The world index that the door will lead to.
     * @return A new random World object, containing a single Door to another World.
     */
    public static World generateRandomWorldWithDoors (Coordinate size, int doorTarget) {
        World world = generateRandomWorld(size);
        Coordinate doorCoordinate = new Coordinate((int)(Math.random()*size.x), (int)(Math.random()*size.y));
        world.map[doorCoordinate.x][doorCoordinate.y] = new Door("TEXTURENOTFOUND_ERRORTILE", doorTarget);
        return world;
    }

    public static World generateWorldFromFile (String path) {
        return null;
    }
}
