package game;

import maze.Maze;
import maze.MazeGenerator;
import problem.LatexParser;
import problem.Problem;
import problem.ProblemFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    static JFrame frame;
    static final World[] worlds;
    static int currentWorld = 0;

    static {
        worlds = new World[10];
        Tile[][] map = new Tile[16][10];
        java.util.List<Decoration> decorationLayer = new ArrayList<>();
        {
            {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        map[i][j] = new Tile("EMPTY", false);
                    }
                }
                for (int i = 1; i < 4; i++) {
                    for (int j = 5; j < 9; j++) {
                        map[i][j].setWalkable(true);
                    }
                }
                for (int i = 3; i < 10; i++) {
                    for (int j = 3; j < 8; j++) {
                        map[i][j].setWalkable(true);
                    }
                }
                for (int i = 11; i < 15; i++) {
                    for (int j = 4; j < 8; j++) {
                        map[i][j].setWalkable(true);
                    }
                }
                map[10][6].setWalkable(true);
                map[14][5].setWalkable(false);
                map[14][4].setWalkable(false);
                map[14][7].setWalkable(false);
                map[13][4].setWalkable(false);
                map[9][7].setWalkable(false);
                map[7][5].setWalkable(false);
                map[6][5].setWalkable(false);
                map[9][3].setWalkable(false);
                map[6][3].setWalkable(false);
                map[5][3].setWalkable(false);
                map[4][3].setWalkable(false);
                map[3][3].setWalkable(false);
                map[2][5].setWalkable(false);
                map[1][5].setWalkable(false);
                map[1][8].setWalkable(false);
                map[2][9] = new Door("EMPTY", 1);
                decorationLayer.add(new Decoration("STARTER_HOUSE_INTERIOR", new Coordinate(0, 0)));
            }
            worlds[0] = new World(map, decorationLayer, new Coordinate(13, 5));
            {
                map = generateSchoolMap();
                decorationLayer = new ArrayList<>();
                decorationLayer.add(new Decoration("SCHOOL_INTERIOR", new Coordinate(0, 0)));
                decorationLayer.add(new Decoration("SCHOOL_TEACHER_1", new Coordinate(5, 2)));
                Popup popup = null;
                try {
                    BufferedImage bf = ImageIO.read(new File("src/main/java/assets/uielements/AdditionLesson.png"));
                    popup = new Popup(
                            (World.SCREEN_SIZE.x - bf.getWidth()) / 2,
                            (World.SCREEN_SIZE.y - bf.getHeight()) / 2,
                            new ArrayList<>(),
                            true,
                            new ArrayList<>());
                    popup.content.add(popup.new Content(bf, true, 0, 0));
                } catch (IOException e) {
                    System.out.println("Could not read file src/main/java/assets/uielements/AdditionLesson.png");
                }
                map[5][3] = new PopupTile("EMPTY", false, popup);
                decorationLayer.add(new Decoration("SCHOOL_TEACHER_1", new Coordinate(9, 3)));
                try {
                    BufferedImage bf = ImageIO.read(new File("src/main/java/assets/uielements/SubtractionLesson.png"));
                    popup = new Popup(
                            (World.SCREEN_SIZE.x - bf.getWidth()) / 2,
                            (World.SCREEN_SIZE.y - bf.getHeight()) / 2,
                            new ArrayList<>(),
                            true,
                            new ArrayList<>());
                    popup.content.add(popup.new Content(bf, true, 0, 0));
                } catch (IOException e) {
                    System.out.println("Could not read file src/main/java/assets/uielements/SubtractionLesson.png");
                }
                map[9][4] = new PopupTile("EMPTY", false, popup);
            }
            worlds[3] = new World(map, decorationLayer, new Coordinate(2, 7));

            {
                map = generateSchoolMap();
                decorationLayer = new ArrayList<>();
                decorationLayer.add(new Decoration("SCHOOL_INTERIOR", new Coordinate(0, 0)));
                decorationLayer.add(new Decoration("SCHOOL_TEACHER_1", new Coordinate(9, 4)));
                Popup popup = null;
                try {
                    BufferedImage bf = ImageIO.read(new File("src/main/java/assets/uielements/MultiplicationLesson.png"));
                    popup = new Popup(
                            (World.SCREEN_SIZE.x - bf.getWidth()) / 2,
                            (World.SCREEN_SIZE.y - bf.getHeight()) / 2,
                            new ArrayList<>(),
                            true,
                            new ArrayList<>());
                    popup.content.add(popup.new Content(bf, true, 0, 0));
                } catch (IOException e) {
                    System.out.println("Could not read file src/main/java/assets/uielements/MultiplicationLesson.png");
                }
                map[9][5] = new PopupTile("EMPTY", false, popup);
                map[9][4] = map[9][5];
            }
            worlds[4] = new World(map, decorationLayer, new Coordinate(2, 7));

            {
                map = generateSchoolMap();
                decorationLayer = new ArrayList<>();
                decorationLayer.add(new Decoration("SCHOOL_INTERIOR", new Coordinate(0, 0)));
                decorationLayer.add(new Decoration("SCHOOL_TEACHER_1", new Coordinate(8, 6)));
                Popup popup = null;
                try {
                    BufferedImage bf = ImageIO.read(new File("src/main/java/assets/uielements/DivisionLesson.png"));
                    popup = new Popup(
                            (World.SCREEN_SIZE.x - bf.getWidth()) / 2,
                            (World.SCREEN_SIZE.y - bf.getHeight()) / 2,
                            new ArrayList<>(),
                            true,
                            new ArrayList<>());
                    popup.content.add(popup.new Content(bf, true, 0, 0));
                } catch (IOException e) {
                    System.out.println("Could not read file src/main/java/assets/uielements/DivisionLesson.png");
                }
                map[8][6] = new PopupTile("EMPTY", false, popup);
                map[8][7] = map[8][6];
            }
            worlds[5] = new World(map, decorationLayer, new Coordinate(2, 7));

            {
                map = generateSchoolMap();
                decorationLayer = new ArrayList<>();
                decorationLayer.add(new Decoration("SCHOOL_INTERIOR", new Coordinate(0, 0)));
                decorationLayer.add(new Decoration("SCHOOL_TEACHER_1", new Coordinate(2, 3)));
                Popup popup = null;
                try {
                    BufferedImage bf = ImageIO.read(new File("src/main/java/assets/uielements/VariablesLesson.png"));
                    popup = new Popup(
                            (World.SCREEN_SIZE.x - bf.getWidth()) / 2,
                            (World.SCREEN_SIZE.y - bf.getHeight()) / 2,
                            new ArrayList<>(),
                            true,
                            new ArrayList<>());
                    popup.content.add(popup.new Content(bf, true, 0, 0));
                } catch (IOException e) {
                    System.out.println("Could not read file src/main/java/assets/uielements/VariablesLesson.png");
                }
                map[2][4] = new PopupTile("EMPTY", false, popup);
                map[2][8] = new Door("EMPTY", 2);
            }
            worlds[6] = new World(map, decorationLayer, new Coordinate(2, 7));

            {
                map = generateSchoolMap();
                decorationLayer = new ArrayList<>();
                decorationLayer.add(new Decoration("SCHOOL_INTERIOR", new Coordinate(0, 0)));
                decorationLayer.add(new Decoration("SCHOOL_TEACHER_1", new Coordinate(8, 5)));
                Popup popup = null;
                try {
                    BufferedImage bf = ImageIO.read(new File("src/main/java/assets/uielements/SystemsLesson.png"));
                    popup = new Popup(
                            (World.SCREEN_SIZE.x - bf.getWidth()) / 2,
                            (World.SCREEN_SIZE.y - bf.getHeight()) / 2,
                            new ArrayList<>(),
                            true,
                            new ArrayList<>());
                    popup.content.add(popup.new Content(bf, true, 0, 0));
                } catch (IOException e) {
                    System.out.println("Could not read file src/main/java/assets/uielements/SystemsLesson.png");
                }
                map[8][5] = new PopupTile("EMPTY", false, popup);
                map[8][6] = map[8][5];
                map[2][8] = new Door("EMPTY", 2);
            }
            worlds[7] = new World(map, decorationLayer, new Coordinate(2, 7));
        } // School worlds
        worlds[1] = World.generateRandomWorldWithDoors(new Coordinate(10, 10), 2);
        worlds[2] = World.generateWorldFromFile("src/main/java/game/testWorld.lvl");

        map = new Tile[9][7];
        {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 2; j++) {
                    map[i][j] = new Tile("BLACK", false);
                }
                for (int j = 2; j < 7; j++) {
                    map[i][j] = new Tile("WORLD2_MAZETILE", true);
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
                CombinationLock.drawCenteredString(g2d, "Thanks for playing", new Rectangle(0,World.SCREEN_SIZE.y/4, World.SCREEN_SIZE.x, World.SCREEN_SIZE.y/4), new Font("Brush Script MT", Font.PLAIN, 150));
                CombinationLock.drawCenteredString(g2d, "Click anywhere to exit", new Rectangle(0,World.SCREEN_SIZE.y/2, World.SCREEN_SIZE.x, World.SCREEN_SIZE.y/2), new Font("Default", Font.PLAIN, 50));
                p.content.add(p.new Content(endScreen, true, 0, 0));
            }
            map[4][0] = new PopupTile("WORLD2_MAZETILE", true, p);

            {
                {
                    p = new Popup(100, 100, new ArrayList<>(), true, new ArrayList<>());
                    p.content.add(p.new Content(LatexParser.parseProblem(problem1, 40), true, 0, 0));
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(null), p.content.get(0).content.getHeight(null), BufferedImage.TYPE_INT_ARGB);
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
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(null), p.content.get(0).content.getHeight(null), BufferedImage.TYPE_INT_ARGB);
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
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(null), p.content.get(0).content.getHeight(null), BufferedImage.TYPE_INT_ARGB);
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
                    BufferedImage bg = new BufferedImage(p.content.get(0).content.getWidth(null), p.content.get(0).content.getHeight(null), BufferedImage.TYPE_INT_ARGB);
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
        {
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
                    map[i][j + 4] = maze.maze[j][i];
                }
            }
            for (int i = 0; i < map.length; i++) {
                if (i != 4) map[i][29] = new Tile("BLACK", false);
                if (i != 4) map[i][30] = new Tile("BLACK", false);
            }
            map[4][0] = new Door("WORLD1_PATHTILE_FULLPATH", 9);
            decorationLayer = new ArrayList<>();
            decorationLayer.add(new Decoration("X_AXIS_BAR", new Coordinate(0, 29)));
            decorationLayer.add(new Decoration("Y_AXIS_BAR_20_24", new Coordinate(9, 24)));
            decorationLayer.add(new Decoration("Y_AXIS_BAR_15_19", new Coordinate(9, 19)));
            decorationLayer.add(new Decoration("Y_AXIS_BAR_10_14", new Coordinate(9, 14)));
            decorationLayer.add(new Decoration("Y_AXIS_BAR_5_9", new Coordinate(9, 9)));
            decorationLayer.add(new Decoration("Y_AXIS_BAR_0_4", new Coordinate(9, 4)));
        }
        worlds[8] = new MazeWorld(map, decorationLayer, new Coordinate(4,31));
    }



    public static void setActiveWorld (int worldIndex) {
        frame.remove(worlds[currentWorld]);
        frame.removeKeyListener(worlds[currentWorld]);
        currentWorld = worldIndex;
        frame.addKeyListener(worlds[currentWorld]);
        frame.setTitle("World " + currentWorld);
        frame.add(worlds[currentWorld]);
        for (int i = World.popupLayer.size() - 1; i >= 0; i--) {
            World.removePopup(World.popupLayer.get(i));
        }
        frame.revalidate();
    }

    private static Tile[][] generateSchoolMap () {
        Tile[][] map = new Tile[11][9];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile("EMPTY", false);
            }
        }
        for (int i = 1; i < 10; i++) {
            for (int j = 4; j < 8; j++) {
                map[i][j].setWalkable(true);
            }
        }
        map[1][4].setWalkable(false);
        map[3][5].setWalkable(false);
        map[3][6].setWalkable(false);
        map[5][5].setWalkable(false);
        map[5][6].setWalkable(false);
        map[5][3].setWalkable(true);
        map[7][5].setWalkable(false);
        map[7][6].setWalkable(false);
        map[9][7].setWalkable(false);
        map[2][8] = new Door("EMPTY", 1);
        return map;
    }

    public static void main (String[] args) {
        frame = new JFrame("World Test");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setActiveWorld(7);
        frame.setVisible(true);
        Timer timer = new Timer(100, e -> frame.getContentPane().repaint());
        timer.start();
    }
}
