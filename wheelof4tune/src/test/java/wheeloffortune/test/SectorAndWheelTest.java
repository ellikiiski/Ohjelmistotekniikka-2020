
package wheeloffortune.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import wheeloffortune.gamelogic.Sector;
import wheeloffortune.gamelogic.SectorType;
import wheeloffortune.gamelogic.Wheel;

public class SectorAndWheelTest {
    
    private Wheel wheel;
    
    @Before
    public void setUp() {
        this.wheel = new Wheel(800);
    }
    
    @Test
    public void sectorValues() {
        boolean test = true;
        Sector[] s = wheel.getSectors();
        for (int i = 0; i < s.length; i++) {
            test = (s[i].getSectorType() == SectorType.MONEY && (s[i].getValue() > 0 && s[i].getValue() <= 800))
                    || (s[i].getSectorType() == SectorType.SKIP && (s[i].getValue() == 0))
                    || (s[i].getSectorType() == SectorType.BANKCRUPT && (s[i].getValue() < 0));
        }
        assertTrue(test);
    }
    
    @Test
    public void sectorToString() {
        boolean test = true;
        Sector[] s = wheel.getSectors();
        for (int i = 0; i < s.length; i++) {
            test = (s[i].getSectorType() == SectorType.MONEY && s[i].toString().contains("€"))
                    || (s[i].getSectorType() == SectorType.SKIP && s[i].toString().equals("OHI-SEKTORI"))
                    || (s[i].getSectorType() == SectorType.BANKCRUPT && s[i].toString().equals("ROSVO-SEKTORI"));
        }
        assertTrue(test);
    }

    @Test
    public void spinning() {
        assertFalse(wheel.spin() == null);
    }
    
}
