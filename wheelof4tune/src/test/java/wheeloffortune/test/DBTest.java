
package wheeloffortune.test;

import org.junit.Before;
import org.junit.Test;
import wheeloffortune.domain.PhraseDBhandler;
import wheeloffortune.domain.PlayerDBhandler;
import wheeloffortune.gamelogic.Game;

public class DBTest {
    
    PlayerDBhandler plDBh;
    PhraseDBhandler phDBh;

    @Before
    public void setUp() {
        plDBh = new PlayerDBhandler("testPlayerDB.txt");
        phDBh = new PhraseDBhandler("testPhraseDB.txt");
        
    }
    
    /*@Test
    public void addNewPlayerToDB() {
        String player = "testipelle";
        plDBh.addPlayer(player);
        
    }*/
    
}
