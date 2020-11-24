
package wheeloffortune.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wheeloffortune.domain.Game;
import wheeloffortune.domain.Phrase;
import wheeloffortune.domain.Category;
import wheeloffortune.domain.Player;

public class GameTest {
    
    Game game;
    Phrase phrase;
    Player player1;
    Player player2;

    @Before
    public void setUp() {
        phrase = new Phrase("testaa pois", Category.COMMON);
        game = new Game(phrase);
        player1 = new Player("eka");
        player2 = new Player("toka");
        game.addPlayer(player1);
        game.addPlayer(player2);
    }

    @Test
    public void addPlayers() {
        assertEquals(game.getScore(), 0);
    }
    
    @Test
    public void firstTurn() {
        assertEquals(game.playerInTurn(), player1.getName());
    }

    @Test
    public void changeTurn() {
        game.nextPlayersTurn();
        assertEquals(game.playerInTurn(), player2.getName());
    }
    
    @Test
    public void tryToGuessThePhrase() {
        boolean wasRight = game.tryToGuessPhrase("testaa pois");
        assertEquals(wasRight, true);
    }
}
