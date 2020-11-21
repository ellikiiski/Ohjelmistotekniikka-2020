
package wheelof4tune;

public class Phrase {
    
    private char[] letters;
    private Category category;
    
    public Phrase(String phrase, Category category) {
        this.initLetterArray(phrase);
        this.category = category;
    }
    
    public char[] getLetters() {
        return letters;
    }
    
    public String getCategory() {
        if (category == Category.COMMON) {
            return "YLEISTIETO";
        } else if (category == Category.SCIENCE) {
            return "TIEDE";
        } else if (category == Category.CULTURE) {
            return "KULTTUURI";
        }
        return "Joku muu ???";
    }
    
    private void initLetterArray(String phrase) {
        String trimmed = phrase.trim().toUpperCase();
        this.letters = new char[trimmed.length()];
        for (int i = 0; i < trimmed.length(); i++) {
            letters[i] = trimmed.charAt(i);
        }
    }
}

enum Category {
    COMMON,
    SCIENCE,
    CULTURE
}
