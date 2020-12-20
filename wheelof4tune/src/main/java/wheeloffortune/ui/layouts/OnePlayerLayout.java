
package wheeloffortune.ui.layouts;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wheeloffortune.gamelogic.Player;

public class OnePlayerLayout implements Layout {
    
    private final Player player;
    private Label name;
    private Label money;
    private VBox layout;

    public OnePlayerLayout(Player p) {
        player = p;
        name = new Label(player.getName());
        money = new Label("0€");
        refresh();
    }
    
    public void setMoney(int m) {
        money = new Label(m + "€");
        refresh();
    }

    public Player getPlayer() {
        return player;
    }
    
    /// Rajapinnan metodit

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(name, money);
    }

    @Override
    public Pane getLayout() {
        refresh();
        return layout;
    }
    
}
