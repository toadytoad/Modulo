package game;

import maze.Maze;
import maze.MazeGenerator;
import maze.MazeTile;
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
        worlds[0] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 8);
        worlds[1] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 2);
        worlds[2] = World.generateWorldFromFile("src/main/java/game/testWorld.lvl");
        worlds[3] = World.generateWorldFromFile("src/main/java/game/World1.lvl");
        Tile[][] map = new Tile[9][7];
        {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 2; j++) {
                    map[i][j] = new Tile("WORLD1_GRASSTILE01", false);
                }
                for (int j = 2; j < 7; j++) {
                    map[i][j] = new Tile("WORLD1_PATHTILE_FULLPATH", true);
                }
            }
            Problem problem1 = ProblemFactory.getLinearEquation(0, 9, "a", (int) (Math.random() * 10));
            Problem problem2 = ProblemFactory.getLinearEquation(0, 9, "b", (int) (Math.random() * 10));
            Problem problem3 = ProblemFactory.getLinearEquation(0, 9, "c", (int) (Math.random() * 10));
            Problem problem4 = ProblemFactory.getLinearEquation(0, 9, "d", (int) (Math.random() * 10));

            map[4][1] = new LockTile(problem1.getSolutions()[0] * 1000 + problem2.getSolutions()[0] * 100 + problem3.getSolutions()[0] * 10 + problem4.getSolutions()[0]);
            Popup p = new Popup(0, 0, new ArrayList<>(), true, new ArrayList<>());
            {
                p.buttons.add(p.new Button(
                        null, null, null,
                        p.x, p.y,
                        true,
                        new Rectangle(0, 0, World.SCREEN_SIZE.x, World.SCREEN_SIZE.y)
                ) {
                    @Override
                    public void onClicked() {
                        System.exit(0);
                    }
                });
                BufferedImage endScreen = new BufferedImage(World.SCREEN_SIZE.x, World.SCREEN_SIZE.y, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = endScreen.createGraphics();
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, World.SCREEN_SIZE.x, World.SCREEN_SIZE.y);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Brush Script MT", Font.PLAIN, 100));
                g2d.drawString("Thanks for playing", 500, 500);
                g2d.setFont(new Font("Default", Font.PLAIN, 50));
                g2d.drawString("Click anywhere to exit", 600, 600);
                p.content.add(p.new Content(endScreen, true, 0, 0));
            }
            map[4][0] = new PopupTile("WORLD1_PATHTILE_FULLPATH", true, p);

            {
                {
                    p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                    p.content.add(p.new Content(LatexParser.parseProblem(problem1, 40), true, 0, 0));
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(), p.content.get(0).content.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = bg.createGraphics();
                    g2d.setBackground(Color.WHITE);
                    g2d.clearRect(0, 0, bg.getWidth(), bg.getHeight());
                    p.content.add(0, p.new Content(bg, true, 0, 0));
                    p.x = (World.SCREEN_SIZE.x - bg.getWidth()) / 2;
                    map[1][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
                }
                {
                    p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                    p.content.add(p.new Content(LatexParser.parseProblem(problem2, 40), true, 0, 0));
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(), p.content.get(0).content.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = bg.createGraphics();
                    g2d.setBackground(Color.WHITE);
                    g2d.clearRect(0, 0, bg.getWidth(), bg.getHeight());
                    p.content.add(0, p.new Content(bg, true, 0, 0));
                    p.x = (World.SCREEN_SIZE.x - bg.getWidth()) / 2;
                    map[3][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
                }
                {
                    p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                    p.content.add(p.new Content(LatexParser.parseProblem(problem3, 40), true, 0, 0));
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(), p.content.get(0).content.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = bg.createGraphics();
                    g2d.setBackground(Color.WHITE);
                    g2d.clearRect(0, 0, bg.getWidth(), bg.getHeight());
                    p.content.add(0, p.new Content(bg, true, 0, 0));
                    p.x = (World.SCREEN_SIZE.x - bg.getWidth()) / 2;
                    map[5][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
                }
                {
                    p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                    p.content.add(p.new Content(LatexParser.parseProblem(problem4, 40), true, 0, 0));
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(), p.content.get(0).content.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = bg.createGraphics();
                    g2d.setBackground(Color.WHITE);
                    g2d.clearRect(0, 0, bg.getWidth(), bg.getHeight());
                    p.content.add(0, p.new Content(bg, true, 0, 0));
                    p.x = (World.SCREEN_SIZE.x - bg.getWidth()) / 2;
                    map[7][4] = new PopupTile("WORLD1_GRASSTILE02", false, p);
                }
            }
        }
        worlds[9] = new World(map, new ArrayList<>(), new Coordinate(4, 6));
        Maze maze = MazeGenerator.getMaze();
        map = new Tile[maze.maze[0].length][maze.maze.length + 7];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new Tile("WORLD1_GRASSTILE01", true);
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 29; j < map[0].length; j++) {
                map[i][j] = new Tile("WORLD1_GRASSTILE01", true);
            }
        }
        for (int j = 0; j < maze.maze.length; j++) {
            for (int i = 0; i < map.length; i++) {
                map[i][j+4] = maze.maze[j][i];
            }
        }
        for (int i = 0; i < map.length; i++) {
            if (i != 4) map[i][29] = new Tile("TEXTURENOTFOUND_ERRORTILE02", false);
        }
        map[4][0] = new Door("WORLD1_PATHTILE_FULLPATH", 9);
        worlds[8] = new MazeWorld(map, new Coordinate(4,31), maze);
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
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setActiveWorld(3);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> frame.getContentPane().repaint());
        timer.start();
    }
}
