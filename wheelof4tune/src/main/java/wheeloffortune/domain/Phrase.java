
package wheeloffortune.domain;

public class Phrase {
    
    private char[] letters;
    private Category category;
    private int used;
    
    public Phrase(String phrase, Category category, int used) {
        this.initLetterArray(phrase);
        this.category = category;
        this.used = used;
    }
    
    public void addUse() {
        used++;
    }
    
    public char[] getLetters() {
        return letters;
    }
    
    public String getPhrase() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < letters.length; i++) {
            sb.append(letters[i]);
        }
        return sb.toString();
    }
    
    public Category getCategory() {
        return category;
    }
    
    public String getCategoryString() {
        if (category == Category.COMMON) {
            return "YLEISTIETO";
        } else if (category == Category.SCIENCE) {
            return "TIEDE";
        } else if (category == Category.CULTURE) {
            return "KULTTUURI";
        }
        return "Ei mikään";
    }
    
    public int getUses() {
        return used;
    }
    
    private void initLetterArray(String phrase) {
        String trimmed = phrase.trim().toUpperCase();
        this.letters = new char[trimmed.length()];
        for (int i = 0; i < trimmed.length(); i++) {
            letters[i] = trimmed.charAt(i);
        }
    }
}
