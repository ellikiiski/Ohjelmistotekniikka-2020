
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
        // Tää on nyt toistaseks hirveetä spagettia kun on vasta välaikanen tekstikäyttöliittymä
        // Myöskään kaikkia mahdollisisa käyttiksen väärinkäytöksiä ei hoidella vielä tällä spagetilla
        // Myöskään kaikki ei vielä sääntöjenkään osalta toimi ihan kuten lopputuloksessa on tarkoitus
        System.out.println("TERVETULOA ONNENPYÖRÄÄN!");
        System.out.println("Kategoria: " + game.getCategory());
        System.out.println("");
        System.out.println("Arvuuteltava fraasi: " + game.getPhraseAsString());
        while (!game.isOver()) {
            System.out.println("");
            System.out.println("Pelaajan " + game.playerInTurn() + " vuoro");
            System.out.println("Mahdollisuutesi: ");
            System.out.println("- Pyöritä onnenpyörää syöttämällä \"spin\"");
            if (game.canBuyNoun()) {
                System.out.println("- Osta vokaali syöttämällä ainoastaan yksi vokaali kirjaimena");
            }
            System.out.println("");
            String command = scanner.nextLine();
            System.out.println("");
            if (command.equals("spin")) {
                game.spinWheel();
                System.out.println("Osuit sektoriin " + game.getLatestSpinSectorName() + "!");
                if (game.latestSpinIsSkip()) {
                    System.out.println("Et voi arvata konsonanttia. Vuoro vaihtuu.");
                    System.out.println("");
                } else if(game.latestSpinIsBankcrupt()) {
                    System.out.println("Kaikki rahasi vietiin, kuten myös arvausvuorosi.");
                    System.out.println("");
                } else {
                    System.out.println("Arvaa kirjainta syöttämällä yksi konsonantti: ");
                    System.out.println("");
                    String c = scanner.nextLine();
                    System.out.println("");
                    int found = game.guessConsonant(c.charAt(0));
                    if (found > 0) {
                        System.out.println(found + " osumaa! Saat jatkaa vuoroasi.");
                        System.out.println("");
                    } else {
                        System.out.println("Nolla osumaa. Vuoro vaihtuu.");
                        System.out.println("");
                    }
                }
            } else if (game.canBuyNoun() && command.length() == 1) {
                game.buyNoun(command.charAt(0));
            } else {
                System.out.println("Syötä validi komento.");
            }
            System.out.println("Arvuuteltava fraasi: " + game.getPhraseAsString());
            
            /*System.out.println(game.playerInTurn());
            System.out.println("Pyöritä onnenpyörää syöttämällä \"spin\"");
            String command = scanner.nextLine();
            if (command.equals("spin")) {
                Sector spinned = game.spinWheel();
                System.out.println("Pyöräytit " + spinned);
            } else {
                System.out.println("Ootko sokee? Syötä \"spin\"");
            }*/
        }
    }
    
}
