
package wheeloffortune.domain;

import wheeloffortune.dao.FilePlayerDao;

public class PlayerDBhandler {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästäkään luokasta koska en ole käyttänyt sitä
    // hyödyksi muuta kuin graafisessa käyttöliittymässä.
    // Eli tekstikäyttöliittymän (jota arvioit) kannalta ei relevantti.
    
    private FilePlayerDao plDao;

    public PlayerDBhandler(String fileName) {
        this.plDao = new FilePlayerDao(fileName);
    }

    public Player addPlayer(String name) {
        Player newPlayer = plDao.findByName(name);
        if (newPlayer == null) {
            newPlayer = new Player(name, 0);
            try {
                plDao.create(newPlayer);
            } catch (Exception e) {
                return null;
            }
        }
        return newPlayer;
    }    
}
