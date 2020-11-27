
package wheeloffortune.domain;

import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.dao.PlayerDao;
import wheeloffortune.ui.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        // päästään vähän pelaulun alkuun jo
        
        FilePlayerDao pDao = new FilePlayerDao("playerDB.txt");
        
        Phrase guessThis = new Phrase("Elli on intellektuelli", Category.COMMON);
        Game game = new Game(guessThis, pDao);
        
        TextUI ui = new TextUI(game);
        ui.startGame();
    }
    
}
