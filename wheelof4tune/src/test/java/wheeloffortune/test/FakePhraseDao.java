
package wheeloffortune.test;

import java.util.ArrayList;
import java.util.HashMap;
import wheeloffortune.dao.PhraseDao;
import wheeloffortune.gamelogic.Category;
import wheeloffortune.gamelogic.Phrase;

public class FakePhraseDao implements PhraseDao {
    
    // nää saattaa jäädä nyt turhaksi
    
    private ArrayList<Phrase> phrases;
    private HashMap<String, Category> categories;
    
    public FakePhraseDao() {
        this.phrases = new ArrayList<>();
        this.categories = new HashMap<>();
        initCategories();
        this.phrases.add(new Phrase("testaa pois", Category.COMMON, 0));
    }

    @Override
    public Phrase create(Phrase phrase) throws Exception {
        phrases.add(phrase);
        return phrase;
    }

    @Override
    public Phrase getRandomPhrase() {
        return phrases.get(0);
    }

    @Override
    public ArrayList<Phrase> getAllFromCategory(Category category) {
        return phrases;
    }

    @Override
    public ArrayList<Phrase> getAll() {
        return phrases;
    }
    
    private void initCategories() {
        // tämä toteutus ei oo ehkä kaikista paras kategorian tunnistamiseksi mutta yritän keksiä parempaa
        categories.put("YLEISTIETO", Category.COMMON);
        categories.put("TIEDE", Category.SCIENCE);
        categories.put("KULTTUURI", Category.CULTURE);
    }
    
}
