package pochemon;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.battle.Battle;

public class BattlePanel extends JPanel {
    public static final int screenHeight = GamePanel.screenHeight;
    public static final int screenWidth = (int)(1.2*GamePanel.screenWidth);
    private final BufferedImage backgroundImage;
    Battle battle;

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

    public void addBattle(Battle battle) {
        this.battle = battle;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);
        battle.startAnimation(g2);

        try {
            Thread.sleep(2000);
            //g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
