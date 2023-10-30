package pochemon.ui.map;

import java.awt.image.BufferedImage;

public class Tile {
    public boolean walkable;
    public final BufferedImage sprite;

    public Tile(boolean walkable, BufferedImage sprite) {
        this.walkable = walkable;
        this.sprite = sprite;
    }
}
