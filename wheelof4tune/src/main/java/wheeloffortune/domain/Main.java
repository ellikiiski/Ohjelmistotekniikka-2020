
package wheeloffortune.domain;

import wheeloffortune.dao.FilePhraseDao;
import wheeloffortune.dao.FilePlayerDao;
import wheeloffortune.dao.PlayerDao;
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
        
        FilePlayerDao plDao = new FilePlayerDao("playerDB.txt");
        FilePhraseDao phDao = new FilePhraseDao("phraseDB.txt");
        
        Game game = new Game(plDao, phDao);
        
        TextUI ui = new TextUI(game);
        ui.startWheelOfFortune();
    }
    
}
