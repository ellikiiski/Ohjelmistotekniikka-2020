
package wheelof4tune;

import java.util.ArrayList;

public class Game {
    
    private ArrayList<Player> players;
    private int playerInTurn;
    private Wheel wheel;
    private Phrase phrase;
    private ArrayList<Character> guessed;
    
    public Game(Phrase phrase) {
        this.players = new ArrayList<>();
        this.playerInTurn = 0;
        this.wheel = new Wheel(800);
        this.phrase = phrase;
        this.guessed = new ArrayList<>();
    }
    
    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
    }
    
    public Player playerInTurn() {
        return players.get(playerInTurn);
    }
    
    public boolean isOver() {
        return false;
    }
    
    public Sector spinWheel() {
        Player inTurn = players.get(playerInTurn);
        Sector spinned = wheel.spin();
        inTurn.addMoney(spinned.getValue());
        playerInTurn = (playerInTurn + 1) % players.size();
        return spinned;
    }
    
}
