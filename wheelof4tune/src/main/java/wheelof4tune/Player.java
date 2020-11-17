
package wheelof4tune;

public class Player {
    
    private String name;
    private int money;
    
    public Player(String name) {
        this.name = name;
        this.money = 0;
    }
    
    public void addMoney(int howMuch) {
        money = Math.max(0, money + howMuch);
    }

    @Override
    public String toString() {
        return "Pelaaja: " + name + ", rahatilanne: " + money + "€";
    }
    
    
    
}
