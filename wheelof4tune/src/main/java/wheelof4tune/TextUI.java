
package wheelof4tune;

import java.util.Scanner;

public class TextUI {
    
    private Game game;
    private Scanner scanner;
    
    public TextUI(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        while (!game.isOver()) {
            System.out.println(game.playerInTurn());
            System.out.println("Pyöritä onnenpyörää syöttämällä \"spin\"");
            String command = scanner.nextLine();
            if (command.equals("spin")) {
                Sector spinned = game.spinWheel();
                System.out.println("Pyöräytit " + spinned);
            } else {
                System.out.println("Ootko sokee? Syötä \"spin\"");
            }
        }
    }
    
}
