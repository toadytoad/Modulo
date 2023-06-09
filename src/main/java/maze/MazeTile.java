package maze;

import game.Tile;

/**
 * MazeTiles is a single tile of a Maze, which may be a part of the path
 * that is the solution to the maze. If a MazeTile is a part of the path,
 * it will store the next Tile in the path. MazeTiles may also be safe or
 * unsafe for the player to walk on.
 *
 * @author Tom Philip
 */
public class MazeTile extends Tile {
    public Tile next;
    public int x, y;
    public boolean isSafe = true;
    public MazeTile(Tile next, int x, int y){
        super("WORLD2_MAZETILE", true);
        this.x=x;
        this.y=y;
        this.next = next;
    }

    /**
     * Makes this MazeTile unsafe to walk on.
     */
    public void burn(){
        super.setImage("WORLD2_MAZETILE_LIT");
        isSafe = false;
    }

    /**
     * Makes this MazeTile safe to walk on.
     */
    public void extinguish(){
        super.setImage("WORLD2_MAZETILE");
        isSafe = true;
    }
}
