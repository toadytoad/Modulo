package game;

import javax.swing.*;
import java.awt.*;


/**
 * <h1>World Test</h1>
 *
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
//                random = 1;
                if (random == 0) {
                    map[i][j] = new Tile("WORLD1_GRASSTILE01", false);
                } else {
                    map[i][j] = new Tile("WORLD1_PATHTILE_FULLPATH", true);
                }
//                else if (random == 1){
//                    map[i][j] = new Tile("WORLD1_PATHTILE_FULLPATH", true);
//                } else if (random == 3) {
//                    map[i][j] = new Tile("WORLD1_PATHTILE_LEFTEDGE", true);
//                } else {
//                    map[i][j] = new Tile("WORLD1_PATHTILE_RIGHTEDGE", true);
//                }
            }
        }


        JFrame frame = new JFrame("World Test");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        World world = new World(map, new Coordinate(Toolkit.getDefaultToolkit().getScreenSize()));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(world);
        frame.addMouseListener(world);
        frame.addKeyListener(world);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> frame.getContentPane().repaint());
        timer.start();
    }
}