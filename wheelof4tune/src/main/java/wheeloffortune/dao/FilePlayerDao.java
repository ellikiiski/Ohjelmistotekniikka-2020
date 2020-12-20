
package wheeloffortune.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import wheeloffortune.gamelogic.Player;

public class FilePlayerDao implements PlayerDao {
    
    private ArrayList<Player> players;
    private String file;

    public FilePlayerDao(String fileName) {
        players = new ArrayList<>();
        file = fileName;
        try {
            initPlayers();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Player create(Player player) throws Exception {
        players.add(player);
        save();
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
        save();
        return p.getBank();
    }

    @Override
    public List<Player> getAll() {
        return players;
    }
    
    //// apumetodi pelaajalistan luomiseen
    private void initPlayers() throws Exception {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split("\t");
                Player p = new Player(parts[0], Integer.valueOf(parts[1]));
                players.add(p);
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
    }
    
    //// tallentaa pelaajan tiedot tiedostoon
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player player : players) {
                writer.write(player.getName() + "\t" + player.getBank() + "\n");
            }
        }
    }
}
