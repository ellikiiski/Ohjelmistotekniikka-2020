
package wheeloffortune.ui;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import wheeloffortune.domain.Game;
import wheeloffortune.domain.PhraseDBhandler;
import wheeloffortune.domain.Player;
import wheeloffortune.domain.PlayerDBhandler;

public class GameView implements View {
    
    private Game game;
    
    private final Label wheeloffortune;
    
    private PlayersLayout pllo;
    private PhraseLayout phlo;
    private WheelLayout wlo;
    private TurnLayout tlo;
    private GuessLayout glo;
    
    private VBox layout;
    
    private HBox subLO1;
    private HBox subLO2;
    
    private Scene scene;
    
    public GameView() {
        game = null;
        
        wheeloffortune = new Label("ONNENPYÖRÄ");
        
        wlo = new WheelLayout();
        tlo = new TurnLayout();
        glo = new GuessLayout();
        
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
        
        pllo = initPllo();
        phlo = new PhraseLayout(game.getPhraseAsString(), game.getCategory());
        
        tlo.setPlayerInTurn(game.getPlayerInTurn().getName());
        
        refresh();
    }
    
    private PlayersLayout initPllo() {
        ArrayList<Player> helpList = new ArrayList<>();
        
        for (Player player : game.getPlayerList()) {
            helpList.add(player);
        }
        
        return new PlayersLayout(helpList.get(0), helpList.get(1), helpList.get(2));
    }
    
    public void setPlayerInTurn(Player player) {
        tlo.setPlayerInTurn(player.getName());
    }
    
    public void spinTheWheel() {
        String spinner = game.getPlayerInTurn().getName();
        game.spinWheel();
        String instruction = "";
        if (game.latestSpinIsBankcrupt()) {
            instruction = "Voi ei! Menitit kaikki rahasi ja vuorosikin.";
            newTurn();
        } else if (game.latestSpinIsSkip()) {
            instruction = "Menetät vuorosi, mutta rahasi säästyvät.";
            newTurn();
        } else {
            instruction = "Kirjoita alle konsonantti, jota haluat veikata.";
            glo.setGuessConsonant();
        }
        wlo.setNewSpin(spinner, game.getLatestSpinSectorName(), instruction);
        tlo.setPlayerInTurn(game.getPlayerInTurn().getName());
        refresh();
    }
    
    public void newTurn() {
        phlo.setPhrase(game.getPhraseAsString());
        pllo.addMoneyToBank(game.getPlayerInTurn(), game.getScore());
        glo.setToInit();
        tlo.setPlayerInTurn(game.getPlayerInTurn().getName());
    }
    
    // alustava
    public boolean guessConsonant() {
        String s = glo.getFieldText();
        if (s.length() != 1) {
            return false;
        }
        int letters = game.guessConsonant(s.toUpperCase().charAt(0));
        if (letters  >= 0) {
            newTurn();
            refresh();
            return true;
        }
        return false;
    }
    
    // alustava
    public boolean setBuyNoun() {
        glo.setBuyNoun();
        refresh();
        return true;
    }
    
    // alustava
    public boolean buyNoun() {
        String s = glo.getFieldText();
        if (s.length() != 1) {
            return false;
        }
        int letters = game.buyNoun(s.toUpperCase().charAt(0));
        if (letters >= 0) {
            newTurn();
            refresh();
            return true;
        }
        return false;
    }
    
    // tätäkin vois vähän pohtia tarkemmin
    public boolean setGuessThePhrase() {
        glo.setGuessThePhrase();
        refresh();
        return true;
    }
    
    public void guessThePhrase() {
        String s = glo.getFieldText();
        game.tryToGuessPhrase(s);
        newTurn();
        refresh();
    }
    
    // alustava
    public void setMessage(String message) {
        layout.getChildren().add(new Label(message));
    }
    
    public Button getSpinButton() {
        return tlo.getSpinButton();
    }
    
    public Button getBuyNounButton() {
        return tlo.getBuyNounButton();
    }
    
    public Button getGuessThePhraseButton() {
        return tlo.getGuessThePhraseButton();
    }
    
    public Button getGuessButton() {
        return glo.getGuessButton();
    }
    
    public Button getBuyButton() {
        return glo.getBuyButton();
    }
    
    public Button getSolveButton() {
        return glo.getSolveButton();
    }
    
    public void refresh() {
        subLO1 = new HBox();
        subLO1.setSpacing(40);
        subLO1.getChildren().addAll(phlo.getLayout(), wlo.getLayout());
        
        subLO2 = new HBox();
        subLO2.setSpacing(40);
        subLO2.getChildren().addAll(tlo.getLayout(), glo.getLayout());
        
        layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().addAll(wheeloffortune,  pllo.getLayout(), subLO1, subLO2);
        
        scene = new Scene(layout, 800, 500);
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
