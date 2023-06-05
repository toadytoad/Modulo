package game;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    static JFrame frame;
    static final World[] worlds;
    static int currentWorld = 0;

    static {
        worlds = new World[10];
        worlds[0] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 1);
        worlds[1] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 2);
        worlds[2] = World.generateWorldFromFile("src/main/java/game/testWorld.lvl");
        Tile[][] map = new Tile[9][5];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new Tile("TEXTURENOTFOUND_ERRORTILE02", true);
            }
        }

        worlds[9] = new World(map, new ArrayList<>(), new Coordinate(4, 4));
    }



    public static void setActiveWorld (int worldIndex) {
        frame.remove(worlds[currentWorld]);
        frame.removeKeyListener(worlds[currentWorld]);
        currentWorld = worldIndex;
        frame.addKeyListener(worlds[currentWorld]);
        frame.setTitle("World " + currentWorld);
        frame.add(worlds[currentWorld]);
        frame.revalidate();
    }

    public static void main (String[] args) {
//        Tile[][] map = new Tile[100][100];
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                int random = (int)(Math.random()*4);
//                if (random == 0) {
//                    map[i][j] = new Tile("WORLD1_GRASSTILE01", false);
//                } else {
//                    map[i][j] = new Tile("WORLD1_PATHTILE_FULLPATH", true);
//                }
//            }
//        }
//        java.util.List<Decoration> decorationList = new ArrayList<>();
//        decorationList.add(new Decoration("TREE", new Coordinate(10, 10)));


        frame = new JFrame("World Test");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setActiveWorld(0);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> frame.getContentPane().repaint());
        timer.start();
    }
}
