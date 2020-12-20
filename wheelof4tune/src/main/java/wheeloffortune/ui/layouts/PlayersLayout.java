
package wheeloffortune.ui.layouts;

import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import wheeloffortune.gamelogic.Player;

public class PlayersLayout implements Layout {
    
    private HashMap<Player, OnePlayerLayout> oplos;

    private final Label players;

    private HBox layout;

    public PlayersLayout(Player p1, Player p2, Player p3) {
        oplos = new HashMap<>();
        oplos.put(p1, new OnePlayerLayout(p1));
        oplos.put(p2, new OnePlayerLayout(p2));
        oplos.put(p3, new OnePlayerLayout(p3));
        
        players = new Label("Pelaajat:");

        refresh();
    }

    public void setPlayerInTurn(Player p) {
        for (Player player : oplos.keySet()) {
            if (player.equals(p)) {
                oplos.get(p).setTurn(true);
            } else {
                oplos.get(p).setTurn(false);
            }
        }
    }
    
    public void addMoneyToBank(Player player, int money) {
        oplos.get(player).setMoney(money);
        refresh();
    }

    @Override
    public void refresh() {
        layout = new HBox();
        layout.setSpacing(50);
        layout.getChildren().add(players);
        for (OnePlayerLayout lo : oplos.values()) {
            layout.getChildren().add(lo.getLayout());
        }
    }

    @Override
    public Pane getLayout() {
        refresh();
        return layout;
    }
    
}
