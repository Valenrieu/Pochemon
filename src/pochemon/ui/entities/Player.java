package pochemon.ui.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.GamePanel;
import pochemon.KeyHandler;

public class Player extends MovableCharacter {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(1); // vitesse

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        x = 29;
        y = 30;
        direction = Direction.DOWN;
        previousDirection = Direction.DOWN;
        spriteNumber = 1;
        this.getImages();
    }

    public void getImages() {
        super.getImages("player");
    }

    public void draw(Graphics2D g) {
        BufferedImage image = null;
        int cameraX, cameraY;

        switch(direction) {
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
                switch(previousDirection) {
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

        cameraX = (int)(gamePanel.screenWidth/2 - gamePanel.tileSize/2);
        cameraY = (int)(gamePanel.screenHeight/2 - gamePanel.tileSize/2);

        g.drawImage(image, cameraX, cameraY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed ||
            keyHandler.leftPressed || keyHandler.rightPressed) {

            if(keyHandler.upPressed) {
                if(gamePanel.map.map[y-speed][x].walkable) {
                    y -= speed;
                }

                direction = Direction.UP;
                previousDirection = Direction.UP;
            } else if(keyHandler.downPressed) {
                if(gamePanel.map.map[y+speed][x].walkable) {
                    y += speed;
                }

                direction = Direction.DOWN;
                previousDirection = Direction.DOWN;
            } else if(keyHandler.leftPressed) {
                if(gamePanel.map.map[y][x-speed].walkable) {
                    x -= speed;
                }

                direction = Direction.LEFT;
                previousDirection = Direction.LEFT;
            } else if(keyHandler.rightPressed) {
                if(gamePanel.map.map[y][x+speed].walkable) {
                    x += speed;
                }

                direction = Direction.RIGHT;
                previousDirection = Direction.RIGHT;
            }

            spriteCounter++;

            if(spriteCounter>10) {
                if(spriteNumber==1) {
                    spriteNumber = 2;
                } else if(spriteNumber==2) {
                    spriteNumber = 1;
                }

                spriteCounter = 0;
            }
        } else {
            direction = Direction.NULL;

			if(spriteNumber==1) {
				spriteNumber = 2;
			} else if(spriteNumber==2) {
				spriteNumber = 1;
			}
        }
    }
}
