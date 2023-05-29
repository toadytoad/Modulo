package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
/**
 * <h1>World</h1>
 * Class to store and display the map of a single level of the game.
 *
 * @author Luke Mathieu
 */

public class World extends JComponent implements MouseListener {
    private final Tile[][] map;
    Coordinate playerCoordinate;
    final static int TILE_LENGTH = 80;

    public World (int rows, int columns) {
        map = new Tile[rows][columns];
    }

    public World (Tile[][] map) {
        this.map = map;
    }

    public void paint (Graphics g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j].paint(g,i*TILE_LENGTH, j*TILE_LENGTH,TILE_LENGTH,TILE_LENGTH );
            }
        }
        repaint();
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
}
