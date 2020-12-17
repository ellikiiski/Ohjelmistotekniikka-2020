
package wheeloffortune.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GuessLayout implements Layout {
    
    private final Label cLabel;
    private final Label nLabel;
    
    private TextField field;
    private Button guess;
    
    private Label labelShown;
    
    private VBox layout;
    
    public GuessLayout() {
        cLabel = new Label("Arvaa konsonanttia (B, C, D, F, G, H, J, K, L, M, N, P, Q, R, S, T, V, W, X, Z)");
        nLabel = new Label("Osta vokaalit hintaan 250€ (A, E, I, O, U, Y, Å, Ä, Ö)");
        
        field = new TextField();
        guess = new Button("Veikkaa");
        
        setToInit();
    }
    
    public void setGuessConsonant() {
        labelShown = cLabel;
        guess.setDisable(false);
        refresh();
    }
    
    public void setBuyNoun() {
        labelShown = nLabel;
        guess.setDisable(false);
        refresh();
    }
    
    public void setToInit() {
        labelShown = new Label("(Pyöritä pyörää tai valitse vokaalin osto)");
        guess.setDisable(true);
        refresh();
    }

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(6);
        layout.getChildren().addAll(field, labelShown, guess);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
