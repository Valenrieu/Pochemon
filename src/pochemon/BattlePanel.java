package pochemon;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

public class BattlePanel extends JPanel implements Runnable {
    public static final int screenHeight = GamePanel.screenHeight;
    public static final int screenWidth = (int)(1.2*GamePanel.screenWidth);
    private static final int FPS = 30;
    private final BufferedImage backgroundImage;
    private static Thread gameThread;

    public BattlePanel() {
        BufferedImage image = null;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        try {
            image = ImageIO.read(new FileInputStream("../res/sprites/arena_background.png"));
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }

        backgroundImage = image;
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);
    }
}
