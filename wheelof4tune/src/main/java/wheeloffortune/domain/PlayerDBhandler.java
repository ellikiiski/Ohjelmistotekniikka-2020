
package wheeloffortune.domain;

import wheeloffortune.dao.FilePlayerDao;

public class PlayerDBhandler implements DBhandler {
    
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
    
    public boolean addMoneyToPlayer(Player player, int money) {
        try {
            plDao.addMoney(player, money);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
