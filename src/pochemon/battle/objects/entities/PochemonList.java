package pochemon.battle.objects.entities;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;

/* Liste de tous les pochemons crees et jouables.
 * Ils ont tous la meme image car je n'en ai pas assez.
 */

public final class PochemonList {
    public static final Pochemon[] list = new Pochemon[5];

    public PochemonList() {
        createPochemons();
    }

    private static void createPochemons() {
        BufferedImage frontSprite = null, backSprite = null;

        try {
            frontSprite = ImageIO.read(new File("../res/sprites/pochechu_standing_down.png"));
            backSprite = ImageIO.read(new File("../res/sprites/pochechu_standing_up.png"));
        } catch(IOException e) {
            System.out.println("res folder was altered.");
            System.exit(1);
        }

        // Pochemon du joueur
        list[0] = new HeatPochemon("Pochechu", 14, 13, frontSprite, backSprite);

        // Pochemon des adversaires
        list[1] = new LeafPochemon("Tidzo", 14, 11, frontSprite, backSprite, 5);
        list[2] = new RainPochemon("Yuhke", 15, 15, frontSprite, backSprite, 7);
        list[3] = new HeatPochemon("Fevdos", 23, 22, frontSprite, backSprite, 10);
        list[4] = new RainPochemon("Woleped", 24, 24, frontSprite, backSprite, 15);
    }
}
