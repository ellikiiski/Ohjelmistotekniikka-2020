
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wheelof4tune.Player;

/**
 *
 * @author kiisk
 */
public class PlayerTest {
    
    Player player;
    
    @Before
    public void setUp() {
        player = new Player("tester");
    }
    
    @Test
    public void PlayerToStringTest() {
        assertEquals("Pelaaja: tester, rahatilanne: 0€", player.toString());
    }
}
