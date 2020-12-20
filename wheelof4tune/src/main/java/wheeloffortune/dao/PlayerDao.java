
package wheeloffortune.dao;

import java.util.List;
import wheeloffortune.gamelogic.Player;

public interface PlayerDao {
    
    Player create(Player player) throws Exception;
    
    Player findByName(String name);
    
    int addMoney(Player player, int euros) throws Exception;
    
    List<Player> getAll();
    
}
