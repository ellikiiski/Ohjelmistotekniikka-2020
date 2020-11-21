
package wheelof4tune.domain;

import wheelof4tune.ui.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        // päästään vähän pelaulun alkuun jo
        
        Phrase guessThis = new Phrase("Elli on intellektuelli", Category.COMMON);
        Game game = new Game(guessThis);
        game.addPlayer(new Player("elli"));
        game.addPlayer(new Player("luuseri"));
        
        TextUI ui = new TextUI(game);
        ui.start();
    }
    
}
