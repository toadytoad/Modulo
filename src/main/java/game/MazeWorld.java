package game;

import maze.MazeTile;
import problem.LatexParser;
import problem.Problem;
import problem.ProblemFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

public class MazeWorld extends World {
    private int counter;
    private Timer timer = new Timer(100, e -> counter += 1);
    private boolean mazeActive;
    public final static int TIME_PER_QUESTION = 200;
    private MazeTile current;
    public MazeWorld(Tile[][] map, java.util.List<Decoration> decorationList, Coordinate playerCoordinates) {
        super(map, decorationList, playerCoordinates);
    }

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

        if (counter == TIME_PER_QUESTION) {
            burnAroundPlayer();
        }

        if (mazeActive && current.next instanceof MazeTile && player.coordinate.y < ((MazeTile)current.next).y + 3) {
            burnAroundPlayer();
        }

        if (counter == TIME_PER_QUESTION + 10) {
            extinguishMaze();
            counter = 0;
            current = (MazeTile) current.next;
            if (current != null) {
                generateNextPopup(current);
            }
        }

        super.paint(g);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(counter), 100, 100);

        if (mazeActive && map[player.coordinate.x][player.coordinate.y] instanceof MazeTile && !((MazeTile)map[player.coordinate.x][player.coordinate.y]).isSafe) {
            resetMaze();
            extinguishMaze();
            player.coordinate = new Coordinate(4, 31);
        }
    }

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

    private void extinguishMaze () {
        for (int i = 0; i < 9; i++) {
            for (int j = 4; j < 29; j++) {
                ((MazeTile)map[i][j]).extinguish();
            }
        }
    }

    private void generateNextPopup(MazeTile mazeTile) {
        if (mazeTile.next instanceof MazeTile) {
            Problem p = ProblemFactory.getSystem(0,2,((MazeTile) mazeTile.next).x, ((MazeTile) mazeTile.next).y);
            Popup popup = new Popup(100, 500,
                    new ArrayList<>(),
                    true,new ArrayList<>());
            popup.content.add(popup.new Content(LatexParser.parseProblem(p, 40), true, 0,0));
            BufferedImage bg = new BufferedImage(popup.content.get(0).content.getWidth(), popup.content.get(0).content.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bg.createGraphics();
            g2d.setBackground(Color.WHITE);
            g2d.clearRect(0, 0, bg.getWidth(), bg.getHeight());
            popup.content.add(0, popup.new Content(bg, true, 0, 0));
            addPopup(popup);
        }
    }

    private void resetMaze() {
        timer.stop();
        counter = 0;
        for (int i = popupLayer.size() - 1; i >= 0; i--) {
            popupLayer.remove(popupLayer.get(i));
        }
        mazeActive = false;
    }
}
