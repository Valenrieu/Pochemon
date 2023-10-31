package pochemon.ui.entities;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

import pochemon.ui.map.Map;
import pochemon.battle.Battle;

public class Dresser extends StaticCharacter implements Talkable {
    public final String name;

    public Dresser(int x, int y, String name, Direction direction, Map map) {
        super(map);
        this.x = x;
        this.y = y;
        this.name = name;
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
