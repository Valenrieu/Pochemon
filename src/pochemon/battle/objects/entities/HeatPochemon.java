package pochemon.battle.objects.entities;

import java.awt.image.BufferedImage;

public class HeatPochemon extends Pochemon {
    public HeatPochemon(String name, int attack, int defense, BufferedImage frontSprite, BufferedImage backSprite) {
        super(name, attack, defense, frontSprite, backSprite);
    }

    int getDamages(Pochemon pochemon) throws UnknownTypeException {
        if(pochemon instanceof RainPochemon) {
            return (int)Math.ceil(((2*lvl/5 + 2)*20*attack/pochemon.defense/50 + 2)*
                                  0.5 * (damageMultiplier.nextInt(256-217) + 217));
        } else if(pochemon instanceof LeafPochemon) {
            return(int) Math.ceil(((2*lvl/5 + 2)*20*attack/pochemon.defense/50 + 2)*
                                  2 * (damageMultiplier.nextInt(256-217) + 217));
        } else if(pochemon instanceof HeatPochemon) {
            return (int)Math.ceil(((2*lvl/5 + 2)*20*attack/pochemon.defense/50 + 2)*
                                  1 * (damageMultiplier.nextInt(256-217) + 217));
        } else {
            throw new UnknownTypeException();
        }
    }
}
