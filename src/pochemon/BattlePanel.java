package pochemon;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.battle.Battle;

public class BattlePanel extends JPanel implements Runnable {
    public static final int screenHeight = (int)(0.6*GamePanel.screenHeight);
    public static final int screenWidth = (int)(0.6*GamePanel.screenWidth);
    public final BufferedImage backgroundImage;
    private Thread gameThread;
    Battle battle;

    public BattlePanel() {
        BufferedImage image = null;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setIgnoreRepaint(true);

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

    public void addBattle(Battle battle) {
        this.battle = battle;
    }

    public void run() {
        while(gameThread!=null) {
            this.repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        battle.draw(g2);
    }
}
