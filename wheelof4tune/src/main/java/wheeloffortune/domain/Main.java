
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.dao.PlayerDao;
import wheeloffortune.ui.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        // peli pyörii vaikka käyttöliittymä on yhä vain tekstinen
        
        FilePlayerDao plDao = new FilePlayerDao("playerDB.txt");
        FilePhraseDao phDao = new FilePhraseDao("phraseDB.txt");
        
        Game game = new Game(plDao, phDao);
        
        TextUI ui = new TextUI(game);
        ui.startWheelOfFortune();
    }
    
}
