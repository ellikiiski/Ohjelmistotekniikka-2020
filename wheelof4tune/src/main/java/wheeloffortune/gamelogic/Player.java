
package wheeloffortune.gamelogic;

public class Player implements Comparable<Player> {
    
    private final String name;
    private int bank;
    
    public Player(String name, int bank) {
        this.name = name;
        this.bank = bank;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBank() {
        return bank;
    }
    
    public void addToBank(int howMuch) {
        bank += howMuch;
    }

    @Override
    public int compareTo(Player p) {
        return bank - p.getBank();
    }    
}
