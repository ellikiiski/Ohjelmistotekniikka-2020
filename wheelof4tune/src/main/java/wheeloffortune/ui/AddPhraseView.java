
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
        emptyScene();
    }
    
    public void emptyScene() {
        addNewhraseHere = new Label("Täällä voit lisätä uuden fraasin tietokantaan");
        chooseCategory = new Text("Valitse fraasin kategoria allaolevista (tasan yksi)");
        initCheckBoxLayout();
        writePhrase = new Text("Kirjoita alle uusi fraasi (väh. 10 merkkiä pitkä!), varo kirjoitusvirheitä");
        phraseText = new TextArea();

        back = new Button("Takaisin aloitussivulle");
        save = new Button("Tallenna uusi fraasi");
        buttonLayout = new HBox();
        buttonLayout.setSpacing(10);
        buttonLayout.getChildren().addAll(back, save);

        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(addNewhraseHere, chooseCategory, checkBoxLayout, writePhrase, phraseText, buttonLayout);

        scene = new Scene(layout, 600, 400);
    }
    
    private void initCheckBoxLayout() {
        initCategoryCheckBoxes();
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
