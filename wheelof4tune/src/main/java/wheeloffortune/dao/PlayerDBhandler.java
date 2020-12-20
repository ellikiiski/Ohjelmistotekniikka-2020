
package wheeloffortune.dao;

import wheeloffortune.gamelogic.Player;
import java.util.Collections;
import java.util.List;

public class PlayerDBhandler {
    
    private final FilePlayerDao plDao;

    public PlayerDBhandler(String fileName) {
        plDao = new FilePlayerDao(fileName);
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
    
    //// järjestää pelaajat pankissa olevan rahasumman mukaan parhausjärjestykseen
    public List<Player> getPlayersInOrder() {
        List<Player> players = plDao.getAll();
        Collections.sort(players, Collections.reverseOrder());
        return players;
    }
}
