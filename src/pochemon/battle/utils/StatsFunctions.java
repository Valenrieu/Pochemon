package pochemon.battle.utils;

import java.util.Random;

public final class StatsFunctions {
    private static final Random random = new Random();
    private static final int ATTACKS_POWER = 10;

    // Math.cbrt(x) : racine cubique de x
    public static final int level(int experience) {
        return (int)Math.ceil(Math.cbrt((double)experience));
    }

    public static final int experience(int level) {
        return (int)Math.ceil(Math.pow((double)level, 3));
    }

    public static final int damages(int level, int attack, int defense, double efficiency) {
        double damageMultiplier = random.nextDouble();
        damageMultiplier = 0.85 + (damageMultiplier*(1 - 0.85));
        // Nombre aleatoire entre 0.85 et 1 inclus

        return (int)Math.ceil((Math.floor(Math.floor(Math.floor(level*0.4 + 2)*
                    attack*ATTACKS_POWER/defense)/50)+2)*
                    efficiency*damageMultiplier);
    }

    public static final int maxHP(int level) {
        return (int)Math.floor(0.01*(30 + Math.floor(0.25*level))) + level + 10;
    }

    public static final int getXp(int level) {
        return (int)(level*experience(5)*1.5/7);
    }
}
