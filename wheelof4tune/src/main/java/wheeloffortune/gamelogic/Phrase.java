
package wheeloffortune.gamelogic;

public class Phrase {
    
    private final Category category;
    private char[] letters;
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
    
    public int getUses() {
        return used;
    }
    
    //// tekee annetusta merkkijonosta merkkitaulukon
    private void initLetterArray(String phrase) {
        String trimmed = phrase.trim().toUpperCase();
        this.letters = new char[trimmed.length()];
        for (int i = 0; i < trimmed.length(); i++) {
            letters[i] = trimmed.charAt(i);
        }
    }
}
