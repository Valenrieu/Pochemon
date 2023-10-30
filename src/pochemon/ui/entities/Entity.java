package pochemon.ui.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    int x, y;
    Direction direction;

    BufferedImage standingDown, standingUp, standingLeft, standingRight;

    public void getImages(String name) {
        try {
            standingUp = ImageIO.read(new FileInputStream("../res/sprites/"+name+"_standing_up.png"));
            standingDown = ImageIO.read(new FileInputStream("../res/sprites/"+name+"_standing_down.png"));
            standingLeft = ImageIO.read(new FileInputStream("../res/sprites/"+name+"_standing_left.png"));
            standingRight = ImageIO.read(new FileInputStream("../res/sprites/"+name+"_standing_right.png"));
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw(Graphics2D g);
}
