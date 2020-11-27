
package wheeloffortune.dao;

import java.util.List;
import wheeloffortune.domain.Player;

public interface PlayerDao {
    
    Player create(Player player) throws Exception;
    
    Player findByName(String name);
    
    List<Player> getAll();
    
}
