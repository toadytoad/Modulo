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
        super("WORLD1_PATHTILE_FULLPATH", true);
        this.x=x;
        this.y=y;
        this.next = next;
    }
    public void burn(){
        super.setImage("TEXTURENOTFOUND_ERRORTILE");
        isSafe = false;
    }
    public void extinguish(){
        super.setImage("WORLD1_PATHTILE_FULLPATH");
        isSafe = true;
    }

    @Override
    public void paint(Graphics g, int x, int y, Coordinate screenOffset) {
        super.paint(g, x, y,screenOffset);
        g.setColor(Color.BLACK);
        g.drawString("x:" + this.x + " y:" + this.y, (x-screenOffset.x)* World.TILE_LENGTH ,(y-screenOffset.y)*World.TILE_LENGTH + World.TILE_LENGTH/2 );
        g.setColor(Color.RED);
        if (next instanceof MazeTile) {
            g.drawString("x:"+((MazeTile) next).x + " y:" +((MazeTile) next).y, (x-screenOffset.x)* World.TILE_LENGTH ,(y-screenOffset.y)*World.TILE_LENGTH + World.TILE_LENGTH/2 +10);
        }
    }
}
