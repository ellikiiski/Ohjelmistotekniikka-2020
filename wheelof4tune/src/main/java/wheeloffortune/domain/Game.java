
package wheeloffortune.domain;

import java.util.ArrayList;
import java.util.HashMap;
import wheeloffortune.dao.PhraseDao;
import wheeloffortune.dao.PlayerDao;

public class Game {
    
    public PlayerDao playerDao;
    public PhraseDao phraseDao;
    
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
    
    public Game(PlayerDao playerDao, PhraseDao phraseDao) {
        
        this.playerDao = playerDao;
        this.phraseDao = phraseDao;
        
        this.score = new HashMap<>();
        this.turnTracker = new ArrayList<>();
        this.turnIndex = 0;
        this.playerInTurn = null;
        this.wheel = new Wheel(800);
        this.phrase = this.phraseDao.getRandomPhrase();
        this.guessed = new ArrayList<>();
        this.revealed = this.hideLetters();
        this.latestSpin = null;
        this.isOver = false;
    }
    
    // fraasien lisäys tietokantaan
    
    public boolean addPhrase(String phrase, String categoryName) {
        // tiedän tää on tyhmää toistoa joka paikassa muuttaa categoryja stringeiksi ja toisinpäin
        // koitan keksiä järkevämmän toteutuksen ensi viikolla
        try {
            Category c = Category.getCategory(categoryName);
            Phrase p = new Phrase(phrase, c, 0);
            phraseDao.create(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // pelaajien lisäys
    
    public boolean addPlayer(String name) {
        Player newPlayer = playerDao.findByName(name);
        if (newPlayer == null) {
            newPlayer = new Player(name, 0);
            try {
                playerDao.create(newPlayer);
            } catch (Exception e) {
                return false;
            }            
        }
        score.put(newPlayer, 0);
        turnTracker.add(newPlayer);
        playerInTurn = turnTracker.get(0);
        return true;
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
        try {
            playerDao.addMoney(playerInTurn, score.get(playerInTurn));
        } catch (Exception ex) {
            return -666;
        }
        return score.get(playerInTurn);
    }
    
    // gettereitä
    
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
