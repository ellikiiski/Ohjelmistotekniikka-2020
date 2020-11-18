
package wheelof4tune;

public class Main {
    
    public static void main(String[] args) {
        
        // tämä on nyt alkuun pelkkää onnenpyörän pyörittelyä
        
        Phrase guessThis = new Phrase("Elli on intellektielli", Category.COMMON);
        Game game = new Game(guessThis);
        game.addPlayer(new Player("elli"));
        game.addPlayer(new Player("luuseri"));
        
        TextUI ui = new TextUI(game);
        ui.start();
    }
    
}
