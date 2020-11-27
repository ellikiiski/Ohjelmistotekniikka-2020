
package wheeloffortune.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import wheeloffortune.domain.Player;

public class FilePlayerDao implements PlayerDao {
    
    private ArrayList<Player> players;
    private String file;

    public FilePlayerDao(String fileName) {
        this.players = new ArrayList<>();
        this.file = fileName;
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
    public List<Player> getAll() {
        return players;
    }
    
    private void initPlayers() throws Exception {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split("\t");
                Player p = new Player(parts[0]);
                players.add(p);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Player player : players) {
                writer.write(player.getName() + "\t" + player.getBank() + "\n");
            }
        } 
    }    
}
