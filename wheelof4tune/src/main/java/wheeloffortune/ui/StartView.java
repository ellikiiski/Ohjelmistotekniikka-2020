
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartView implements View {
    
    private VBox layout;
    private ButtonLayout buttonLO;
    private String[] buttonNames;
    private Label welcome;
    private Text instructions;
    private Scene scene;
    
    public StartView() {
        buttonNames = new String[3];
        buttonNames[0] = "Pelaajatilastot";
        buttonNames[1] = "Lisää uusi fraasi";
        buttonNames[2] = "Pelaa onnenpyörää";
        
        welcome = new Label("Tervetuloa!");
        instructions = new Text("Tarkastele pelaajatilastoja, lisää uusi fraasi tai pelaa peliä!");
        
        buttonLO = new ButtonLayout(buttonNames, 10);
        
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(welcome, instructions, buttonLO.getLayout());
        
        scene = new Scene(layout, 600, 400);
    }
    
    public Button getPlayerStatsButton() {
        return buttonLO.getButton("Pelaajatilastot");
    }
    
    public Button getAddPhraseButton() {
        return buttonLO.getButton("Lisää uusi fraasi");
    }
    
    public Button getPlayButton() {
        return buttonLO.getButton("Pelaa onnenpyörää");
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
