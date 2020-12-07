
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
        
        this.play = new Button("Pelaa onnenpyörää");
        this.addPhrase = new Button("Lisää uusi fraasi");
        this.playerStats = new Button("Pelaajatilastot");
        
        this.welcome = new Label("Tervetuloa!");
        this.instructions = new Text("Tarkastele pelaajatilastoja, lisää uusi fraasi tai pelaa peliä!");
        
        this.buttonLayout = new HBox();
        this.buttonLayout.setSpacing(10);
        this.buttonLayout.getChildren().addAll(this.playerStats, this.addPhrase, this.play);
        
        this.layout = new VBox();
        this.layout.setSpacing(20);
        this.layout.getChildren().addAll(this.welcome, this.instructions, this.buttonLayout);
        
        this.scene = new Scene(layout, 600, 400);
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
