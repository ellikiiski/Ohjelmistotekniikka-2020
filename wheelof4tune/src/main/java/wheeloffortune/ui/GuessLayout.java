
package wheeloffortune.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GuessLayout implements Layout {
    
    private final Label cLabel;
    private final Label nLabel;
    private final Label gLabel;
    
    private TextField field;
    private ButtonLayout buttons;
    
    private Label labelShown;
    
    private VBox layout;
    
    public GuessLayout() {
        cLabel = new Label("Arvaa konsonanttia (B, C, D, F, G, H, J, K, L, M, N, P, Q, R, S, T, V, W, X, Z)");
        nLabel = new Label("Osta vokaalit hintaan 250€ (A, E, I, O, U, Y, Å, Ä, Ö)");
        gLabel = new Label("Yritä ratkaista tehtävä!");
        
        field = new TextField();
        String[] bs = {"Veikkaa", "Osta", "Ratkaise"};
        buttons = new ButtonLayout(bs, 4);
        
        setToInit();
    }
    
    public void setGuessConsonant() {
        labelShown = cLabel;
        buttons.disableAll();
        buttons.enableButton("Veikkaa");
        field.setDisable(false);
        refresh();
    }
    
    public void setBuyNoun() {
        labelShown = nLabel;
        buttons.disableAll();
        buttons.enableButton("Osta");
        field.setDisable(false);
        refresh();
    }
    
    public void setGuessThePhrase() {
        labelShown = gLabel;
        buttons.disableAll();
        buttons.enableButton("Ratkaise");
        field.setDisable(false);
        refresh();
    }
    
    public void setToInit() {
        labelShown = new Label("(Pyöritä pyörää tai valitse vokaalin osto)");
        buttons.disableAll();
        field = new TextField();
        field.setDisable(true);
        refresh();
    }
    
    public String getFieldText() {
        return field.getText();
    }
    
    public Button getGuessButton() {
        return buttons.getButton("Veikkaa");
    }
    
    public Button getBuyButton() {
        return buttons.getButton("Osta");
    }
    
    public Button getSolveButton() {
        return buttons.getButton("Ratkaise");
    }

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(6);
        layout.getChildren().addAll(field, labelShown, buttons.getLayout());
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
