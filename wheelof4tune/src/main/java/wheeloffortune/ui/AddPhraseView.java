
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import wheeloffortune.domain.Category;

public class AddPhraseView implements View {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästä tai mistään muustakaan graafiseen käyttöliittymään
    // liittyvästä luokasta, sillä ne ovat pahasti kesken!!
    // Jätä siis huomiotta kaikki tämän pakkauksen luokat paitsi TextUI.java.
    
    private VBox layout;
    private Label addNewhraseHere;
    private Text chooseCategory;
    private CheckBox[] categories;
    private VBox checkBoxLayout;
    private Text writePhrase;
    private TextArea phraseText;
    private Button back;
    private Button save;
    private HBox buttonLayout;
    private Scene scene;
    
    public AddPhraseView() {
        this.emptyScene();
    }
    
    public void emptyScene() {
        this.addNewhraseHere = new Label("Täällä voit lisätä uuden fraasin tietokantaan");
        this.chooseCategory = new Text("Valitse fraasin kategoria allaolevista (tasan yksi)");
        this.initCheckBoxLayout();
        this.writePhrase = new Text("Kirjoita alle uusi fraasi (väh. 10 merkkiä pitkä!), varo kirjoitusvirheitä");
        this.phraseText = new TextArea();

        this.back = new Button("Takaisin aloitussivulle");
        this.save = new Button("Tallenna uusi fraasi");
        this.buttonLayout = new HBox();
        this.buttonLayout.setSpacing(10);
        this.buttonLayout.getChildren().addAll(this.back, this.save);

        this.layout = new VBox();
        this.layout.setSpacing(20);
        this.layout.getChildren().addAll(this.addNewhraseHere, this.chooseCategory, this.checkBoxLayout, this.writePhrase, this.phraseText, this.buttonLayout);

        this.scene = new Scene(this.layout);
    }
    
    private void initCheckBoxLayout() {
        this.initCategoryCheckBoxes();
        checkBoxLayout = new VBox();
        checkBoxLayout.setSpacing(10);
        for (CheckBox cb : categories) {
            checkBoxLayout.getChildren().add(cb);
        }
    }
    
    private void initCategoryCheckBoxes() {
        categories = new CheckBox[Category.values().length];
        for (int i = 0; i < Category.values().length; i++) {
            categories[i] = new CheckBox(Category.values()[i].getCategoryName());
        }
    }
    
    public String getPhraseText() {
        return phraseText.getText();
    }
    
    public String getCategoryName() {
        for (CheckBox cb : categories) {
            if (cb.isSelected()) {
                return cb.getText();
            }
        }
        return "Jotain on nyt pielessä";
    }
    
    public boolean allowedToSave() {
        int categoriesSelected = 0;
        for (CheckBox cb : categories) {
            if (cb.isSelected()) {
                categoriesSelected++;
            }
        }
        return categoriesSelected == 1 && phraseText.getText().length() > 10;
    }
    
    public void setInvalidSelections() {
        Text invalid = new Text("Lue ohjeet (suluissa)!");
        buttonLayout.getChildren().add(invalid);
    }
    
    public Button getBackButton() {
        return back;
    }
    
    public Button getSaveButton() {
        return save;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
