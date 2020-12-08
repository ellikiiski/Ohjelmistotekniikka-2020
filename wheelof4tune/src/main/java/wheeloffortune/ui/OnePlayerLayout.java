
package wheeloffortune.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wheeloffortune.domain.Player;

public class OnePlayerLayout implements Layout {
    
    private Player player;
    private boolean inTurn;

    private Label name;
    private Label money;
    private ButtonLayout buttons;

    private VBox layout;

    public OnePlayerLayout(Player p, String[] bs) {
        player = p;
        inTurn = false;

        name = new Label(player.getName());
        money = new Label("0€");
        buttons = new ButtonLayout(bs, 5);
        buttons.disableAll();

        refresh();
    }

    public void setTurn(boolean turn) {
        inTurn = turn;
        refresh();
    }
    
    public ButtonLayout getButtonLayout() {
        return buttons;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(name, money, buttons.getLayout());
    }

    @Override
    public Pane getLayout() {
        refresh();
        return layout;
    }
    
}
