package pochemon.openworld.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.GamePanel;
import pochemon.KeyHandler;
import pochemon.openworld.entities.Talkable;
import pochemon.battle.objects.entities.Pochemon;

public class Player extends MovableCharacter {
    public final Pochemon pochemon;
    GamePanel gamePanel;
    KeyHandler keyHandler;
    private Follower follower;
    int previousX, previousY;

    public Player(Pochemon pochemon, GamePanel gamePanel, KeyHandler keyHandler) {
        super(1); // vitesse

        this.pochemon = pochemon;
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        x = 27;
        y = 31;
        previousX = x;
        previousY = y-1;
        direction = Direction.DOWN;
        previousDirection = Direction.DOWN;
        spriteNumber = 1;
        follower = new Follower(this);
        this.getImages("player");
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

        follower.draw(g);
        g.drawImage(image, cameraX, cameraY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed ||
            keyHandler.leftPressed || keyHandler.rightPressed) {

            if(keyHandler.upPressed) {
                if(gamePanel.map.map[y-speed][x].walkable) {
                    previousY = y;
                    previousX = x;
                    y -= speed;
                }

                direction = Direction.UP;
                previousDirection = Direction.UP;
            } else if(keyHandler.downPressed) {
                if(gamePanel.map.map[y+speed][x].walkable) {
                    previousY = y;
                    previousX = x;
                    y += speed;
                }

                direction = Direction.DOWN;
                previousDirection = Direction.DOWN;
            } else if(keyHandler.leftPressed) {
                if(gamePanel.map.map[y][x-speed].walkable) {
                    previousY = y;
                    previousX = x;
                    x -= speed;
                }

                direction = Direction.LEFT;
                previousDirection = Direction.LEFT;
            } else if(keyHandler.rightPressed) {
                if(gamePanel.map.map[y][x+speed].walkable) {
                    previousY = y;
                    previousX = x;
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

        follower.update();

        if(keyHandler.spacePressed) {
            for(int i=0; i<gamePanel.map.trainers.length; i++) {
                if(gamePanel.map.trainers[i].getX()+1 == x && gamePanel.map.trainers[i].getY() == y ||
                   gamePanel.map.trainers[i].getX()-1 == x && gamePanel.map.trainers[i].getY() == y ||
                   gamePanel.map.trainers[i].getY()+1 == y && gamePanel.map.trainers[i].getX() == x ||
                   gamePanel.map.trainers[i].getY()-1 == y && gamePanel.map.trainers[i].getX() == x) {

                    if(gamePanel.map.trainers[i] instanceof Talkable) {
                        gamePanel.map.trainers[i].talk(this);
                    }
                }
            }
        }
    }
}
