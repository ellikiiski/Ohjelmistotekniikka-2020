
package wheelof4tune;

import java.util.ArrayList;

public class Game {
    
    private ArrayList<Player> players;
    private int playerInTurn;
    private Wheel wheel;
    
    public Game() {
        this.players = new ArrayList<>();
        this.playerInTurn = 0;
        this.wheel = new Wheel(800);
    }
    
    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
    }
    
    public void playTurn() {
        Player inTurn = players.get(playerInTurn);
        Sector spinned = wheel.spin();
        inTurn.addMoney(spinned.getValue());
        System.out.println(spinned + " pyöräytetty\n" + inTurn);
        playerInTurn = (playerInTurn + 1) % players.size();
    }
    
}
