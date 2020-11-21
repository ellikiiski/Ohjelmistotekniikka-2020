
package wheeloffortune.domain;

public class Player {
    
    private int id;
    private String name;
    private int bank;
    
    public Player(String name) {
        this.id = 666;
        this.name = name;
        this.bank = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public void addToBank(int howMuch) {
        bank = howMuch;
    }

    @Override
    public String toString() {
        return "Pelaaja: " + name + ", rahaa pankissa: " + bank + "€";
    }
    
    
    
}
