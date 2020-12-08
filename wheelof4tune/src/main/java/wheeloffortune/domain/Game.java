
package wheeloffortune.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import wheeloffortune.dao.PhraseDao;
import wheeloffortune.dao.PlayerDao;

public class Game {
    
    private PlayerDBhandler plDBh;
    private PhraseDBhandler phDBh;
    
    private HashMap<Player, Integer> score;
    private ArrayList<Player> turnTracker;
    private int turnIndex;
    private Player playerInTurn;
    private final Wheel wheel;
    private final Phrase phrase;
    private ArrayList<Character> guessed;
    private char[] revealed;
    private Sector latestSpin;
    private boolean isOver;
    
    public Game(PlayerDBhandler playerDBh, PhraseDBhandler phraseDBh) {
        
        this.plDBh  = playerDBh;
        this.phDBh = phraseDBh;
        
        this.score = new HashMap<>();
        this.turnTracker = new ArrayList<>();
        this.turnIndex = 0;
        this.playerInTurn = null;
        this.wheel = new Wheel(800);
        this.phrase = this.phDBh.getPhrase();
        this.guessed = new ArrayList<>();
        this.revealed = this.hideLetters();
        this.latestSpin = null;
        this.isOver = false;
    }
    
    // fraasien lisäys tietokantaan
    public boolean addPhrase(String phrase, String categoryName) {
        return phDBh.addPhrase(phrase, categoryName);
    }
    
    // pelaajien lisäys
    
    public boolean addPlayer(String name) {
        Player newPlayer = plDBh.addPlayer(name);
        if (newPlayer != null) {
            score.put(newPlayer, 0);
            turnTracker.add(newPlayer);
            playerInTurn = turnTracker.get(0);
            return true;
        }        
        return false;
    }
    
    // onnenpyörän pyörittäminen ja sen apumetodit
    
    public void spinWheel() {
        latestSpin = wheel.spin();
        if (this.latestSpinIsBankcrupt() || this.latestSpinIsSkip()) {
            this.nextPlayersTurn();
        }
    }
    
    public boolean latestSpinIsBankcrupt() {
        return latestSpin.getSectorType() == SectorType.BANKCRUPT;
    }

    public boolean latestSpinIsSkip() {
        return latestSpin.getSectorType() == SectorType.SKIP;
    }
    
    public void nextPlayersTurn() {
        turnIndex = (turnIndex + 1) % turnTracker.size();
        playerInTurn = turnTracker.get(turnIndex);
    }
    
    // pelaajan peliliikkeet ja niiden apumetodit
    
    public boolean tryToGuessPhrase(String guess) {
        if (guess.toUpperCase().equals(phrase.getPhrase())) {
            for (int i = 0; i < revealed.length; i++) {
                revealed[i] = phrase.getLetters()[i];
            }
            isOver = true;
            return true;
        }
        nextPlayersTurn();
        return false;
    }
    
    public int guessConsonant(char consonant) {
        int guessedConsonants = revealLetter(consonant);
        this.addScore(guessedConsonants);
        if (guessedConsonants == 0) {
            this.nextPlayersTurn();
        }
        return guessedConsonants;
    }
    
    public boolean canBuyNoun() {
        return score.get(playerInTurn) >= 250;
    }
    
    public int buyNoun(char noun) {
        score.put(playerInTurn, score.get(playerInTurn) - 250);
        return revealLetter(noun);
    }
    
    public int revealLetter(char letter) {
        int lettersFound = 0;
        for (int i = 0; i < phrase.getLetters().length; i++) {
            if (phrase.getLetters()[i] == letter) {
                revealed[i] = letter;
                lettersFound++;
            }
        }
        guessed.add(letter);
        return lettersFound;
    }
    
    // pisteiden lisäys ja nollaus
    
    public void addScore(int x) {
        score.put(playerInTurn, score.get(playerInTurn) + x * latestSpin.getValue());
    }

    public void resetScore() {
        score.put(playerInTurn, 0);
    }
    
    // pelin päättyminen
    
    public int declrareWinner() {
        plDBh.addMoneyToPlayer(playerInTurn, score.get(playerInTurn));
        return score.get(playerInTurn);
    }
    
    // gettereitä
    
    public Set<Player> getPlayerList() {
        return score.keySet();
    }
    
    public String playerInTurn() {
        if (playerInTurn == null) {
            System.out.println("Pelistä puuttuu pelaajat!");
        }
        return playerInTurn.getName();
    }
    
    public int getScore() {
        return score.get(playerInTurn);
    }
    
    public boolean isOver() {
        return isOver;
    }
    
    public String getPhraseAsString() {
        return this.letterArrayToString();
    }

    public String getCategory() {
        return phrase.getCategoryString();
    }

    public String getLatestSpinSectorName() {
        return latestSpin.toString();
    }
    
    // private apumetodeja
    
    private char[] hideLetters() {
        char[] allHidden = new char[phrase.getLetters().length];
        for (int i = 0; i < phrase.getLetters().length; i++) {
            if (phrase.getLetters()[i] == ' ') {
                allHidden[i] = ' ';
            } else {
                allHidden[i] = '_';
            }
        }
        return allHidden;
    }
    
    private String letterArrayToString() {
        StringBuilder s = new StringBuilder("");
        for (char letter : revealed) {
            s.append(letter);
        }
        return s.toString();
    }
    
}
