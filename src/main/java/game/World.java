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
    final static int TILE_LENGTH = 80;

    public World (int rows, int columns) {
        map = new Tile[rows][columns];
    }

    public World (Tile[][] map) {
        this.map = map;
        buttons = new ArrayList<>();

    }

    public void paint (Graphics g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].paint(g,i*TILE_LENGTH, j*TILE_LENGTH,TILE_LENGTH,TILE_LENGTH );
            }
        }
        player.move();
        player.paint(g,TILE_LENGTH*player.coordinate.x,TILE_LENGTH*player.coordinate.y,TILE_LENGTH, TILE_LENGTH);
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
