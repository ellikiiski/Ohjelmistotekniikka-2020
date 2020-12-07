
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;

public class PhraseDBhandler {
    
    // KOODIKATSELMOIJA!!
    // Nämä DBhandler-luokat on ilmesynyt releasen jälkeen,
    // mutta niiden ei pitäis olla muuttanut mitään toiminnallisuuksia
    
    private FilePhraseDao phDao;
    
    public PhraseDBhandler(String fileName) {
        this.phDao = new FilePhraseDao(fileName);
    }

    public boolean addPhrase(String phrase, String categoryName) {
        try {
            Category c = Category.getCategory(categoryName);
            Phrase p = new Phrase(phrase, c, 0);
            phDao.create(p);
            System.out.println(p.getPhrase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Phrase getPhrase() {
        return phDao.getRandomPhrase();
    }
    
}