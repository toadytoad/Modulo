package game;

public class Door extends Tile {
    int worldPointer;

    public Door(String tileImage, int worldPointer) {
        super(tileImage, true);
        this.worldPointer = worldPointer;
    }

    public void interact() {
        Game.setActiveWorld(worldPointer);
    }
}
