package pochemon.ui.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

public class Follower extends MovableCharacter {
    private Player player;

    public Follower(Player player) {
        super(1);

        this.player = player;
        x = player.x;
        y = player.y - 1;
        spriteNumber = player.spriteNumber;
        this.getImages();
    }

    public void getImages() {
        super.getImages("pochechu");
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        int cameraX, cameraY;

        switch(player.direction) {
            case UP:
                if(spriteNumber==1) {
                    image = up;
                } else {
                    image = up1;
                }
                break;

            case DOWN:
                if(spriteNumber==1) {
                    image = down;
                } else {
                    image = down1;
                }
                break;

            case LEFT:
                if(spriteNumber==1) {
                    image = standingLeft;
                } else {
                    image = left;
                }
                break;

            case RIGHT:
                if(spriteNumber==1) {
                    image = standingRight;
                } else {
                    image = right;
                }
                break;

            case NULL:
                switch(player.previousDirection) {
                    case UP:
                        image = standingUp;
                        break;
                    case DOWN:
                        image = standingDown;
                        break;
                    case LEFT:
                        image = standingLeft;
                        break;
                    case RIGHT:
                        image = standingRight;
                        break;
                }
        }

        cameraX = (int)(player.gamePanel.screenWidth/2 - player.gamePanel.tileSize/2 + (x-player.x)*player.gamePanel.tileSize);
        cameraY = (int)(player.gamePanel.screenHeight/2 - player.gamePanel.tileSize/2 + (y-player.y)*player.gamePanel.tileSize);
        g.drawImage(image, cameraX, cameraY, player.gamePanel.tileSize, player.gamePanel.tileSize, null);
    }

    public void update() {
        x = player.previousX;
        y = player.previousY;
        spriteNumber = player.spriteNumber;
    }
}
