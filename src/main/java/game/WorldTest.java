package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * <h1>World Test</h1>
 *
 * Driver class to test basic World functionality
 *
 * @author Luke Mathieu
 */

public class WorldTest {
    static JFrame frame;

    public static void main (String[] args) {
        Tile[][] map = new Tile[10][10];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int random = (int)(Math.random()*4);
                random = 0;
                if (random == 0) {
                    map[i][j] = new Tile(Toolkit.getDefaultToolkit().getImage("src/main/java/Assets/pathtile_leftedge-pixilart.png"), false);
                } else if (random == 1){
                    map[i][j] = new Tile(Toolkit.getDefaultToolkit().getImage("src/main/java/Assets/GrassTile02.png"), false);
                } else if (random == 3) {
                    map[i][j] = new Tile(Toolkit.getDefaultToolkit().getImage("src/main/java/Assets/GrassTile03.png"), false);
                } else {
                    map[i][j] = new Tile(Toolkit.getDefaultToolkit().getImage("src/main/java/Assets/GrassTile04.png"), false);
                }
            }
        }

        World world = new World(map);
        frame = new JFrame("World Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.add(world);
        frame.addMouseListener(world);
        frame.addKeyListener(world);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> frame.getContentPane().repaint());
        timer.start();
    }
}