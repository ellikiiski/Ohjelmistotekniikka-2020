
package wheeloffortune.dao;

import wheeloffortune.gamelogic.Phrase;
import wheeloffortune.gamelogic.Category;

public class PhraseDBhandler {
    
    private final FilePhraseDao phDao;
    
    public PhraseDBhandler(String fileName) {
        phDao = new FilePhraseDao(fileName);
    }

    public boolean addPhrase(String phrase, String categoryName) {
        try {
            Category c = Category.getCategory(categoryName);
            Phrase p = new Phrase(phrase, c, 0);
            phDao.create(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    //// palauttaa satunnaisen fraasin tietokannasta
    public Phrase getPhrase() {
        return phDao.getRandomPhrase();
    }
    
}
