package game;

import maze.MazeTile;
import problem.LatexParser;
import problem.Problem;
import problem.ProblemFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Extension of the World class to provide additional functionality required
 * for the Maze level of the game.
 *
 * @author Luke Mathieu
 */
public class MazeWorld extends World {
    private int counter;
    private final Timer timer = new Timer(100, e -> counter += 1);
    private boolean mazeActive;
    public final static int TIME_PER_QUESTION = 250;
    private MazeTile current;

    /**
     * Class constructor. Inherited from World.
     * @param map The map of Tiles that make up this World.
     * @param decorationList The list of decorations in this World.
     * @param playerCoordinates The player's starting coordinates.
     */
    public MazeWorld(Tile[][] map, java.util.List<Decoration> decorationList, Coordinate playerCoordinates) {
        super(map, decorationList, playerCoordinates);
    }

    /**
     * Method to draw one frame of the MazeWorld. Functions similar to
     * World.paint() as a method to tick the game forward once, except that
     * additional functionality so that this World can act as the maze level.
     * @param g  the <code>Graphics</code> context in which to paint.
     */
    @Override
    public void paint (Graphics g) {
        if (player.coordinate.y < 29 && player.coordinate.y > 3) {
            if (!timer.isRunning()) timer.start();
        } else {
            resetMaze();
        }

        if (!mazeActive && player.coordinate.y < 29 && player.coordinate.y > 3) {
            mazeActive = true;
            current = (MazeTile)map[4][28];
            generateNextPopup(current);
        }

        if (counter >= TIME_PER_QUESTION && counter < TIME_PER_QUESTION + 10) {
            burnAroundPlayer();
            counter = TIME_PER_QUESTION + 10;
        }

        if (mazeActive && current.next instanceof MazeTile && player.coordinate.y < ((MazeTile)current.next).y + 3) {
            burnAroundPlayer();
        }

        if (mazeActive && current.next instanceof MazeTile && player.coordinate.x == ((MazeTile)current.next).x && player.coordinate.y == ((MazeTile)current.next).y + 4) {
            counter+=7;
        }

        if (counter >= TIME_PER_QUESTION + 30) {
            extinguishMaze();
            counter = 0;
            current = (MazeTile) current.next;
            if (current != null) {
                if (!popupLayer.isEmpty()) removePopup(popupLayer.get(0));
                generateNextPopup(current);
            }
        }

        super.paint(g);
        for (Popup popup : popupLayer) {
            popup.isVisible = true;
        }
//        g.setColor(Color.WHITE);
//        g.drawString(Integer.toString(counter), 100, 100);

        if (mazeActive && map[player.coordinate.x][player.coordinate.y] instanceof MazeTile && !((MazeTile)map[player.coordinate.x][player.coordinate.y]).isSafe) {
            resetMaze();
            extinguishMaze();
            player.coordinate = new Coordinate(4, 31);
        }
    }

    /**
     * Burns the tiles located directly around the player, making them unsafe for the player
     * to walk on.
     */
    private void burnAroundPlayer () {
        for (int i = player.coordinate.x - 1; i <= player.coordinate.x + 1; i++) {
            for (int j = player.coordinate.y - 1; j <= player.coordinate.y + 1; j++) {
                if (i < 0 || i > 8 || j < 4 || j > 28) {
                    continue;
                }
                if (i == ((MazeTile)current.next).x && j == ((MazeTile)current.next).y + 4) continue;
                ((MazeTile)map[i][j]).burn();
            }
        }
    }

    /**
     * Extinguishes all Tiles in the maze, making them safe to walk on again.
     */
    private void extinguishMaze () {
        for (int i = 0; i < 9; i++) {
            for (int j = 4; j < 29; j++) {
                ((MazeTile)map[i][j]).extinguish();
            }
        }
    }

    /**
     * Generates the next Popup that displays the Problem whose solution
     * leads to the next safe Tile in the maze.
     * @param mazeTile The current safe MazeTile.
     */
    private void generateNextPopup(MazeTile mazeTile) {
        if (mazeTile.next instanceof MazeTile) {
            Popup popup = new Popup(100, 500,
                    new ArrayList<>(),
                    true,new ArrayList<>());
            BufferedImage bg;
            if (((MazeTile)current.next).y > 11) {
                Problem p = ProblemFactory.getLinearEquation(1, 5, "x", ((MazeTile) mazeTile.next).x);
                popup.content.add(popup.new Content(LatexParser.parseProblem(p, 40), true, 0,0));
                p = ProblemFactory.getLinearEquation(1, 5, "y", ((MazeTile) mazeTile.next).y);
                popup.content.add(popup.new Content(LatexParser.parseProblem(p, 40), true, 0,popup.content.get(0).content.getHeight(null)));

                bg = new BufferedImage(Math.max(popup.content.get(0).content.getWidth(null),popup.content.get(1).content.getWidth(null)), popup.content.get(0).content.getHeight(null)+popup.content.get(1).content.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bg.createGraphics();
                g2d.setBackground(Color.WHITE);
                g2d.clearRect(0, 0, bg.getWidth(), bg.getHeight());
            }
            else {
                Problem p = ProblemFactory.getSystem(1, 3, ((MazeTile) mazeTile.next).x, ((MazeTile) mazeTile.next).y);
                popup.content.add(popup.new Content(LatexParser.parseProblem(p, 40), true, 0,0));
                bg = new BufferedImage(popup.content.get(0).content.getWidth(null), popup.content.get(0).content.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bg.createGraphics();
                g2d.setBackground(Color.WHITE);
                g2d.clearRect(0, 0, bg.getWidth(), bg.getHeight());
            }

            popup.content.add(0, popup.new Content(bg, true, 0, 0));
            popup.y = (SCREEN_SIZE.y - popup.content.get(0).content.getHeight(null)) / 2;
            System.out.println("Next Tile of the Maze: " + ((MazeTile)current.next).x + ", " + ((MazeTile)current.next).y);
            addPopup(popup);
        }
    }

    /**
     * Resets the maze.
     */
    private void resetMaze() {
        timer.stop();
        counter = 0;
        for (int i = popupLayer.size() - 1; i >= 0; i--) {
            popupLayer.remove(popupLayer.get(i));
        }
        mazeActive = false;
    }
}
