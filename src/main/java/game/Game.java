package game;

import javax.swing.*;

public class Game {
    static JFrame frame;
    static final World[] worlds;
    static int currentWorld = 0;

    static {
        worlds = new World[10];
        worlds[0] = World.generateRandomWorldWithDoors(new Coordinate(40, 20), 1);
        worlds[1] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 0);
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
