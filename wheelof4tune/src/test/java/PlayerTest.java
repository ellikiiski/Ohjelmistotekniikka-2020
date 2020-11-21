
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wheelof4tune.Player;

public class PlayerTest {
    
    Player player;
    
    @Before
    public void setUp() {
        player = new Player("tester");
    }
    
    @Test
    public void NewPlayerCreated() {
        assertEquals("Pelaaja: tester, rahaa pankissa: 0€", player.toString());
    }
    
    @Test
    public void moneyAddedToBank() {
        player.addToBank(10);
        assertEquals("Pelaaja: tester, rahaa pankissa: 10€", player.toString());
    }
}
