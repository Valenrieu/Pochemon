package pochemon.battle.objects.entities;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;

/* Liste de tous les pochemons crees et jouables.
 * Ils ont touts la meme image car je n'en ai pas assez.
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

        list[0] = new HeatPochemon("Pochechu", 15, 12, frontSprite, backSprite);
        list[1] = new LeafPochemon("Test", 11, 16, frontSprite, backSprite);
        list[2] = new RainPochemon("Test2", 20, 7, frontSprite, backSprite);
        list[3] = new HeatPochemon("Test3", 9, 18, frontSprite, backSprite);
        list[4] = new RainPochemon("Test4", 14, 13, frontSprite, backSprite);
    }
}
