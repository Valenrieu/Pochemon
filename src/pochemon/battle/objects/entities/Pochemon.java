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
    private final Random random;

    Pochemon(String name, int attack, int defense, BufferedImage frontSprite, BufferedImage backSprite) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.frontSprite = frontSprite;
        this.backSprite = backSprite;

        random = new Random();
        lvl = 5;
        experience = experience(lvl);
        maxHP = maxHP(lvl);
        HP = maxHP;
    }

    Pochemon(String name, int attack, int defense, BufferedImage frontSprite, BufferedImage backSprite, int lvl) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.frontSprite = frontSprite;
        this.backSprite = backSprite;

        random = new Random();
        this.lvl = lvl;
        experience = experience(lvl);
        maxHP = maxHP(lvl);
        HP = maxHP;
    }

    public void heal() {
        HP = maxHP;
    }

    private void heal(int healHP) {
        HP += healHP;

        if(HP>maxHP) {
            HP = maxHP;
        }
    }

    public void addExperience(int opponentLvl) {
        experience += getXp(opponentLvl);
    }

    public void levelUp() {
        int oldLvl = lvl;
        lvl = level(experience);

        if(oldLvl!=lvl) {
            attack++;
            defense++;
            maxHP = maxHP(lvl);
            this.heal();
        }
    }

    public boolean isAlive() {
        return HP>0;
    }

    public int getHP() {
        return HP;
    }

    public int getExperience() {
        return experience;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getLvl() {
        return lvl;
    }

    public final void attack(Pochemon pochemon) {
        boolean hasHealed;

        try {
            hasHealed = this.healLastAttack(pochemon);

            /* A chaque tour, le pochemon a une chance sur 30
             * de guerir du nombre de PV que l'adversaire peut lui
             * infliger. S'il se soigne, il n'attaque pas.
             * Si son nombre de PV est au max, il attaque quand meme.
             */

            if(hasHealed) {
                return;
            }

            pochemon.HP -= this.getDamages(pochemon);

            if(pochemon.HP<0) {
                pochemon.HP = 0;
            }
        } catch(UnknownTypeException e) {
            e.printStackTrace();
        }
    }

    private final boolean healLastAttack(Pochemon pochemon) {
        int number = random.nextInt(30);

        if(number==0) {
            if(HP==maxHP) {
                return false;
            }

            try {
                this.heal(pochemon.getDamages(this));
            } catch(UnknownTypeException e) {
                return false;
            }

            return true;
        }

        return false;
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
