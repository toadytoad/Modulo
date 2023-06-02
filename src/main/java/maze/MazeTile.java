package maze;

import game.Tile;

public class MazeTile extends Tile {
    Tile next;
    int x, y;
    public MazeTile(Tile next, int x, int y){
        super("WORLD2_MAZE_UNLIT", true);
        this.x=x;
        this.y=y;
        this.next = next;
    }
    public void burn(){
        super.setImage("WORLD2_MAZE_LIT");
    }
    public void extinguish(){
        super.setImage("WORLD2_MAZE_UNLIT");
    }
}
