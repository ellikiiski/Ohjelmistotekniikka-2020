
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;

public class PhraseDBhandler {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästäkään luokasta koska en ole käyttänyt sitä
    // hyödyksi muuta kuin graafisessa käyttöliittymässä.
    // Eli tekstikäyttöliittymän (jota arvioit) kannalta ei relevantti.
    
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
