package pochemon.battle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Dialog;

import pochemon.ui.entities.Player;
import pochemon.ui.entities.Dresser;
import pochemon.BattlePanel;

public class Battle {
    private JDialog window;
    private BattlePanel battlePanel;
    private Player player;
    private Dresser opponent;

    public Battle(Player player, Dresser opponent) {
        this.player = player;
        this.opponent = opponent;
        this.loadWindow();
    }

    private void loadWindow() {
        JDialog window = new JDialog();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Combat");

        BattlePanel battlePanel = new BattlePanel();
        battlePanel.startGameThread();
        window.add(battlePanel);
        window.pack();

        window.setModal(true);
        window.setAlwaysOnTop(true);
        window.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
