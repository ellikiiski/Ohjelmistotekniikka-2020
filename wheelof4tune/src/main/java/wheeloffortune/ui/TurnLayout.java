
package wheeloffortune.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TurnLayout implements Layout {
    
    private Label whosTurn;
    private ButtonLayout buttons;
    
    private VBox layout;
    
    public TurnLayout() {
        whosTurn = new Label("Pelaajan x vuoro");
        String[] bs = {"Osta vokaali", "Pyöritä", "Arvaa ratkaisua"};
        buttons = new ButtonLayout(bs, 10);
        
        refresh();
    }
    
    public void setPlayerInTurn(String player) {
        whosTurn = new Label("Pelaajan " + player + " vuoro");
        refresh();
    }
    
    public Button getSpinButton() {
        return buttons.getButton("Pyöritä");
    }
    
    public Button getBuyNounButton() {
        return buttons.getButton("Osta vokaali");
    }
    
    public Button getGuessThePhraseButton() {
        return buttons.getButton("Arvaa ratkaisua");
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
