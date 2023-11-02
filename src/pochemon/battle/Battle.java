package pochemon.battle;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.openworld.entities.Player;
import pochemon.openworld.entities.Trainer;
import pochemon.BattlePanel;

public class Battle {
    private JDialog window;
    private BattlePanel battlePanel;
    private Player player;
    private Trainer opponent;

    public Battle(Player player, Trainer opponent) {
        this.player = player;
        this.opponent = opponent;
        this.loadWindow();
    }

    private void loadWindow() {
        JDialog window = new JDialog();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Combat : Joueur vs "+opponent.name);

        BattlePanel battlePanel = new BattlePanel();
        battlePanel.addBattle(this);
        window.add(battlePanel);
        window.pack();

        window.setModal(true);
        window.setAlwaysOnTop(true);
        window.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void startAnimation(Graphics2D g) {
        BufferedImage ryanGosling = null;
        BufferedImage oponnentImage = null;
        BufferedImage VSLogo = null;

        try {
            // Je n'ai pas reussi a trouver d'images interessantes.
            ryanGosling = ImageIO.read(new FileInputStream("../res/sprites/ryan_gosling_cereal.png"));
            oponnentImage = ImageIO.read(new FileInputStream("../res/sprites/oponnent_standing_down.png"));
            VSLogo = ImageIO.read(new FileInputStream("../res/sprites/vs_logo.png"));
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }

        int xSize = 200;
        int ySize = 200;
        int middleY = (int)(BattlePanel.screenHeight/2 - ySize/2);
        int middleX = (int)(BattlePanel.screenWidth/2 - xSize/2);

        g.drawImage(ryanGosling, 0, middleY, xSize, ySize, null);
        g.drawImage(VSLogo, middleX, middleY, xSize, ySize, null);
        g.drawImage(oponnentImage, BattlePanel.screenWidth-xSize, middleY, xSize, ySize, null);
    }
}
