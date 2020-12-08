
package wheeloffortune.ui;

import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import wheeloffortune.domain.Player;

public class PlayersLayout implements Layout {
    
    private HashMap<Player, OnePlayerLayout> oplos;

    private OnePlayerLayout inTurn;

    private HBox layout;

    public PlayersLayout(Player p1, Player p2, Player p3, String[] bs) {
        oplos = new HashMap<>();
        oplos.put(p1, new OnePlayerLayout(p1, bs));
        oplos.put(p2, new OnePlayerLayout(p2, bs));
        oplos.put(p3, new OnePlayerLayout(p3, bs));

        refresh();
    }

    private void setPlayerInTurn(Player p) {
        for (Player player : oplos.keySet()) {
            if (player.equals(p)) {
                oplos.get(p).setTurn(true);
            } else {
                oplos.get(p).setTurn(false);
            }
        }
    }

    public Button getPlayerButton(Player p, String b) {
        return oplos.get(p).getButtonLayout().getButton(b);
    }

    @Override
    public void refresh() {
        layout = new HBox();
        layout.setSpacing(30);
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
