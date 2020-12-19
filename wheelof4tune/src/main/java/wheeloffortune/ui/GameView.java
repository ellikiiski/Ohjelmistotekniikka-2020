
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
    private String winnerName;
    private String correctPhrase;
    private int winningMoney;
    
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
        winnerName = "Ei kukaan";
        correctPhrase = "Ei, ei, ei";
        winningMoney = 0;
        
        wlo = new WheelLayout();
        tlo = new TurnLayout();
        glo = new GuessLayout();
        
        layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().add(new Label("Odotetaan pelin latautumista..."));
        
        scene = new Scene(layout, 800, 500);
    }
    
    public void setGame(PlayerDBhandler plDBh, PhraseDBhandler phDBh, String[] players) {
        game = new Game(plDBh, phDBh);        
        for (String p : players) {
            game.addPlayer(p);
        }
        
        pllo = initPllo();
        phlo = new PhraseLayout(game.getPhraseAsStringToPresent(), game.getCategory());
        
        glo.setToInit();
        wlo.setToInit();
        tlo.seToInit();
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
    
    public void spinTheWheel() {
        String spinner = game.getPlayerInTurn().getName();
        game.spinWheel();
        String instruction = "";
        if (game.latestSpinIsBankcrupt()) {
            instruction = " osui rosvo-sektoriin ja menitti sekä rahansa että vuoronsa.";
            newTurn();
        } else if (game.latestSpinIsSkip()) {
            instruction = "osui ohi-sektoriin ja menetti vuoronsa.";
            newTurn();
        } else {
            instruction = "osuit sektoriin " + game.getLatestSpinSectorName() + "!";
            glo.setGuessConsonant();
            tlo.disableAllButtons();
        }
        wlo.setNewSpin(game.getLatestSpinSectorName());
        tlo.setLatestEvent(spinner, instruction);
        tlo.setPlayerInTurn(game.getPlayerInTurn().getName());
        refresh();
    }
    
    public void newTurn() {
        phlo.setPhrase(game.getPhraseAsStringToPresent());
        pllo.addMoneyToBank(game.getPlayerInTurn(), game.getScore());
        glo.setToInit();
        tlo.setPlayerInTurn(game.getPlayerInTurn().getName());
        tlo.enalbleSpinButton();
        tlo.enableGuessThePhraseButton();
        if (game.canBuyNoun()) {
            tlo.enabeBuyNounButton();
        }
    }
    
    // alustava
    public boolean guessConsonant() {
        String guessed = glo.getFieldText();
        String playerGuessing = game.getPlayerInTurn().getName();
        if (guessed.length() != 1) {
            return false;
        }
        int letters = game.guessConsonant(guessed.toUpperCase().charAt(0));
        if (letters  >= 0) {
            if (letters == 0) {
                tlo.setLatestEvent(playerGuessing, "arvasi konsonanttia " + guessed.toUpperCase() + ", eikä sitä löytynyt\nyhtäkään kappaletta. Vuoro vaihtui.");
            } else {
                tlo.setLatestEvent(playerGuessing, "arvasit konsonanttia " + guessed.toUpperCase() + ", joita löytyi yhteensä " + letters + ".");
            }
            newTurn();
            refresh();
            return true;
        }
        return false;
    }
    
    // alustava
    public boolean setBuyNoun() {
        glo.setBuyNoun();
        tlo.setLatestEvent(game.getPlayerInTurn().getName(), "sinulla on pankissa tarpeeksi rahaa\nostaaksesi vokaalin.");
        tlo.disableAllButtons();
        refresh();
        return true;
    }
    
    // alustava
    public boolean buyNoun() {
        String guessed = glo.getFieldText();
        if (guessed.length() != 1) {
            return false;
        }
        int letters = game.buyNoun(guessed.toUpperCase().charAt(0));
        if (letters >= 0) {
            if (letters == 0) {
                tlo.setLatestEvent(game.getPlayerInTurn().getName(), "ostit vokaalin " + guessed.toUpperCase() + ", mutta sitä löytynyt\nyhtäkään kappaletta.");
            } else {
                tlo.setLatestEvent(game.getPlayerInTurn().getName(), "ostit vokaalin " + guessed.toUpperCase() + ", ja niitä löytyi yhteensä " + letters + ".");
            }
            newTurn();
            refresh();
            return true;
        }
        return false;
    }
    
    // tätäkin vois vähän pohtia tarkemmin
    public boolean setGuessThePhrase() {
        glo.setGuessThePhrase();
        tlo.setLatestEvent(game.getPlayerInTurn().getName(), "haluat yrittää ratkaista tehtävän...");
        tlo.disableAllButtons();
        refresh();
        return true;
    }
    
    public boolean guessThePhrase() {
        String guess = glo.getFieldText();
        String playerGuessing = game.getPlayerInTurn().getName();
        if (game.tryToGuessPhrase(guess)) {
            setGameOver();
            return true;
        }
        tlo.setLatestEvent(playerGuessing, "arvasi ratkaisuksi \"" + guess.toUpperCase() + "\",\nmutta se oli väärin.");
        newTurn();
        refresh();
        return false;
    }
    
    // alustava
    public void setMessage(String message) {
        layout.getChildren().add(new Label(message));
    }
    
    private void setGameOver() {
        winningMoney = game.declrareWinner();
        winnerName = game.getPlayerInTurn().getName();
        correctPhrase = game.getPhraseAsString();        
    }
    
    public String getWinnerName() {
        return winnerName;
    }
    
    public String getCorrectPhrase() {
        return correctPhrase;
    }
    
    public int getWinningMoney() {
        return winningMoney;
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
    
    @Override
    public void refresh() {
        subLO1 = new HBox();
        subLO1.setSpacing(40);
        subLO1.getChildren().addAll(phlo.getLayout(), wlo.getLayout());
        
        subLO2 = new HBox();
        subLO2.setSpacing(40);
        subLO2.getChildren().addAll(tlo.getLayout(), glo.getLayout());
        
        layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().addAll( pllo.getLayout(), subLO1, subLO2);
        
        scene = new Scene(layout, 800, 500);
    }

    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
}
