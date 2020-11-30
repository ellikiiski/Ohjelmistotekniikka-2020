
package wheeloffortune.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import wheeloffortune.domain.Category;
import wheeloffortune.domain.Phrase;

public class PhraseTest {
    
    Phrase phrase;

    @Before
    public void setUp() {
        phrase = new Phrase("testaa pois", Category.COMMON, 0);
    }

    @Test
    public void PhraseInitilizing() {
        assertEquals(phrase.getLetters().length, 11);
    }

    @Test
    public void backToString() {
        assertEquals("TESTAA POIS", phrase.getPhrase());
    }
    
    @Test
    public void category() {
        assertEquals("YLEISTIETO", phrase.getCategoryString());
    }
    
    @Test
    public void addingUse() {
        phrase.addUse();
        assertEquals(phrase.getUses(), 1);
    }
    
}
