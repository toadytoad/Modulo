package game;

import java.awt.*;
import java.util.ArrayList;

/**
 * LockTile is a PopupTile whose Popup is a CombinationLock. When the CombinationLock
 * is solved, this LockTile will become walkable, and will remove its Popup
 *
 * @author Luke Mathieu
 */
public class LockTile extends PopupTile {

    /**
     * Creates a LockTile with the specified solution.
     * @param solution The solution to the CombinationLock that this LockTile will contain.
     */
    public LockTile(int solution) {
        super("WORLD3_DOORTILE", false, new CombinationLock(solution));
    }

    /**
     * Overrides the paint method to additionally check whether the CombinationLock has
     * been solved.
     * @param g The Graphics object to be painted on.
     * @param x The x-coordinate to paint this LockTile at.
     * @param y The y-coordinate to paint this LockTile at.
     * @param screenOffset The current screenOffset affecting this World.
     */
    public void paint (Graphics g, int x, int y, Coordinate screenOffset) {
        super.paint(g,x,y, screenOffset);
        if (CombinationLock.isSolved) {
            setWalkable(true);
            World.removePopup(this.popup);
            setImage("WORLD2_MAZETILE");
            this.popup = new Popup(0,0,new ArrayList<>(),false,new ArrayList<>());
        }
    }
}
