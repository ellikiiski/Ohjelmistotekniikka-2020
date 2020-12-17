
package wheeloffortune.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TurnLayout implements Layout {
    
    private Label whosTurn;
    private ButtonLayout buttons;
    
    private VBox layout;
    
    public TurnLayout() {
        whosTurn = new Label("Pelaajan x vuoro");
        String[] bs = {"Osta vokaali", "Pyöritä", "Arvaa"};
        buttons = new ButtonLayout(bs, 10);
        
        refresh();
    }

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(whosTurn, buttons.getLayout());
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
