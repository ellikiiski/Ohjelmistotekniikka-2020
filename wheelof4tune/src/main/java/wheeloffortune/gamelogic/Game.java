
package wheeloffortune.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import wheeloffortune.domain.PhraseDBhandler;
import wheeloffortune.domain.PlayerDBhandler;

public class Game {
    
    private final PlayerDBhandler plDBh;
    private final PhraseDBhandler phDBh;    
    private final HashMap<Player, Integer> score;
    private final ArrayList<Player> turnTracker;
    private int turnIndex;
    private Player playerInTurn;
    private final Wheel wheel;
    private final Phrase phrase;
    private final ArrayList<Character> guessed;
    private final char[] revealed;
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
    
    // PELAAJIEN LISÄYS
    
    public void addPlayer(String name) {
        Player newPlayer = plDBh.addPlayer(name);
        if (newPlayer != null) {
            score.put(newPlayer, 0);
            turnTracker.add(newPlayer);
            playerInTurn = turnTracker.get(0);
        }
    }
    
    // ONNENPYÖRÄ
    
    /// Pyörän pyörittäminen
    
    //// arpoo muuttujaan latestSpin uuden sektorin
    //// jos arvottu sektori on rosvo, vuorossa olevan pelaajan pisteet nollaantuvat ja vuoro vaihtuu
    //// jos arvottu sektori on ohi, vuoro vaihtuu    
    public void spinWheel() {
        latestSpin = wheel.spin();
        if (latestSpinIsBankcrupt()) {
            resetScore();
            nextPlayersTurn();
        } else if (latestSpinIsSkip()) {
            nextPlayersTurn();
        }
    }
    
    /// Apumetodeja
    
    public boolean latestSpinIsBankcrupt() {
        return latestSpin.getSectorType() == SectorType.BANKCRUPT;
    }

    public boolean latestSpinIsSkip() {
        return latestSpin.getSectorType() == SectorType.SKIP;
    }
    
    // VUORONVAIHTO
    
    //// vaihtaa vuorossa olevaa pelaajaa kuvaavan muuttujan playerInTurn arvon
    //// seuraavana listassa turnTracker olevaan pelaajaan    
    public void nextPlayersTurn() {
        turnIndex = (turnIndex + 1) % turnTracker.size();
        playerInTurn = turnTracker.get(turnIndex);
    }
    
    // PELILIIKKEET
    
    /// Tehtävän ratkaisun yrittäminen
    
    //// vertaa parametrina annettavaa merkkijonoa oikeaan ratkaisuun
    //// ja palauttaa totuustarvon sen mukaan olivatko ne samat    
    public boolean tryToGuessPhrase(String guess) {
        if (guess.toUpperCase().equals(phrase.getPhrase())) {
            revealAll();
            return true;
        }
        nextPlayersTurn();
        return false;
    }
    
    /// Konsonantin veikkaus
    
    //// tarkistaa onko parametrina annettu merkki konsonantti
    //// jos ei, palauttaa -666
    //// jos on, paljastaa kyseiset kirjaimet fraasista ja palauttaa niiden määrän arvattavana olevassa fraasissa    
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
    
    /// Vokaalin osto
    
    //// palauttaa totuusarvon sen mukaan, onko pelaajalla tarpeeksi rahaa ostaakseen vokaalin    
    public boolean canBuyNoun() {
        return score.get(playerInTurn) >= 250;
    }
    
    //// tarkastaa onko parametrina annettu merkki vokaali
    //// jos ei, palauttaa -666
    //// jos on, vähentää pelaajan pisteistä 250 sekä paljastaa kyseiset kirjaimet fraasista ja palauttaa niiden määrän arvattavana olevassa fraasissa    
    public int buyNoun(char noun) {
        if (! canBuyNoun()) {
            return -1;
        }
        if (!isNoun(noun)) {
            return -666;
        }
        score.put(playerInTurn, score.get(playerInTurn) - 250);
        return revealLetter(noun);
    }
    
    // PISTETILANTEEN MUUTOKSET
    
    /// Pisteiden lisääminen
    
    //// lisää vuorossa olevan pelaajan pisteisiin viimeisimpänä pyöräytetyn sektorin arvon
    //// kerrottuna parametrina annetulla luvulla    
    public void addScore(int x) {
        score.put(playerInTurn, score.get(playerInTurn) + x * latestSpin.getValue());
    }
    
    /// Pisteiden nollaus
    
    //// nollaa vuorossa olevan pelaaja pisteet
    public void resetScore() {
        score.put(playerInTurn, 0);
    }
    
    // PELIN PÄÄTTYMINEN
    
    //// päättää pelin: tallentaa voittajan keräämät pisteet (=rahasumman) pelaajan pankkiin tietokantaan
    //// ja palauttaa voittajan keräämät pisteet
    public int declrareWinner() {
        plDBh.addMoneyToPlayer(playerInTurn, score.get(playerInTurn));
        return score.get(playerInTurn);
    }
    
    // GETTEREITÄ
    
    public Set<Player> getPlayerList() {
        return score.keySet();
    }
    
    public Player getPlayerInTurn() {
        return playerInTurn;
    }
    
    //// palauttaa tällä hetkellä vuorossa olevan pelaajan pistetilanteen
    public int getScore() {
        return score.get(playerInTurn);
    }
    
    //// palauttaa fraasin merkkijonona, jossa vain välilyönnit ja tähän mennessä oikein arvatutu kirjaimet näkyvät
    //// muiden kirjainten tilalla on alaviiva
    public String getPhraseAsString() {
        return letterArrayToString();
    }
    
    //// palauttaa muuten samanlaisen merkkijonon kuin getPhraseAsString(),
    //// mutta jokaisen merkin välissä on ylimääräinen välilyönti
    //// (tämä helpottaa alaviivojen erottamista toisistaan käyttöliittymässä)
    public String getPhraseAsStringToPresent() {
        StringBuilder sb = new StringBuilder("");
        for (char c : revealed) {
            sb.append(c);
            sb.append(" ");
        }
        return sb.toString();
    }

    public String getCategoryName() {
        return phrase.getCategory().getName();
    }

    public String getLatestSpinSectorName() {
        return latestSpin.toString();
    }
    
    // APUMETODEJA
    
    //// paljastaa fraasista löytyvät parametrina annettua kirjainta vastaavat kirjaimet
    //// ja palauttaa niiden lukumäärän
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
    
    private boolean isConsonant(char c) {
        ArrayList<Character> consonants = new ArrayList<>(Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Z'));
        return consonants.contains(c);
    }
    
    private boolean isNoun(char c) {
        ArrayList<Character> nouns = new ArrayList<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'Y', 'Å', 'Ä', 'Ö'));
        return nouns.contains(c);
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
