
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

public class GameView implements View {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästä tai mistään muustakaan graafiseen käyttöliittymään
    // liittyvästä luokasta, sillä ne ovat pahasti kesken!!
    // Jätä siis huomiotta kaikki tämän pakkauksen luokat paitsi TextUI.java.
    
    private Game game;
    
    private Label wheeloffortune;
    private Button nextTurnTEST;
    
    private PhraseLayout phlo;
    private WheelLayout wlo;
    private PlayersLayout pllo;
    
    private VBox layout;
    
    private Scene scene;
    
    public GameView() {
        game = null;
        
        wheeloffortune = new Label("ONNENPYÖRÄ");
        nextTurnTEST = new Button("Testinappula: vuoronvaihto");
        
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
        
        layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().addAll(wheeloffortune, nextTurnTEST, phlo.getLayout(), wlo.getLayout(), pllo.getLayout());
        
        scene = new Scene(layout, 800, 400);
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
    
    public void changeTurn() {
        game.nextPlayersTurn();
        pllo.setPlayerInTurn(game.getPlayerInTurn());
    }
    
    public Button getNextTurnButton() {
        return nextTurnTEST;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
    // Erillinen luokka pelaaja-asettelua varten
    
    private class PlayersLayout {
        
        private OnePlayerLayout plo1;
        private OnePlayerLayout plo2;
        private OnePlayerLayout plo3;
        
        private HBox layout;
        
        public PlayersLayout(Player p1, Player p2, Player p3, String[] bs) {
            plo1 = new OnePlayerLayout(p1, bs);
            plo2 = new OnePlayerLayout(p2, bs);
            plo3 = new OnePlayerLayout(p3, bs);
            
            layout = new HBox();
            layout.setSpacing(30);
            layout.getChildren().addAll(plo1.getLayout(), plo2.getLayout(), plo3.getLayout());
        }
        
        private void setPlayerInTurn(Player p) {
            if (plo1.getPlayer().equals(p)) {
                plo1.setPlayerInTurn();
                plo3.setPlayerOutOfTurn();
            } else if (plo2.getPlayer().equals(p)) {
                plo2.setPlayerInTurn();
                plo1.setPlayerOutOfTurn();
            } else if (plo3.getPlayer().equals(p)) {
                plo3.setPlayerInTurn();
                plo2.setPlayerOutOfTurn();
            }
        }

        public HBox getLayout() {
            return layout;
        }
        
    }
    
    private class OnePlayerLayout {
        
        private Player player;
        
        private Label name;
        private Label money;
        private ButtonLayout buttons;
        
        private VBox layout;
        
        public OnePlayerLayout(Player p, String[] bs) {
            player = p;
            
            name = new Label(player.getName());
            money = new Label("0€");
            buttons = new ButtonLayout(bs, 5);
            buttons.disableAll();
            
            layout = new VBox();
            layout.setSpacing(20);
            layout.getChildren().addAll(name, money, buttons.getLayout());
        }
        
        public void setPlayerInTurn() {
            buttons.enableAll();
            name = new Label(player.getName().toUpperCase());
            if (!game.canBuyNoun()) {
                buttons.disableButton("Osta vokaali");
            }
        }
        
        public void setPlayerOutOfTurn() {
            buttons.disableAll();
            name = new Label(player.getName());
        }
        
        public Player getPlayer() {
            return player;
        }
        
        public VBox getLayout() {
            return layout;
        }
        
    }
    
    private class ButtonLayout {
        
        private HashMap<String, Button> buttons;
        private HBox layout;
        
        public ButtonLayout(String[] bs, int spacing) {
            buttons = new HashMap<>();
            for (String b : bs) {
                buttons.put(b, new Button(b));
            }
            
            layout = new HBox();
            layout.setSpacing(spacing);
            
            for (Button button : buttons.values()) {
                layout.getChildren().add(button);
            }
        }
        
        public void disableButton(String b) {
            for (String buttonName : buttons.keySet()) {
                if (buttonName.equals(b)) {
                    buttons.get(b).setDisable(true);
                }
            }
        }
        
        public void disableAll() {
            for (Button b : buttons.values()) {
                b.setDisable(true);
            }
        }
        
        public void enableAll() {
            for (Button b : buttons.values()) {
                b.setDisable(false);
            }
        }
        
        public HBox getLayout() {
            return layout;
        }        
    }
    
    // Erillinen luokka onnenpyöräasettelua varten
    
    private class WheelLayout {
        
        private final Label heading;
        private Label spinned;
        
        private HBox layout;
        
        public WheelLayout() {
            heading = new Label("OSUIT SEKTORIIN ");
            spinned = new Label("ei vielä mitään");
            
            layout = new HBox();
            layout.setSpacing(10);
            layout.getChildren().addAll(heading, spinned);
        }
        
        public HBox getLayout() {
            return layout;
        }
    }
    
    // Erillinen luokka arvuuteltavaa tehtävää varten
    
    private class PhraseLayout {
        
        private Label phrase;
        private final Label category;        
        
        private VBox layout;
        
        public PhraseLayout(String phr, String cat) {
            phrase = new Label(cat);
            category = new Label(phr);
                        
            layout = new VBox();
            layout.setSpacing(10);
            layout.getChildren().addAll(phrase, category);
        }
        
        public VBox getLayout() {
            return layout;
        }
        
    }
}
