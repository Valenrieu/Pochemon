package pochemon.openworld.entities;

import pochemon.battle.objects.entities.PochemonList;

// Liste de tous les adversaires crees.

public class TrainerList {
    private static final PochemonList pochemonList = new PochemonList();
    public static final Trainer[] list = new Trainer[4];

    public TrainerList() {
        this.loadTrainers();
    }

    private static void loadTrainers() {
        list[0] = new Trainer(39, 58, "Christian", Direction.LEFT, pochemonList.list[1]);
        list[1] = new Trainer(48, 56, "Bruce", Direction.DOWN, pochemonList.list[2]);
        list[2] = new Trainer(23, 21, "Giovanni", Direction.DOWN, pochemonList.list[3]);
        list[3] = new Trainer(13, 31, "Sacha", Direction.RIGHT, pochemonList.list[4]);
    }
}
