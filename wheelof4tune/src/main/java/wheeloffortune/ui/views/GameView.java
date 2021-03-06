
package wheeloffortune.ui.views;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import wheeloffortune.gamelogic.Game;
import wheeloffortune.dao.PhraseDBhandler;
import wheeloffortune.gamelogic.Player;
import wheeloffortune.dao.PlayerDBhandler;
import wheeloffortune.ui.layouts.ErrorMessageLayout;
import wheeloffortune.ui.layouts.GuessLayout;
import wheeloffortune.ui.layouts.PhraseLayout;
import wheeloffortune.ui.layouts.PlayersLayout;
import wheeloffortune.ui.layouts.TurnLayout;
import wheeloffortune.ui.layouts.WheelLayout;

public class GameView implements View {
    
    private Game game;
    private String winnerName;
    private String correctPhrase;
    private int winningMoney;    
    private PlayersLayout pllo;
    private PhraseLayout phlo;
    private final WheelLayout wlo;
    private final TurnLayout tlo;
    private final GuessLayout glo;
    private final ErrorMessageLayout emlo;    
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
        emlo = new ErrorMessageLayout();        
        layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().add(new Label("Odotetaan pelin latautumista..."));        
        scene = new Scene(layout, 800, 500);
    }
    
    //// alustaa pelinäkymän luomalla uuden peliolion parametrien avulla
    public void setNewGame(PlayerDBhandler plDBh, PhraseDBhandler phDBh, String[] players) {
        game = new Game(plDBh, phDBh);        
        for (String p : players) {
            game.addPlayer(p);
        }        
        pllo = initPllo();
        phlo = new PhraseLayout(game.getPhraseAsStringToPresent(), game.getCategoryName());        
        glo.setToInit();
        wlo.setToInit();
        tlo.seToInit();
        tlo.setPlayerInTurn(game.getPlayerInTurn().getName());        
        refresh();
    }
    
    //// apumetodi pelaajanäkymän alustamiseen
    private PlayersLayout initPllo() {
        ArrayList<Player> helpList = new ArrayList<>();        
        for (Player player : game.getPlayerList()) {
            helpList.add(player);
        }        
        return new PlayersLayout(helpList.get(0), helpList.get(1), helpList.get(2));
    }
    
    // PELAAMINEN
    
    /// Onnenpyörän pyörittäminen
    
    //// arpoo uuden sektorin
    //// sekä vaihtaa asettelujen tilat vastaamaan pyöräytettyä sektoria
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
    
    //// vaihtaa asettelut vuoron aloittamista vastaavaan tilaan
    public void newTurn() {
        emlo.clear();
        phlo.setPhrase(game.getPhraseAsStringToPresent());
        pllo.addMoneyToScore(game.getPlayerInTurn(), game.getScore());
        glo.setToInit();
        tlo.setPlayerInTurn(game.getPlayerInTurn().getName());
        tlo.enalbleSpinButton();
        tlo.enableGuessThePhraseButton();
        if (game.canBuyNoun()) {
            tlo.enabeBuyNounButton();
        }
    }
    
    //// välittää käyttäjän arvaaman konsonantin pelilogiikalle ja riippuen syötteestä antaa virheilmoituksen tai edistää peliä
    public void guessConsonant() {
        String guessed = glo.getFieldText();
        String playerGuessing = game.getPlayerInTurn().getName();
        if (guessed.length() != 1) {
            emlo.setNewErrorMessage("Syötä tasan yksi kirjain!");
        } else {
            int letters = game.guessConsonant(guessed.toUpperCase().charAt(0));
            if (letters >= 0) {
                if (letters == 0) {
                    tlo.setLatestEvent(playerGuessing, "arvasi konsonanttia " + guessed.toUpperCase() + ", eikä sitä löytynyt\nyhtäkään kappaletta. Vuoro vaihtui.");
                } else {
                    tlo.setLatestEvent(playerGuessing, "arvasit konsonanttia " + guessed.toUpperCase() + ", joita löytyi yhteensä " + letters + ".");
                }
                newTurn();
                refresh();
            } else if (letters == -888) {
                emlo.setNewErrorMessage("Konsonantti " + guessed.toUpperCase() + " on jo arvattu!");
            } else {
                emlo.setNewErrorMessage("Arvauksesi ei ollut konsonantti!");
            }
        }
    }
    
    //// vaihtaa asettelujen tilat vokaalin ostamista varten
    public void setBuyNoun() {
        glo.setBuyNoun();
        tlo.setLatestEvent(game.getPlayerInTurn().getName(), "sinulla on pankissa tarpeeksi rahaa\nostaaksesi vokaalin.");
        tlo.disableAllButtons();
        refresh();
    }
    
    //// välittää käyttäjän arvaaman vokaalin pelilogiikalle ja riippuen syötteestä antaa virheilmoituksen tai edistää peliä
    public void buyNoun() {
        String guessed = glo.getFieldText();
        if (guessed.length() != 1) {
            emlo.setNewErrorMessage("Syötä tasan yksi kirjain!");
        } else {
            int letters = game.buyNoun(guessed.toUpperCase().charAt(0));
            if (letters >= 0) {
                if (letters == 0) {
                    tlo.setLatestEvent(game.getPlayerInTurn().getName(), "ostit vokaalin " + guessed.toUpperCase() + ", mutta sitä löytynyt\nyhtäkään kappaletta.");
                } else {
                    tlo.setLatestEvent(game.getPlayerInTurn().getName(), "ostit vokaalin " + guessed.toUpperCase() + ", ja niitä löytyi yhteensä " + letters + ".");
                }
                newTurn();
                refresh();
            } else if (letters == -888) {
                emlo.setNewErrorMessage("Vokaali " + guessed.toUpperCase() + " on jo ostettu!");
            } else {
                emlo.setNewErrorMessage("Arvauksesi ei ollut vokaali!");
            }
        }
    }
    
    //// vaihtaa asettelujen tilat tehtävän ratkaisemista varten
    public void setGuessThePhrase() {
        glo.setGuessThePhrase();
        tlo.setLatestEvent(game.getPlayerInTurn().getName(), "haluat yrittää ratkaista tehtävän...");
        tlo.disableAllButtons();
        refresh();
    }
    
    //// välittää käyttäjän arvaaman merkkijonon pelilogiikalle
    //// jos arvaus oli oikea, palauttaa true
    //// muuten palauttaa false ja vaihtaa vuoroa
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
    
    //// välittää pelilogiikalle tiedon, että peli on päättynyt
    //// ja tallentaa voiton tiedot muuttujiin
    private void setGameOver() {
        winningMoney = game.declrareWinner();
        winnerName = game.getPlayerInTurn().getName();
        correctPhrase = game.getPhraseAsString();        
    }
    
    /// Voittotietojen getterit
    
    public String getWinnerName() {
        return winnerName;
    }
    
    public String getCorrectPhrase() {
        return correctPhrase;
    }
    
    public int getWinningMoney() {
        return winningMoney;
    }
    
    /// Nappien getterit
    
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
    
    /// Rajapinnan metodit
    
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
        layout.getChildren().addAll( pllo.getLayout(), subLO1, subLO2, emlo.getLayout());        
        scene = new Scene(layout, 800, 500);
    }

    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
}
