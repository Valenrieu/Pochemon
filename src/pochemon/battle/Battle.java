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

    public void startBattle(Graphics2D g2) {
        startAnimation(g2);
        g2.drawImage(battlePanel.backgroundImage, 0, 0, battlePanel.screenWidth, battlePanel.screenHeight, null);
        player.pochemon.draw(g2, true);
        opponent.pochemon.draw(g2, false);
        /*while(player.pochemon.isAlive() && opponent.pochemon.isAlive()) {
            player.pochemon.attack(opponent.pochemon);
            opponent.pochemon.animate(g2, battlePanel.backgroundImage, battlePanel.screenWidth, battlePanel.screenHeight, false);

            if(!opponent.pochemon.isAlive()) {
                break;
            }
            
            opponent.pochemon.attack(player.pochemon);
            player.pochemon.animate(g2, battlePanel.backgroundImage, battlePanel.screenWidth, battlePanel.screenHeight, true);
        }*/
    }

    private void loadWindow() {
        JDialog window = new JDialog();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Combat : Joueur vs "+opponent.name);

        battlePanel = new BattlePanel();
        battlePanel.addBattle(this);
        window.add(battlePanel);
        window.pack();

        window.setModal(true);
        window.setAlwaysOnTop(true);
        window.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void startAnimation(Graphics2D g2) {
        BufferedImage ryanGosling = null;
        BufferedImage opponentImage = null;
        BufferedImage VSLogo = null;

        g2.drawImage(battlePanel.backgroundImage, 0, 0, battlePanel.screenWidth, battlePanel.screenHeight, null);

        try {
            // Je n'ai pas reussi a trouver d'images interessantes.
            ryanGosling = ImageIO.read(new FileInputStream("../res/sprites/ryan_gosling_cereal.png"));
            opponentImage = ImageIO.read(new FileInputStream("../res/sprites/opponent_standing_down.png"));
            VSLogo = ImageIO.read(new FileInputStream("../res/sprites/vs_logo.png"));
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }

        int xSize = 150;
        int ySize = 150;
        int middleY = (int)(BattlePanel.screenHeight/2 - ySize/2);
        int middleX = (int)(BattlePanel.screenWidth/2 - xSize/2);

        g2.drawImage(ryanGosling, 0, middleY, xSize, ySize, null);
        g2.drawImage(VSLogo, middleX, middleY, xSize, ySize, null);
        g2.drawImage(opponentImage, BattlePanel.screenWidth-xSize, middleY, xSize, ySize, null);
    
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
