package pochemon;

import java.awt.*;
import javax.swing.*;

import pochemon.openworld.entities.Player;
import pochemon.openworld.map.Map;

public class GamePanel extends JPanel implements Runnable {
    public static final int originalTileSize = 16;
    public static final int scale = 3;

    public static final int tileSize = originalTileSize*scale;
    public static final int maxScreenCol = 17; // Choisir des nombres impairs
    public static final int maxScreenRow = 13;
    public static final int screenWidth = tileSize*maxScreenCol;
    public static final int screenHeight = tileSize*maxScreenRow;

    public static final int FPS = 30;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public Map map = new Map(this);
    public Player player = new Player(map.pochemonList.list[0], this, keyHandler);

    private BackgroundMusic music = new BackgroundMusic("schubert-liszt-serenade");

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        music.loop();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread!=null) {
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if(delta>=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer>=1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        map.draw(g2);
        player.draw(g2);
    }
}
