package game;

public class PopupTile extends Tile{
    Popup popup;
    public PopupTile(String tileImage, boolean isWalkable, Popup popup){
        super(tileImage, isWalkable);
        this.popup=popup;
    }
}
