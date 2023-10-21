package pochemon.ui.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

public abstract class MovableCharacter extends Entity {
    public final int speed;
    int spriteNumber, spriteCounter;
    Direction previousDirection;

    BufferedImage up, up1;
    BufferedImage down, down1;
    BufferedImage left;
    BufferedImage right;

    public MovableCharacter(int speed) {
        if(speed<=0) {
            throw new IllegalArgumentException("Speed must be a positive integer.");
        }

        this.speed = speed;
    }

    public void getImages(String name) {
        super.getImages(name);

        try {
            up = ImageIO.read(new FileInputStream("res/sprites/"+name+"_up.png"));
            up1 = ImageIO.read(new FileInputStream("res/sprites/"+name+"_up1.png"));
            down = ImageIO.read(new FileInputStream("res/sprites/"+name+"_down.png"));
            down1 = ImageIO.read(new FileInputStream("res/sprites/"+name+"_down1.png"));

            left = ImageIO.read(new FileInputStream("res/sprites/"+name+"_left.png"));
            right = ImageIO.read(new FileInputStream("res/sprites/"+name+"_right.png"));
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }
    }

    public abstract void draw(Graphics2D g);
    public abstract void update();
}
