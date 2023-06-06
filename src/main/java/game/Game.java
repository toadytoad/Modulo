package game;

import problem.LatexParser;
import problem.Problem;
import problem.ProblemFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game {
    static JFrame frame;
    static final World[] worlds;
    static int currentWorld = 0;

    static {
        worlds = new World[10];
        worlds[0] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 9);
        worlds[1] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 2);
        worlds[2] = World.generateWorldFromFile("src/main/java/game/testWorld.lvl");
        Tile[][] map = new Tile[9][7];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 2; j++) {
                map[i][j] = new Tile("WORLD1_GRASSTILE01", false);
            }
            for (int j = 2; j < 7; j++) {
                map[i][j] = new Tile("WORLD1_PATHTILE_FULLPATH", true);
            }
        }
        Problem problem1 = ProblemFactory.getLinearEquation(0,9,"a",(int)(Math.random()*10));
        Problem problem2 = ProblemFactory.getLinearEquation(0,9,"b",(int)(Math.random()*10));
        Problem problem3 = ProblemFactory.getLinearEquation(0,9,"c",(int)(Math.random()*10));
        Problem problem4 = ProblemFactory.getLinearEquation(0,9,"d",(int)(Math.random()*10));

        map[4][1] = new LockTile(problem1.getSolutions()[0] * 1000 + problem2.getSolutions()[0]*100+problem3.getSolutions()[0]*10+problem4.getSolutions()[0]);
        Popup p = new Popup(0,0,new ArrayList<>(),true,new ArrayList<>());
        {
            p.buttons.add(p.new Button(
                    null, null, null,
                    p.x, p.y,
                    true,
                    new Rectangle(0, 0, 1920, 1080)
            ) {
                @Override
                public void onClicked() {
                    System.exit(0);
                }
            });
            BufferedImage endScreen = new BufferedImage(1920,1080, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = endScreen.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,1920,1080);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font ("Brush Script MT", Font.PLAIN, 100));
            g2d.drawString("Thanks for playing",500,500);
            g2d.setFont(new Font("Default", Font.PLAIN, 50));
            g2d.drawString("Click anywhere to exit", 600,600);
            p.content.add(p.new Content(endScreen,true,0,0));
        }
        map[4][0] = new PopupTile("WORLD1_PATHTILE_FULLPATH", true,p);
        {
            {
                p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                p.content.add(p.new Content(LatexParser.parseProblem(problem1, 40), true, 100, 100));
                map[1][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
            }
            {
                p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                p.content.add(p.new Content(LatexParser.parseProblem(problem2, 40), true, 100, 100));
                map[3][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
            }
            {
                p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                p.content.add(p.new Content(LatexParser.parseProblem(problem3, 40), true, 100, 100));
                map[5][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
            }
            {
                p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                p.content.add(p.new Content(LatexParser.parseProblem(problem4, 40), true, 100, 100));
                map[7][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
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
        frame = new JFrame("World Test");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setActiveWorld(9);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> frame.getContentPane().repaint());
        timer.start();
    }
}
