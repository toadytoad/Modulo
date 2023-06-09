package game;

/**
 * A PopupTile is a Tile that will display a Popup when the player
 * approaches it.
 *
 * @author Tom Philip
 */
public class PopupTile extends Tile{
    public Popup popup;

    /**
     * Constructs a PopupTile that displays the passed Popup.
     * @param tileImage The sprite the PopupTile will have.
     * @param isWalkable Whether this PopupTile should be walkable.
     * @param popup The Popup that this PopupTile will display.
     */
    public PopupTile(String tileImage, boolean isWalkable, Popup popup){
        super(tileImage, isWalkable);
        this.popup=popup;
    }
}
