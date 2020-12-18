
package wheeloffortune.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
    
    public Game(PlayerDBhandler playerDBh, PhraseDBhandler phraseDBh) {
        
        plDBh  = playerDBh;
        phDBh = phraseDBh;
        
        score = new HashMap<>();
        turnTracker = new ArrayList<>();
        turnIndex = 0;
        playerInTurn = null;
        wheel = new Wheel(800);
        phrase = phDBh.getPhrase();
        guessed = new ArrayList<>();
        revealed = hideLetters();
        latestSpin = null;
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
        if (latestSpinIsBankcrupt()) {
            resetScore();
            nextPlayersTurn();
        } else if (latestSpinIsSkip()) {
            nextPlayersTurn();
        }
    }
    
    public boolean latestSpinIsBankcrupt() {
        return latestSpin.getSectorType() == SectorType.BANKCRUPT;
    }

    public boolean latestSpinIsSkip() {
        return latestSpin.getSectorType() == SectorType.SKIP;
    }
    
    private void nextPlayersTurn() {
        turnIndex = (turnIndex + 1) % turnTracker.size();
        playerInTurn = turnTracker.get(turnIndex);
    }
    
    // pelaajan peliliikkeet ja niiden apumetodit
    
    public boolean tryToGuessPhrase(String guess) {
        if (guess.toUpperCase().equals(phrase.getPhrase())) {
            System.out.println("meni oikein");
            System.out.println(getPhraseAsString());
            revealAll();
            System.out.println(getPhraseAsString());
            return true;
        }
        System.out.println("väärin meni");
        nextPlayersTurn();
        return false;
    }
    
    public int guessConsonant(char consonant) {
        if (!isConsonant(consonant)) {
            return -666;
        }
        int guessedConsonants = revealLetter(consonant);
        addScore(guessedConsonants);
        if (guessedConsonants == 0) {
            nextPlayersTurn();
        }
        return guessedConsonants;
    }
    
    public boolean canBuyNoun() {
        System.out.println(score.get(playerInTurn));
        return score.get(playerInTurn) >= 250;
    }
    
    public int buyNoun(char noun) {
        if (!isNoun(noun)) {
            return -666;
        }
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
    
    public void revealAll() {
        for (int i = 0; i < revealed.length; i++) {
            revealed[i] = phrase.getLetters()[i];
        }
    }
    
    // pisteiden lisäys ja nollaus
    
    private void addScore(int x) {
        score.put(playerInTurn, score.get(playerInTurn) + x * latestSpin.getValue());
    }

    private void resetScore() {
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
    
    public Player getPlayerInTurn() {
        return playerInTurn;
    }
    
    public int getScore() {
        return score.get(playerInTurn);
    }
    
    public String getPhraseAsString() {
        return letterArrayToString();
    }

    public String getCategory() {
        return phrase.getCategoryString();
    }

    public String getLatestSpinSectorName() {
        return latestSpin.toString();
    }
    
    // apumetodeja
    
    private boolean isConsonant(char c) {
        return c == 'B' || c == 'C' || c == 'D' || c == 'F' || c == 'G' || c == 'H'
                || c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'P' || c == 'Q'
                || c == 'R' || c == 'S' || c == 'T' || c == 'V' || c == 'W' || c == 'X' || c == 'Z'; 
    }
    
    private boolean isNoun(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y' || c == 'Å' || c == 'Ä' || c == 'Ö';
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
