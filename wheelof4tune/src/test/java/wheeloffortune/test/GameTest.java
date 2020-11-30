
package wheeloffortune.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.domain.Game;
import wheeloffortune.domain.Phrase;
import wheeloffortune.domain.Category;
import wheeloffortune.domain.Player;

public class GameTest {
    
    FilePlayerDao plDao;
    FilePhraseDao phDao;
    
    Game game;
    Phrase phrase;
    String player1name;
    String player2name;

    @Before
    public void setUp() {
        plDao = new FilePlayerDao("testPlayerDB.txt");
        phDao = new FilePhraseDao("testPhraseDB.txt");
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
    
    /*@Test
    public void tryToGuessThePhrase() {
        boolean wasRight = game.tryToGuessPhrase("testaa pois");
        assertEquals(wasRight, true);
    }*/
}
