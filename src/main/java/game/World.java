package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
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
    public static HashMap<Character, Boolean> keysPressed;

    static {
        keysPressed = new HashMap<>();
        keysPressed.put('w', false);
        keysPressed.put('s', false);
        keysPressed.put('a', false);
        keysPressed.put('d', false);
    }

    public World (Tile[][] map, List<Decoration> decorationLayer) {
        screenOffset = new Coordinate(0, 0);
        this.map = map;
        this.decorationLayer = decorationLayer;
        tilesOnScreen = new Coordinate(SCREEN_SIZE.x / TILE_LENGTH, SCREEN_SIZE.y / TILE_LENGTH - 1);
        if (map.length < tilesOnScreen.x) {
            screenOffset = new Coordinate(Math.abs(tilesOnScreen.x - map.length) / -2, 0);
        }
        if (map[0].length < tilesOnScreen.y) {
            screenOffset = new Coordinate(screenOffset.x, Math.abs(map.length - tilesOnScreen.y) / -2);
        }
    }

    public World (Tile[][] map, List<Decoration> decorationLayer, Coordinate playerCoordinates) {
        this(map, decorationLayer);
        this.player.coordinate = playerCoordinates;
    }

    public void paint (Graphics g) {
        for (int i = Math.max(0, screenOffset.x); i < tilesOnScreen.x + screenOffset.x && i < map.length; i++) {
            for (int j = Math.max(0, screenOffset.y); j < tilesOnScreen.y + screenOffset.y && j < map[0].length; j++) {
                map[i][j].paint(g,i, j, screenOffset);
            }
        }

        for (Decoration decoration : decorationLayer) {
            if (decoration.coordinate.x + decoration.decorationImage.size.x > screenOffset.x
                    && decoration.coordinate.y + decoration.decorationImage.size.y > screenOffset.y
                    && decoration.coordinate.x < screenOffset.x + tilesOnScreen.x
                    && decoration.coordinate.y < screenOffset.y + tilesOnScreen.y) {
                decoration.paint(g, screenOffset);
            }
        }

        short x = 0, y = 0;
        if (keysPressed.get('w')) y -= 1;
        if (keysPressed.get('s')) y += 1;
        if (keysPressed.get('a')) x -= 1;
        if (keysPressed.get('d')) x += 1;

        player.setMovement(x, y);

        Coordinate movingTo = player.movingTo();
        if (movingTo.x < map.length && movingTo.x >= 0 && movingTo.y < map[0].length && movingTo.y >= 0) {
            if (map[movingTo.x][movingTo.y] instanceof Door) {
                player.setMovement(0, 0);
//                player.coordinate = new Coordinate(0, 0);
                map[movingTo.x][movingTo.y].interact();
            }
            else if (map[movingTo.x][movingTo.y].getWalkable()
                    && map[movingTo.x][player.coordinate.y].getWalkable()
                    && map[player.coordinate.x][movingTo.y].getWalkable()) {
                player.move();
            }
        }
        Coordinate playerCoordinatesOnScreen = new Coordinate(player.coordinate.x - screenOffset.x, player.coordinate.y - screenOffset.y);
        if (playerCoordinatesOnScreen.x <= 3 && screenOffset.x > 0) {
            screenOffset = new Coordinate(screenOffset.x - 1, screenOffset.y);
        }
        if (playerCoordinatesOnScreen.x >= tilesOnScreen.x - 3 && screenOffset.x < map.length- tilesOnScreen.x) {
            screenOffset = new Coordinate(screenOffset.x + 1, screenOffset.y);
        }
        if (playerCoordinatesOnScreen.y <= 3 && screenOffset.y > 0) {
            screenOffset = new Coordinate(screenOffset.x, screenOffset.y - 1);
        }
        if (playerCoordinatesOnScreen.y >= tilesOnScreen.y - 3 && screenOffset.y < map[0].length - tilesOnScreen.y) {
            screenOffset = new Coordinate(screenOffset.x, screenOffset.y + 1);
        }
        /*
        TODO: fine tune numbers in if statement
         */
        player.paint(g, screenOffset);
//        System.out.print(screenOffset + "\t" + player.coordinate + "\n");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 's' || e.getKeyChar() == 'a' || e.getKeyChar() == 'd') keysPressed.put(e.getKeyChar(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyChar() == 's' || e.getKeyChar() == 'a' || e.getKeyChar() == 'd') keysPressed.put(e.getKeyChar(), false);
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
        map[0][0] = new Tile ("WORLD1_PATHTILE_FULLPATH", true);
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringTokenizer st = new StringTokenizer(reader.readLine());
            Tile[][] map = new Tile[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
            Coordinate playerCoordinate = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    st = new StringTokenizer(reader.readLine());
                    String token;
                    switch (token = st.nextToken()) {
                        case "DOOR":
                            map[i][j] = new Door("TEXTURENOTFOUND_ERRORTILE", Integer.parseInt(token));
                            break;
                        default:
                            map[i][j] = new Tile(token, Boolean.parseBoolean(st.nextToken()));
                            break;
                    }
                }
            }
            reader.readLine(); // To remove the *** line from the input file
            List<Decoration> decorationList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null ) {
                st = new StringTokenizer(line);
                decorationList.add(new Decoration(st.nextToken(), new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))));
            }
            return new World(map, decorationList, playerCoordinate);
        } catch (IOException e) {
            System.out.println("File " + path + " is formatted improperly.");
            System.exit(-1);
            return null;
        }
    }
}
