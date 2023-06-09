package game;

import java.awt.*;

/**
 * Class to represent the player, storing the current location and movement directions.
 *
 * @author Luke Mathieu
 */
public class Player {
    private final Image sprite = Toolkit.getDefaultToolkit().getImage("assets/wizardIdle.gif");
    public Coordinate coordinate;
    int xMovement, yMovement;

    /**
     * Creates a player at the specified coordinates.
     * @param x The x-coordinate the player should begin at.
     * @param y The y-coordinate the player should begin at.
     */
    public Player (int x, int y) {
        coordinate = new Coordinate(x, y);
        xMovement = 0;
        yMovement = 0;
    }

    /**
     * Updates the player's coordinates based on the direction of the player's movement.
     */
    public void move() {
        coordinate = new Coordinate(coordinate.x + xMovement, coordinate.y + yMovement);
    }

    /**
     * Used to determine the coordinate that the player currently intends to move to.
     * @return The coordinate the player is trying to move to.
     */
    public Coordinate movingTo() {
        return new Coordinate(coordinate.x + xMovement, coordinate.y + yMovement);
    }

    public void setMovement(int x, int y) {
        xMovement = x;
        yMovement = y;
    }

    public Coordinate getMovement() {
        return new Coordinate(xMovement, yMovement);
    }

    public void paint (Graphics g, Coordinate screenOffset) {
        g.drawImage(sprite, (coordinate.x- screenOffset.x)*World.TILE_LENGTH, (coordinate.y-screenOffset.y)*World.TILE_LENGTH-World.TILE_LENGTH/2, World.TILE_LENGTH, World.TILE_LENGTH *3/2, null);
    }
}
