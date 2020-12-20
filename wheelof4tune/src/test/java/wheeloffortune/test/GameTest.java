
package wheeloffortune.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wheeloffortune.gamelogic.Game;
import wheeloffortune.dao.PhraseDBhandler;
import wheeloffortune.dao.PlayerDBhandler;

public class GameTest {
    
    PlayerDBhandler plDBh;
    PhraseDBhandler phDBh;    
    Game game;
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
    
    /// Tehtävän ratkaisemisen testaus
    
    @Test
    public void tryToGuessThePhraseRight() {
        assertTrue(game.tryToGuessPhrase("testaaminen on rasittavaa"));
    }
    
    @Test
    public void tryToGuessThePhraseWrong() {
        assertFalse(game.tryToGuessPhrase("testaaminen on mun lepparijuttu"));
    }
    
    /// Konsonantin arvaamisen tehtaus
    
    @Test
    public void guessConsonantRight() {
        forceSpinMoney();
        int consonants = game.guessConsonant('T');
        assertEquals(consonants, 4);
    }
    
    @Test
    public void guessConsonantWrong() {
        forceSpinMoney();
        int consonants = game.guessConsonant('W');
        assertEquals(consonants, 0);
    }
    
    @Test
    public void tryToGuessNounAsConsonant() {
        forceSpinMoney();
        int consonants = game.guessConsonant('A');
        assertEquals(consonants, -666);
    }
    
    /// Vokaalin ostamisen testaus
    
    @Test
    public void tryToBuyNounWithoutEnooughMoney() {
        game.resetScore();
        assertEquals(game.buyNoun('A'), -1);
    }
    
    @Test
    public void buyNounRight() {
        forceSpinMoney();
        game.addScore(250);
        assertEquals(game.buyNoun('A'), 6);
    }
    
    @Test
    public void buyNounWrong() {
        forceSpinMoney();
        game.addScore(250);
        assertEquals(game.buyNoun('Å'), 0);
    }
    
    @Test
    public void tryToBuyConsonantAsNoun() {
        forceSpinMoney();
        game.addScore(250);
        assertEquals(game.buyNoun('T'), -666);
    }
    
    /// Pelin pääättymisen voittoon testaus
    
    @Test
    public void declareWinner() {
        forceSpinMoney();
        game.addScore(13);
        game.declrareWinner();
        assertTrue(game.getPlayerInTurn().getBank() % 13 == 0);
    }
    
    /// Apumetodien testausta
    
    @Test
    public void hidingPhrase() {
        assertEquals(game.getPhraseAsString(), "___________ __ __________");
    }
    
    @Test
    public void revealingLetter() {
        game.revealLetter('A');
        assertEquals(game.getPhraseAsString(), "____AA_____ __ _A____A_AA");
    }
    
    // APUMETODi TESTAUKSEEN (onnenpyörän pakottaminen tiettyyn sektoriin)
    
    private void forceSpinMoney() {
        boolean notMoneySpinned = true;
        while(notMoneySpinned) {
            game.spinWheel();
            notMoneySpinned = game.latestSpinIsBankcrupt() || game.latestSpinIsSkip();
        }
    }
}
