
package wheeloffortune.dao;

import java.util.ArrayList;
import wheeloffortune.domain.Category;
import wheeloffortune.domain.Phrase;

public interface PhraseDao {
    
    Phrase create(Phrase phrase) throws Exception;
    
    Phrase getRandomPhrase();

    ArrayList<Phrase> getAllFromCategory(Category category);

    ArrayList<Phrase> getAll();
    
}
