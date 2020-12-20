
package wheeloffortune.gamelogic;

public class Player implements Comparable<Player> {
    
    private String name;
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
    public String toString() {
        return "Pelaaja: " + name + ", rahaa pankissa: " + bank + "€";
    }

    @Override
    public int compareTo(Player p) {
        return bank - p.getBank();
    }    
}
