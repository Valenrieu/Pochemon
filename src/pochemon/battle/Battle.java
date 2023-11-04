package pochemon.battle;

import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.openworld.entities.Entity;
import pochemon.openworld.entities.Player;
import pochemon.openworld.entities.Trainer;
import pochemon.battle.objects.entities.Pochemon;
import pochemon.battle.objects.entities.HeatPochemon;
import pochemon.battle.objects.entities.RainPochemon;
import pochemon.battle.objects.entities.LeafPochemon;
import pochemon.BattlePanel;

public class Battle {
    private JDialog window;
    private BattlePanel battlePanel;
    private Player player;
    private Trainer opponent;
    private BufferedImage ryanGosling = null;
    private BufferedImage opponentImage = null;
    private BufferedImage VSLogo = null;
    private final long beginningTime;
    private long lastAttack;
    private int previousAttack = 1; // 0 pour joueur, 1 pour adversaire
    private Entity winner = null;

    public Battle(Player player, Trainer opponent) {
        this.player = player;
        this.opponent = opponent;
        beginningTime = System.currentTimeMillis();
        lastAttack = System.currentTimeMillis();
        this.loadImages();
        this.loadWindow();
    }

    public void updateBattle() {
        if(System.currentTimeMillis()-beginningTime < 4000) {
            return;
        } else if(System.currentTimeMillis()-lastAttack < 3000) {
            // Pause de 3 secondes entre chaque attaque.
            return;
        }

        if(previousAttack==1 && winner==null) {
            previousAttack = 0;
            player.pochemon.attack(opponent.pochemon);

            if(!opponent.pochemon.isAlive()) {
                winner = player;
                player.pochemon.heal();
                opponent.pochemon.heal();

                player.pochemon.addExperience(opponent.pochemon.getLvl());
                player.pochemon.levelUp();
            }
        } else if(previousAttack==0 && winner==null) {
            previousAttack = 1;
            opponent.pochemon.attack(player.pochemon);

            if(!player.pochemon.isAlive()) {
                winner = opponent;
                player.pochemon.heal();
                opponent.pochemon.heal();
                // Pas d'experience gagnee si l'adversaire gagne.
            }
        }

        lastAttack = System.currentTimeMillis();
    }

    public void draw(Graphics2D g2) {
        if(System.currentTimeMillis()-beginningTime < 4000) {
            this.startAnimation(g2);
        } else {
            if(winner==null) {
                this.drawBattle(g2);
            } else {
                this.drawWinner(g2);
            }
        }
    }

    private void drawBattle(Graphics2D g2) {
        g2.drawImage(battlePanel.backgroundImage, 0, 0, battlePanel.screenWidth, battlePanel.screenHeight, null);
        player.pochemon.draw(g2, true);
        opponent.pochemon.draw(g2, false);
        this.addLabel(g2, 0, 0, 215, 4*12 + 2);
        this.addLabel(g2, 275, battlePanel.screenHeight-(4*12 + 2), 215, 4*12 + 2);
        this.writePochemonInfos(g2, opponent.pochemon, true);
        this.writePochemonInfos(g2, player.pochemon, false);
    }

    private void addLabel(Graphics2D g2, int x, int y, int width, int height) {
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, width, height, 0, 0);
    }

    private void writePochemonInfos(Graphics2D g2, Pochemon pochemon, boolean isOpponent) {
        int fontSize = 12;
        String type = "";
        String name = pochemon.name;
        String lvl = Integer.toString(pochemon.getLvl());
        String HPStatus = pochemon.getHP()+" / "+pochemon.getMaxHP()+" PV";
        String xp = pochemon.getExperience()+" XP";
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", 1, fontSize));

        if(pochemon instanceof HeatPochemon) {
            type = "Type chaleur";
        } else if(pochemon instanceof LeafPochemon) {
            type = "Type feuille";
        } else if(pochemon instanceof RainPochemon) {
            type = "Type pluie";
        }

        // Pour aligner le niveau a droite

        do {
            name += " ";

            if(((name.length()+lvl.length())*12)-2==215) {
                name += lvl;
                break;
            }
        } while(true);

        if(isOpponent) {
            g2.drawString(name, 1, fontSize);
            g2.drawString(type, 1, 2*fontSize);
            g2.drawString(HPStatus, 1, 3*fontSize);
            g2.drawString(xp, 1, 4*fontSize);
        } else {
            g2.drawString(name, 276, battlePanel.screenHeight-(fontSize*4 + 2)+fontSize);
            g2.drawString(type, 276, battlePanel.screenHeight-(fontSize*4 + 2)+2*fontSize);
            g2.drawString(HPStatus, 276, battlePanel.screenHeight-(fontSize*4 + 2)+3*fontSize);
            g2.drawString(xp, 276, battlePanel.screenHeight-(fontSize*4 + 2)+4*fontSize);
        }
    }

    private void drawWinner(Graphics2D g2) {
        int fontSize = 60, imageSize = 150;
        String message = "";
        BufferedImage image = null;
        int x, y;
        int middleX, middleY;
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if(winner instanceof Player) {
            message = "Joueur a gagne";
            image = ryanGosling;
        } else {
            message = opponent.name+" a gagne";
            image = opponentImage;
        }

        x = (int)(battlePanel.screenWidth - message.length()*fontSize);
        y = (int)(battlePanel.screenHeight/3 - fontSize);
        middleY = (int)(BattlePanel.screenHeight/2 - imageSize/2);
        middleX = (int)(BattlePanel.screenWidth/2 - imageSize/2);

        g2.drawImage(battlePanel.backgroundImage, 0, 0, battlePanel.screenWidth, battlePanel.screenHeight, null);
        this.addLabel(g2, 0, 0, battlePanel.screenWidth, battlePanel.screenHeight/3);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", 1, fontSize));
        g2.drawString(message, x, y);
        g2.drawImage(image, middleX, middleY+25, imageSize, imageSize, null);
    }

    private void loadImages() {
        try {
            // Je n'ai pas reussi a trouver d'images interessantes.
            ryanGosling = ImageIO.read(new FileInputStream("../res/sprites/ryan_gosling_cereal.png"));
            opponentImage = ImageIO.read(new FileInputStream("../res/sprites/opponent_standing_down.png"));
            VSLogo = ImageIO.read(new FileInputStream("../res/sprites/vs_logo.png"));
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }
    }

    private void loadWindow() {
        window = new JDialog();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Combat : Joueur vs "+opponent.name);

        battlePanel = new BattlePanel();
        battlePanel.startGameThread();
        battlePanel.addBattle(this);
        window.add(battlePanel);
        window.pack();

        window.setModal(true);
        window.setAlwaysOnTop(true);
        window.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private void startAnimation(Graphics2D g2) {
        int xSize = 150;
        int ySize = 150;
        int middleY = (int)(BattlePanel.screenHeight/2 - ySize/2);
        int middleX = (int)(BattlePanel.screenWidth/2 - xSize/2);

        g2.drawImage(battlePanel.backgroundImage, 0, 0, battlePanel.screenWidth, battlePanel.screenHeight, null);
        g2.drawImage(ryanGosling, 0, middleY, xSize, ySize, null);
        g2.drawImage(VSLogo, middleX, middleY, xSize, ySize, null);
        g2.drawImage(opponentImage, BattlePanel.screenWidth-xSize, middleY, xSize, ySize, null);
    }
}
