package pochemon.ui.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.ui.map.Map;

public abstract class StaticCharacter extends Entity {
    protected Map map;
    
    public StaticCharacter(Map map) {
        this.map = map;
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        int cameraX, cameraY;

        switch(direction) {
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

        cameraX = (int)(map.gamePanel.screenWidth/2 - map.gamePanel.tileSize/2 + (x-map.gamePanel.player.getX())*map.gamePanel.tileSize);
        cameraY = (int)(map.gamePanel.screenHeight/2 - map.gamePanel.tileSize/2 + (y-map.gamePanel.player.getY())*map.gamePanel.tileSize);
        g.drawImage(image, cameraX, cameraY, map.gamePanel.tileSize, map.gamePanel.tileSize, null);
    }
}
