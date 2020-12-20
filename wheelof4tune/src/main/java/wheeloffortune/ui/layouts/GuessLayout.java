
package wheeloffortune.ui.layouts;

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
    private final ButtonLayout buttons;
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
    
    /// Näkymän "tilan" vaihtaminen
    
    //// vaihtaa konsonantin arvaamiseen tarkoitetun asettelun
    //// eli vaihtaa näkyviin siihen tarkoitetun ohjetekstin cLabel ja disabloi muut paitsi "Veikkaa"-napin
    public void setGuessConsonant() {
        labelShown = cLabel;
        buttons.disableAll();
        buttons.enableButton("Veikkaa");
        field.setDisable(false);
        refresh();
    }
    
    //// vaihtaa vokaalin ostoon tarkoitetun asettelun
    //// eli vaihtaa näkyviin siihen tarkoitetun ohjetekstin nLabel ja disabloi muut paitsi "Osta"-napin
    public void setBuyNoun() {
        labelShown = nLabel;
        buttons.disableAll();
        buttons.enableButton("Osta");
        field.setDisable(false);
        refresh();
    }
    
    //// vaihtaa tehtävän ratkaisemiseen tarkoitetun asettelun
    //// eli vaihtaa näkyviin siihen tarkoitetun ohjetekstin gLabel ja disabloi muut paitsi "Ratkaise"-napin
    public void setGuessThePhrase() {
        labelShown = gLabel;
        buttons.disableAll();
        buttons.enableButton("Ratkaise");
        field.setDisable(false);
        refresh();
    }
    
    //// vaihtaa ns. disabloituun asetteluun 
    //// eli disabloi kaikki napit ja tekstikentän sekä vaihtaa näkyviin ohjeen
    public void setToInit() {
        labelShown = new Label("(Valitse ensin pyöritätkö, ostatko vai ratkaisetko tehtävän)");
        buttons.disableAll();
        field = new TextField();
        field.setDisable(true);
        refresh();
    }
    
    /// Komponenttien getterit
    
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
    
    /// Rajapinnan metodit

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
