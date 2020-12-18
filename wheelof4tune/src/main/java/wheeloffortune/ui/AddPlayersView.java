
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddPlayersView implements View {
    
    private final Text addPlayers;
    private TextField player1;
    private TextField player2;
    private TextField player3;
    private final Button add;
    private HBox subPlayerLayout;
    private VBox playerLayout;
    
    private Scene scene;
    
    public AddPlayersView() {
        
        addPlayers = new Text("Lisää peliin kolme pelaajaa:");
        add = new Button("Lisää pelaajat");
        
        refresh();
    }
    
    public Button getAddPlayersButton() {
        return add;
    }
    
    public boolean all3PlayersFilledIn() {
        return !player1.getText().isEmpty() && !player2.getText().isEmpty() && !player3.getText().isEmpty();
    }
    
    public String[] getGivenPlayers() {
        String[] players = new String[3];
        players[0] = player1.getText().trim();
        players[1] = player2.getText().trim();
        players[2] = player3.getText().trim();
        return players;
    }
    
    public void invalidPlayersMessage() {
        playerLayout.getChildren().add(new Label("Peliin tarvitaan kolme pelaajaa!"));
        scene = new Scene(playerLayout);
    }
    
    public void clearView() {
        
    }
    
    @Override
    public void refresh() {
        player1 = new TextField();
        player2 = new TextField();
        player3 = new TextField();
        
        subPlayerLayout = new HBox();
        subPlayerLayout.setSpacing(30);
        subPlayerLayout.getChildren().addAll(player1, player2, player3);
        playerLayout = new VBox();
        playerLayout.setSpacing(10);
        playerLayout.getChildren().addAll(addPlayers, subPlayerLayout, add);

        scene = new Scene(playerLayout, 600, 400);
    }

    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
    
}
