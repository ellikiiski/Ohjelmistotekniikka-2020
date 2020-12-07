
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddPlayersView implements View {
    
    private Text addPlayers;
    private TextField player1;
    private TextField player2;
    private TextField player3;
    private Button add;
    private HBox subPlayerLayout;
    private VBox playerLayout;
    
    private Scene scene;
    
    public AddPlayersView() {
        this.addPlayers = new Text("Lisää peliin kolme pelaajaa:");
        this.player1 = new TextField();
        this.player2 = new TextField();
        this.player3 = new TextField();
        this.add = new Button("Lisää pelaajat");
        this.subPlayerLayout = new HBox();
        this.subPlayerLayout.setSpacing(30);
        this.subPlayerLayout.getChildren().addAll(this.player1, this.player2, this.player3);
        this.playerLayout = new VBox();
        this.playerLayout.setSpacing(10);
        this.playerLayout.getChildren().addAll(this.addPlayers, this.subPlayerLayout, this.add);
        
        this.scene = new Scene(this.playerLayout, 600, 400);
    }
    
    public Button getAddPlayersButton() {
        return add;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
