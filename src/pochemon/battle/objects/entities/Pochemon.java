package pochemon.battle.objects.entities;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Random;

import pochemon.BattlePanel;

public abstract class Pochemon {
    public final String name;
    protected int HP, maxHP, attack, defense, lvl, experience;
    public final BufferedImage frontSprite, backSprite;
    protected static Random damageMultiplier = new Random();

    Pochemon(String name, int attack, int defense, BufferedImage frontSprite, BufferedImage backSprite) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.frontSprite = frontSprite;
        this.backSprite = backSprite;

        lvl = 5;
        experience = 0;
        maxHP = (int)Math.floor(0.01*(30 + Math.floor(0.25*lvl))) + lvl + 10;
        HP = maxHP;
    }

    public boolean isAlive() {
        return HP>0;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getLvl() {
        return lvl;
    }

    public void attack(Pochemon pochemon) {
        try {
            pochemon.HP -= this.getDamages(pochemon);

            if(pochemon.HP<0) {
                pochemon.HP = 0;
            }
        } catch(UnknownTypeException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, boolean back) {
        if(back) {
            g2.drawImage(backSprite, 50, 250, 150, 150, null);   
        } else {
            g2.drawImage(frontSprite, 275, 100, 150, 150, null);
        }
    }

    abstract int getDamages(Pochemon pochemon) throws UnknownTypeException;
}
