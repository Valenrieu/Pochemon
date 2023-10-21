package pochemon.ui.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;

public abstract class StaticCharacter extends Entity {
    public void getImages(String name) {
        super.getImages(name);
    }

    public abstract void draw(Graphics2D g);
    public abstract void update();
}
