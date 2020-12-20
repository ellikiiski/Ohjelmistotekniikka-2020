
package wheeloffortune.ui.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import wheeloffortune.ui.layouts.ButtonLayout;

public class StartView implements View {
    
    private VBox layout;
    private final ButtonLayout buttonLO;
    private final Label welcome;
    private final Text instructions;
    private Scene scene;
    
    public StartView() {
        String[] bs = {"Pelaajatilastot", "Lisää uusi fraasi", "Pelaa onnenpyörää"};        
        welcome = new Label("Tervetuloa!");
        instructions = new Text("Tarkastele pelaajatilastoja, lisää uusi fraasi tai pelaa peliä!");        
        buttonLO = new ButtonLayout(bs, 10);        
        refresh();
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
    
    /// Rajapinnan metodit
    
    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(welcome, instructions, buttonLO.getLayout());
        scene = new Scene(layout, 600, 400);
    }

    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
    
}
