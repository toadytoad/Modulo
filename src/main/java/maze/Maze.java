package maze;

import game.Coordinate;
import game.Tile;

import java.util.LinkedList;

/**
 * Stores the maze used for the Maze level, represented by an array of MazeTiles,
 * which can be translated into a World.
 *
 * @author Tom Philip
 */
public class Maze {
    public MazeTile[][] maze;
    LinkedList<Coordinate> path;
    public static Maze getBlankMaze(){
        Maze m = new Maze();
        m.maze = new MazeTile[25][9];
        m.path = new LinkedList<>();
        for(int y= 0; y<25; y++){
            for(int x=0; x<9; x++){
                m.maze[y][x] = new MazeTile(null, x, y);
            }
        }
        return m;
    }
}
