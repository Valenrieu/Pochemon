package pochemon.openworld.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.openworld.map.Map;
import pochemon.battle.Battle;
import pochemon.battle.objects.entities.Pochemon;

public class Trainer extends StaticCharacter implements Talkable {
    public final String name;
    public final Pochemon pochemon;

    public Trainer(int x, int y, String name, Direction direction, Pochemon pochemon) {
        super();
        this.x = x;
        this.y = y;
        this.name = name;
        this.pochemon = pochemon;
        this.direction = direction;

        this.getImages("opponent");
    }

    public void draw(Graphics2D g) {
        super.draw(g);
    }

    public void talk(Player player) {
        Battle battle = new Battle(player, this);
    }
}
