
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartView implements View {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästä tai mistään muustakaan graafiseen käyttöliittymään
    // liittyvästä luokasta, sillä ne ovat pahasti kesken!!
    // Jätä siis huomiotta kaikki tämän pakkauksen luokat paitsi TextUI.java.
    
    private VBox layout;
    private HBox buttonLayout;
    private Button play;
    private Button addPhrase;
    private Button playerStats;
    private Label welcome;
    private Text instructions;
    private Scene scene;
    
    public StartView() {
        
        play = new Button("Pelaa onnenpyörää");
        addPhrase = new Button("Lisää uusi fraasi");
        playerStats = new Button("Pelaajatilastot");
        
        welcome = new Label("Tervetuloa!");
        instructions = new Text("Tarkastele pelaajatilastoja, lisää uusi fraasi tai pelaa peliä!");
        
        buttonLayout = new HBox();
        buttonLayout.setSpacing(10);
        buttonLayout.getChildren().addAll(playerStats, addPhrase, play);
        
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(welcome, instructions, buttonLayout);
        
        scene = new Scene(layout, 600, 400);
    }
    
    public Button getPlayerStatsButton() {
        return playerStats;
    }
    
    public Button getAddPhraseButton() {
        return addPhrase;
    }
    
    public Button getPlayButton() {
        return play;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
