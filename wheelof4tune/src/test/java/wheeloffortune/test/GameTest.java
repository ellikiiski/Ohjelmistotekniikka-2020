
package wheeloffortune.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wheeloffortune.domain.Game;
import wheeloffortune.domain.Phrase;

public class GameTest {
    
    FakePlayerDao plDao;
    FakePhraseDao phDao;
    
    Game game;
    Phrase phrase;
    String player1name;
    String player2name;

    @Before
    public void setUp() {
        plDao = new FakePlayerDao();
        phDao = new FakePhraseDao();
        game = new Game(plDao, phDao);
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
        assertTrue(game.tryToGuessPhrase("testaa pois"));
    }
}
