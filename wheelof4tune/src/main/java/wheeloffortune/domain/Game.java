
package wheeloffortune.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    
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
    
    public Game(Phrase phrase) {
        this.score = new HashMap<>();
        this.turnTracker = new ArrayList<>();
        this.turnIndex = 0;
        this.playerInTurn = null;
        this.wheel = new Wheel(800);
        this.phrase = phrase;
        this.guessed = new ArrayList<>();
        this.revealed = this.hideLetters();
        this.latestSpin = null;
        this.isOver = false;
    }
    
    public void addPlayer(Player newPlayer) {
        score.put(newPlayer, 0);
        turnTracker.add(newPlayer);
        playerInTurn = turnTracker.get(0);
    }
    
    public String playerInTurn() {
        if (playerInTurn == null) {
            System.out.println("Pelistä puuttuu pelaajat!");
        }
        return playerInTurn.getName();
    }
    
    public void nextPlayersTurn() {
        turnIndex = (turnIndex + 1) % turnTracker.size();
        playerInTurn = turnTracker.get(turnIndex);
    }
    
    public boolean isOver() {
        return isOver;
    }
    
    public String getPhraseAsString() {
        return this.letterArrayToString();
    }
    
    public String getCategory() {
        return phrase.getCategory();
    }
    
    public String getLatestSpinSectorName() {
        return latestSpin.toString();
    }
    
    public boolean latestSpinIsBankcrupt() {
        return latestSpin.getCategory() == SectorType.BANKCRUPT;
    }
    
    public boolean latestSpinIsSkip() {
        return latestSpin.getCategory() == SectorType.SKIP;
    }
    
    public boolean canBuyNoun() {
        return score.get(playerInTurn) >= 250;
    }
    
    public int buyNoun(char noun) {
        score.put(playerInTurn, score.get(playerInTurn) - 250);
        return revealLetter(noun);
    }
    
    public int guessConsonant(char consonant) {
        int guessedConsonants = revealLetter(consonant);
        this.addScore(guessedConsonants);
        if (guessedConsonants == 0) {
            this.nextPlayersTurn();
        }
        return guessedConsonants;
    }
    
    public void spinWheel() {
        latestSpin = wheel.spin();
        if (this.latestSpinIsBankcrupt() || this.latestSpinIsSkip()) {
            this.nextPlayersTurn();
        }
    }
    
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
    
    public int declrareWinner() {
        playerInTurn.addToBank(score.get(playerInTurn));
        return score.get(playerInTurn);
    }
    
    public void addScore(int x) {
        score.put(playerInTurn, score.get(playerInTurn) + x * latestSpin.getValue());
    }
    
    public void resetScore() {
        score.put(playerInTurn, 0);
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
