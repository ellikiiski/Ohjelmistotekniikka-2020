
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverView implements View {
    
    private Label itIsCorrect;
    private Label congratulations;
    private Label phrase;
    private Label toBank;
    private ButtonLayout buttons;
    
    private VBox layout;
    
    private Scene scene;
    
    public GameOverView() {
        itIsCorrect = new Label("SE ON OIKEIN!!!");
        congratulations = new Label("Onneksi olkoon! Arvasit oikein fraasin");
        phrase = new Label("TYHJÄÄ TÄYNNÄ");
        toBank = new Label("Saat talletettua pankkiin tasan nolla euroa!");
        
        String[] bs = {"Aloitussivulle", "Pelaajatilastoihin"};
        buttons = new ButtonLayout(bs, 10);
        
        refresh();
    }
    
    public void setGameOver(String winner, String correctPhrase, int winningMoney) {
        congratulations = new Label("Onneksi olkoon " + winner + "! Arvasit oikein fraasin");
        phrase = new Label("\"" + correctPhrase + "\"" + ".");
        toBank = new Label("Saat talletettua pankkiin " + winningMoney + " euroa!");
        
        refresh();
    }
    
    public Button getBackToTheStartButton() {
        return buttons.getButton("Aloitussivulle");
    }
    
    public Button getToStatisticsButton() {
        return buttons.getButton("Pelaajatilastoihin");
    }
    
    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(30);
        layout.getChildren().addAll(itIsCorrect, congratulations, phrase, toBank, buttons.getLayout());

        scene = new Scene(layout, 1000, 600);
    }

    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
    
}
