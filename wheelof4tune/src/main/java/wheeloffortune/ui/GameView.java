
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameView implements View {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästä tai mistään muustakaan graafiseen käyttöliittymään
    // liittyvästä luokasta, sillä ne ovat pahasti kesken!!
    // Jätä siis huomiotta kaikki tämän pakkauksen luokat paitsi TextUI.java.
    
    private Label wheeloffortune;
    
    private Label wheel;
    private Label spinned;
    private Label toBeSolved;
    private Label phrase;
    private Label category;
    private Label categoryName;
    
    private HBox wheelLO;
    private HBox phraseLO;
    private HBox categoryLO;
    private VBox pcLO;
    private VBox gameLO;
    
    private Button guess;
    private Button spin;
    private Button buyNoun;
    private HBox buttonLayout;
    
    private VBox layout;
    
    private Scene scene;
    
    public GameView() {
        this.wheeloffortune = new Label("TERVETULOA PLEAAMAAN ONNENPYÖRÄÄ!!");
        
        this.initGameLayout();        
        this.initButtonLayout();
        
        this.layout = new VBox();
        this.layout.setSpacing(60);
        this.layout.getChildren().addAll(this.wheeloffortune, this.gameLO, this.buttonLayout);
        
        this.scene = new Scene(this.layout);
    }
    
    private void initGameLayout() {
        this.wheel = new Label("ONNENPYÖRÄ:");
        this.spinned = new Label("   ");
        this.toBeSolved = new Label("Tehtävä:");
        this.phrase = new Label("   ");
        this.category = new Label("Kategoria: ");
        this.categoryName = new Label("   ");
        
        this.wheelLO = new HBox();
        this.wheelLO.setSpacing(20);
        this.wheelLO.getChildren().addAll(this.wheel, this.spinned);
        
        this.phraseLO = new HBox();
        this.phraseLO.setSpacing(20);
        this.phraseLO.getChildren().addAll(this.toBeSolved, this.phrase);
        
        this.categoryLO = new HBox();
        this.categoryLO.setSpacing(20);
        this.categoryLO.getChildren().addAll(this.category, this.categoryName);
        
        this.pcLO = new VBox();
        this.pcLO.setSpacing(20);
        this.pcLO.getChildren().addAll(this.phraseLO, this.categoryLO);
        
        this.gameLO = new VBox();
        this.gameLO.setSpacing(50);
        this.gameLO.getChildren().addAll(this.wheelLO, this.pcLO);
    }
    
    private void initButtonLayout() {
        this.guess = new Button("Yritä ratkaista tehtävä");
        this.spin = new Button("Pyöritä onnenpyörää");
        this.buyNoun = new Button("Osta vokaali 250€");
        this.buttonLayout = new HBox();
        this.buttonLayout.setSpacing(30);
        this.buttonLayout.getChildren().addAll(this.guess, this.spin, this.buyNoun);
    }
    
    
    public Button getGuessButton() {
        return guess;
    }
    
    public Button getSpinButton() {
        return spin;
    }
    
    public Button getBuyNounButton() {
        return buyNoun;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
