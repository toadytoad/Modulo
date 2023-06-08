package maze;

import game.Coordinate;
import game.Tile;
import game.World;

import java.awt.*;

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
    public void burn(){
        super.setImage("WORLD2_MAZETILE_LIT");
        isSafe = false;
    }
    public void extinguish(){
        super.setImage("WORLD2_MAZETILE");
        isSafe = true;
    }
}
