package game;

/**
 * Extension of the Tile class that sends the player to a different World
 * when the player walks into this Door.
 *
 * @author Luke Mathieu
 */
public class Door extends Tile {
    int worldPointer;

    /**
     * Creates a Door object with the specified tileImage, and the
     * World destination given by the worldPointer.
     * @param tileImage The sprite of this Door.
     * @param worldPointer The index of the World that this Door will lead to in the worlds array in Game.
     */
    public Door(String tileImage, int worldPointer) {
        super(tileImage, true);
        this.worldPointer = worldPointer;
    }

    /**
     * Called whenever the player moves into a Door. Sets the current world
     * to this Door's destination.
     */
    public void interact() {
        Game.setActiveWorld(worldPointer);
    }
}
