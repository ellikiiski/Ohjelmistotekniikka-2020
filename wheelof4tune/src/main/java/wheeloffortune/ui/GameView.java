
package wheeloffortune.ui;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import wheeloffortune.domain.Game;
import wheeloffortune.domain.Phrase;
import wheeloffortune.domain.PhraseDBhandler;
import wheeloffortune.domain.Player;
import wheeloffortune.domain.PlayerDBhandler;
import wheeloffortune.domain.Sector;

public class GameView implements View {
    
    private Game game;
    
    private Label wheeloffortune;
    private Button nextTurnTEST;
    private Button spinButtonTEST;
    
    private PlayersLayout inTurn;
    
    private PhraseLayout phlo;
    private WheelLayout wlo;
    private PlayersLayout pllo;
    
    private VBox layout;
    
    private Scene scene;
    
    public GameView() {
        game = null;
        
        wheeloffortune = new Label("ONNENPYÖRÄ");
        nextTurnTEST = new Button("Testinappi: vuoronvaihto");
        spinButtonTEST = new Button("Testinappi: Pyöritys");
        
        layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().add(wheeloffortune);
        
        scene = new Scene(layout, 1000, 600);
    }
    
    public void setGame(PlayerDBhandler plDBh, PhraseDBhandler phDBh, String[] players) {
        game = new Game(plDBh, phDBh);        
        for (String p : players) {
            game.addPlayer(p);
        }
        
        phlo = new PhraseLayout(game.getPhraseAsString(), game.getCategory());
        wlo = new WheelLayout();
        pllo = initPllo();
        
        refresh();
    }
    
    private PlayersLayout initPllo() {
        ArrayList<Player> helpList = new ArrayList<>();
        
        for (Player player : game.getPlayerList()) {
            helpList.add(player);
        }
        
        String[] buttons = new String[3];
        buttons[0] = "Arvaa";
        buttons[1] = "Pyöritä";
        buttons[2] = "Osta vokaali";
        
        return new PlayersLayout(helpList.get(0), helpList.get(1), helpList.get(2), buttons);
    }
    
    public void spinTheWheel() {
        game.spinWheel();
        wlo.setSpinnedSector(game.getLatestSpinSectorName());
        refresh();
    }
    
    public Button getNextTurnButton() {
        return nextTurnTEST;
    }
    
    public Button getSpinButton() {
        return spinButtonTEST;
    }
    
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().addAll(wheeloffortune, spinButtonTEST, phlo.getLayout(), wlo.getLayout(), pllo.getLayout());
        
        scene = new Scene(layout, 800, 500);
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
