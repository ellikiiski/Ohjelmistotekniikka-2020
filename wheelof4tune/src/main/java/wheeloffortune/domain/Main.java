
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.dao.PlayerDao;
import wheeloffortune.ui.GUI;
import wheeloffortune.ui.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        // KOODIKATSELMOIJA!!
        // Jos sattumalta tässä vaiheessa lataat koodini,
        // niin graafisen käyttöliittymän kehitys on vasta alkuvaiheessa,
        // joten ei kannata välittää vielä lainkaan mistään muusta
        // wheeloffortune.ui -paketin alaisesta luokasta kuin TextUI.java.
        // Yritän muistaa olla pushaamaatt muutoksia tähän main luokkaan githubiin,
        // että pystyt vielä antaa palautetta tästä tekstikäyttöliittymästä.
        
        PlayerDBhandler plDao = new PlayerDBhandler("playerDB.txt");
        PhraseDBhandler phDao = new PhraseDBhandler("phraseDB.txt");
        
        Game game = new Game(plDao, phDao);
        
        TextUI ui = new TextUI(game);
        ui.startWheelOfFortune();
        
        /*new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(GUI.class);
            }
        }.start();*/
    }
    
}
