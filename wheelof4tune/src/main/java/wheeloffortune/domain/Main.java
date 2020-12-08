
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.dao.PlayerDao;
import wheeloffortune.ui.GUI;
import wheeloffortune.ui.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        /*PlayerDBhandler plDao = new PlayerDBhandler("playerDB.txt");
        PhraseDBhandler phDao = new PhraseDBhandler("phraseDB.txt");
        
        Game game = new Game(plDao, phDao);
        
        TextUI ui = new TextUI(game);
        ui.startWheelOfFortune();*/
        
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(GUI.class);
            }
        }.start();
    }
    
}
