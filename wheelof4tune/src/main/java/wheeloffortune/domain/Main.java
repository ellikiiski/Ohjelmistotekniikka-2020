
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.dao.PlayerDao;
import wheeloffortune.ui.GUI;
import wheeloffortune.ui.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        // Tiedän, että tää on just nyt vähän levällään koko projekti,
        // kun yrityän siirtyä graafiseen käyttöliittymään.
        
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(GUI.class);
            }
        }.start();
        
        /*PlayerDBhandler plDao = new PlayerDBhandler("playerDB.txt");
        PhraseDBhandler phDao = new PhraseDBhandler("phraseDB.txt");
        
        Game game = new Game(plDao, phDao);
        
        TextUI ui = new TextUI(game);
        ui.startWheelOfFortune();*/
    }
    
}
