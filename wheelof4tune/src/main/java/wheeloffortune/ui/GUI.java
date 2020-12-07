
package wheeloffortune.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.domain.Game;

public class GUI extends Application {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästä tai mistään muustakaan graafiseen käyttöliittymään
    // liittyvästä luokasta, sillä ne ovat pahasti kesken!!
    // Jätä siis huomiotta kaikki tämän pakkauksen luokat paitsi TextUI.java.

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Noniin ja sit hommiin");
        
        FilePlayerDao plDao = new FilePlayerDao("playerDB.txt");
        FilePhraseDao phDao = new FilePhraseDao("phraseDB.txt");

        Game game = new Game(plDao, phDao);
        
    }
    
}
