package pochemon.battle.objects.entities;

import java.awt.image.BufferedImage;

import static pochemon.battle.utils.StatsFunctions.damages;

public class HeatPochemon extends Pochemon {
    public HeatPochemon(String name, int attack, int defense, BufferedImage frontSprite, BufferedImage backSprite) {
        super(name, attack, defense, frontSprite, backSprite);
    }

    public HeatPochemon(String name, int attack, int defense, BufferedImage frontSprite, BufferedImage backSprite, int lvl) {
        super(name, attack, defense, frontSprite, backSprite, lvl);
    }

    int getDamages(Pochemon pochemon) throws UnknownTypeException {
        if(pochemon instanceof RainPochemon) {
            return damages(this.lvl, this.attack, pochemon.defense, 0.5);
        } else if(pochemon instanceof LeafPochemon) {
            return damages(this.lvl, this.attack, pochemon.defense, 2);
        } else if(pochemon instanceof HeatPochemon) {
            return damages(this.lvl, this.attack, pochemon.defense, 1);
        } else {
            throw new UnknownTypeException();
        }
    }
}
