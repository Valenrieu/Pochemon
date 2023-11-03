package pochemon.battle.objects.entities;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Random;

import pochemon.BattlePanel;
import static pochemon.battle.utils.StatsFunctions.level;
import static pochemon.battle.utils.StatsFunctions.experience;
import static pochemon.battle.utils.StatsFunctions.maxHP;
import static pochemon.battle.utils.StatsFunctions.getXp;

public abstract class Pochemon {
    public final String name;
    protected int HP, maxHP, attack, defense, lvl, experience;
    public final BufferedImage frontSprite, backSprite;

    Pochemon(String name, int attack, int defense, BufferedImage frontSprite, BufferedImage backSprite) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.frontSprite = frontSprite;
        this.backSprite = backSprite;

        lvl = 5;
        experience = experience(lvl);
        maxHP = maxHP(lvl);
        HP = maxHP;
    }

    public void heal() {
        HP = maxHP;
    }

    public void addExperience(int opponentLvl) {
        experience = getXp(opponentLvl);
    }

    public void levelUp() {
        lvl = level(experience);
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

    public final void attack(Pochemon pochemon) {
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
            g2.drawImage(backSprite, 60, 250, 125, 125, null);   
        } else {
            g2.drawImage(frontSprite, 290, 100, 125, 125, null);
        }
    }

    abstract int getDamages(Pochemon pochemon) throws UnknownTypeException;
}
