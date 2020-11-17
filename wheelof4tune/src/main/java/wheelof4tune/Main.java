
package wheelof4tune;

public class Main {
    
    public static void main(String[] args) {
        
        // tämä on nyt alkuun pelkkää onnenpyörän pyörittelyä
        
        Game game = new Game();
        game.addPlayer(new Player("elli"));
        game.addPlayer(new Player("luuseri"));
        
        for (int i = 0; i < 10; i++) {
            game.playTurn();
        }
    }
    
}
