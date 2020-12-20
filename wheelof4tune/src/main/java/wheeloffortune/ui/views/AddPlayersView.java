
package wheeloffortune.ui.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import wheeloffortune.ui.layouts.ErrorMessageLayout;

public class AddPlayersView implements View {
    
    private final ErrorMessageLayout emlo;
    private final Text addPlayers;
    private TextField player1;
    private TextField player2;
    private TextField player3;
    private final Button add;
    private HBox subPlayerLayout;
    private VBox playerLayout;    
    private Scene scene;
    
    public AddPlayersView() {
        
        emlo = new ErrorMessageLayout();
        addPlayers = new Text("Lisää peliin kolme pelaajaa:");
        add = new Button("Lisää pelaajat");        
        clearView();
    }
    
    //// tarkistaa, onko kaikkiin kenttiin lisätty pelaajan nimi
    public boolean all3PlayersFilledIn() {
        return !player1.getText().isEmpty() && !player2.getText().isEmpty() && !player3.getText().isEmpty();
    }
    
    public void setInvalidPlayersMessage() {
        emlo.setNewErrorMessage("Peliin tarvitaan kolme pelaajaa!");
        refresh();
    }
    
    //// palauttaa listan lisättyjen pelaajien nimistä
    public String[] getGivenPlayers() {
        String[] players = new String[3];
        players[0] = player1.getText().trim();
        players[1] = player2.getText().trim();
        players[2] = player3.getText().trim();
        return players;
    }
    
    //// tyhjentää näkymän
    public void clearView() {
        player1 = new TextField();
        player2 = new TextField();
        player3 = new TextField();
        refresh();
    }
    
    public Button getAddPlayersButton() {
        return add;
    }
    
    /// Rajapinnan metodit
    
    @Override
    public void refresh() {        
        subPlayerLayout = new HBox();
        subPlayerLayout.setSpacing(30);
        subPlayerLayout.getChildren().addAll(player1, player2, player3);
        playerLayout = new VBox();
        playerLayout.setSpacing(10);
        playerLayout.getChildren().addAll(addPlayers, subPlayerLayout, add, emlo.getLayout());

        scene = new Scene(playerLayout, 600, 400);
    }

    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
    
}
