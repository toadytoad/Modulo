package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
/**
 * <h1>World</h1>
 * Class to store and display the map of a single level of the game.
 *
 * @author Luke Mathieu
 */

public class World extends JComponent implements MouseListener, KeyListener {
    private final Tile[][] map;
    Player player = new Player(0, 0);
    java.util.List<Button> buttons;
    private Coordinate screenOffset;
    private final Coordinate tilesOnScreen;
    final static int TILE_LENGTH = 60;

    public World (Tile[][] map, Coordinate screenSize) {
        screenOffset = new Coordinate(0, 0);
        this.map = map;
        tilesOnScreen = new Coordinate(screenSize.x / TILE_LENGTH+1, screenSize.y / TILE_LENGTH+1);
        buttons = new ArrayList<>();
    }

    public void paint (Graphics g) {
        for (int i = 0; i < tilesOnScreen.x && i < map.length; i++) {
            for (int j = 0; j < tilesOnScreen.y && j < map[0].length; j++) {
                map[screenOffset.x + i][screenOffset.y + j].paint(g,i*TILE_LENGTH, j*TILE_LENGTH,TILE_LENGTH,TILE_LENGTH );
            }
        }

        if (player.movingTo().x < map.length && player.movingTo().x >= 0 && player.movingTo().y < map[0].length && player.movingTo().y >= 0) {
            if (map[player.coordinate.x + player.xMovement][player.coordinate.y + player.yMovement].getWalkable()) {
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

        player.paint(g,TILE_LENGTH, screenOffset);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

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

    abstract class Button {
        Rectangle box;
        public abstract void interact();
    }
}
