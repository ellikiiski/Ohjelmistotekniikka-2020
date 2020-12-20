
package wheeloffortune.test;

import java.util.ArrayList;
import java.util.List;
import wheeloffortune.dao.PlayerDao;
import wheeloffortune.gamelogic.Player;

public class FakePlayerDao implements PlayerDao {
    
    // nää saattaa jäädä nyt turhaksi
    
    private ArrayList<Player> players;
    
    public FakePlayerDao() {
        this.players = new ArrayList<>();
        this.players.add(new Player("tester", 0));
    }

    @Override
    public Player create(Player player) throws Exception {
        players.add(player);
        return player;
    }

    @Override
    public Player findByName(String name) {
        return players.stream()
                .filter(u -> u.getName()
                .equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int addMoney(Player player, int euros) throws Exception {
        Player p = findByName(player.getName());
        p.addToBank(euros);
        return p.getBank();
    }

    @Override
    public List<Player> getAll() {
        return players;
    }
    
}
