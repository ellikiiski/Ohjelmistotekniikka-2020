
package wheeloffortune.dao;

import java.util.ArrayList;
import wheeloffortune.gamelogic.Phrase;

public interface PhraseDao {
    
    Phrase create(Phrase phrase) throws Exception;
    
    Phrase getRandomPhrase();

    ArrayList<Phrase> getAll();
    
}
