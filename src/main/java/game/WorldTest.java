package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Driver class to test basic World functionality
 *
 * @author Luke Mathieu
 */

public class WorldTest {

    public static void main (String[] args) {
        Tile[][] map = new Tile[100][100];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int random = (int)(Math.random()*4);
                if (random == 0) {
                    map[i][j] = new Tile("WORLD1_GRASSTILE01", false);
                } else {
                    map[i][j] = new Tile("WORLD1_PATHTILE_FULLPATH", true);
                }
            }
        }
        java.util.List<Decoration> decorationList = new ArrayList<>();
        decorationList.add(new Decoration("TREE", new Coordinate(10, 10)));


        JFrame frame = new JFrame("World Test");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        World world = new World(map, decorationList);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(world);
        frame.addKeyListener(world);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> frame.getContentPane().repaint());
        timer.start();
    }
}