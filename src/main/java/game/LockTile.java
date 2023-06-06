package game;

import java.awt.*;
import java.util.ArrayList;

public class LockTile extends PopupTile {
    public LockTile(int solution) {
        super("TEXTURENOTFOUND_ERRORTILE02", false, new CombinationLock(solution));
    }

    public void paint (Graphics g, int x, int y, Coordinate screenOffset) {
        super.paint(g,x,y, screenOffset);
        if (CombinationLock.isSolved) {
            setWalkable(true);
            World.removePopup(this.popup);
            this.popup = new Popup(0,0,new ArrayList<>(),false,new ArrayList<>());
        }
    }
}
