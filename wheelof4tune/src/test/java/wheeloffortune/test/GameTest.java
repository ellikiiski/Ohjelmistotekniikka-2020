
package wheeloffortune.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wheeloffortune.gamelogic.Game;
import wheeloffortune.gamelogic.Phrase;
import wheeloffortune.domain.PhraseDBhandler;
import wheeloffortune.domain.PlayerDBhandler;

public class GameTest {
    
    PlayerDBhandler plDBh;
    PhraseDBhandler phDBh;
    
    Game game;
    Phrase phrase;
    String player1name;
    String player2name;

    @Before
    public void setUp() {
        plDBh = new PlayerDBhandler("testPlayerDB.txt");
        phDBh = new PhraseDBhandler("testPhraseDB.txt");
        game = new Game(plDBh, phDBh);
        player1name = "eka";
        player2name = "toka";
        game.addPlayer(player1name);
        game.addPlayer(player2name);
    }

    @Test
    public void addPlayers() {
        assertEquals(game.getScore(), 0);
    }
    
    @Test
    public void firstTurn() {
        assertEquals(game.playerInTurn(), "eka");
    }

    @Test
    public void changeTurn() {
        game.nextPlayersTurn();
        assertEquals(game.playerInTurn(), "toka");
    }
    
    @Test
    public void spinningWheel() {
        game.spinWheel();
        assertFalse(game.getLatestSpinSectorName().equals("Ei mikään"));
    }
    
    @Test
    public void tryToGuessThePhrase() {
        assertTrue(game.tryToGuessPhrase("testaaminen on rasittavaa"));
    }
    
    @Test
    public void guessingConsonant() {
        game.spinWheel();
        int consonants = game.guessConsonant('T');
        assertEquals(consonants, 4);
    }
    
    @Test
    public void cantBuyNoun() {
        assertFalse(game.canBuyNoun());
    }
    
    @Test
    public void hidinPhrase() {
        assertEquals(game.getPhraseAsString(), "___________ __ __________");
    }
    
    @Test
    public void revealingLetter() {
        game.revealLetter('A');
        assertEquals(game.getPhraseAsString(), "____AA_____ __ _A____A_AA");
    }
}
