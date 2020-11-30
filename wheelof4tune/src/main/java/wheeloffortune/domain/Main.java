
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.dao.PlayerDao;
import wheeloffortune.ui.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        // päästään vähän pelaulun alkuun jo
        
        FilePlayerDao plDao = new FilePlayerDao("playerDB.txt");
        FilePhraseDao phDao = new FilePhraseDao("phraseDB.txt");
        
        Game game = new Game(plDao, phDao);
        
        TextUI ui = new TextUI(game);
        ui.startGame();
    }
    
}
