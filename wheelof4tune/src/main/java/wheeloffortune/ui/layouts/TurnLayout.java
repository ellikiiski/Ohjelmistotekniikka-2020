
package wheeloffortune.ui.layouts;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TurnLayout implements Layout {
    
    private Label latestEvent;
    private Label whosTurn;
    private final ButtonLayout buttons;    
    private VBox layout;
    
    public TurnLayout() {
        String[] bs = {"Osta vokaali", "Pyöritä", "Arvaa ratkaisua"};
        buttons = new ButtonLayout(bs, 10);        
        seToInit();
    }
    
    public void seToInit() {
        latestEvent = new Label("Avaa peli pyöräyttämällä onnenpyörää");
        whosTurn = new Label("Vuorossa olevaa pelaajaa ei vielä ladattu");
        disableAllButtons();
        enalbleSpinButton();        
        refresh();
    }
    
    //// asettaa parametrina annetun nimen vuoroon
    public void setPlayerInTurn(String player) {
        whosTurn = new Label("Pelaajan " + player + " vuoro");
        refresh();
    }
    
    //// asettaa näkyviin viimeisimmän tapahtuman parametreina annetun nimen ja tapahtumakuvauksen perusteella
    public void setLatestEvent(String player, String event) {
        latestEvent = new Label("Pelaaja " + player + " " + event);
        refresh();
    }
    
    /// Nappien disablointi ja enablointi
    
    public void disableAllButtons() {
        buttons.disableAll();
    }
    
    public void enalbleSpinButton() {
        buttons.enableButton("Pyöritä");
    }
    
    public void enabeBuyNounButton() {
        buttons.enableButton("Osta vokaali");
    }
    
    public void enableGuessThePhraseButton() {
        buttons.enableButton("Arvaa ratkaisua");
    }
    
    /// Nappien getterit
    
    public Button getSpinButton() {
        return buttons.getButton("Pyöritä");
    }
    
    public Button getBuyNounButton() {
        return buttons.getButton("Osta vokaali");
    }
    
    public Button getGuessThePhraseButton() {
        return buttons.getButton("Arvaa ratkaisua");
    }
    
    /// Rajapinnan metodit

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(latestEvent, whosTurn, buttons.getLayout());
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
