
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    private ButtonLayout buttonLO;
    private String[] buttonNames;
    private Scene scene;
    
    public AddPhraseView() {
        emptyScene();
    }
    
    public void emptyScene() {
        addNewhraseHere = new Label("Täällä voit lisätä uuden fraasin tietokantaan");
        chooseCategory = new Text("Valitse fraasin kategoria allaolevista (tasan yksi)");
        initCheckBoxLayout();
        writePhrase = new Text("Kirjoita alle uusi fraasi (väh. 10-40!), varo kirjoitusvirheitä");
        phraseText = new TextArea();
        phraseText.setPrefHeight(80);

        buttonNames = new String[2];
        buttonNames[0] = "Takaisin aloitussivulle";
        buttonNames[1] = "Tallenna uusi fraasi";
        buttonLO = new ButtonLayout(buttonNames, 10);

        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(addNewhraseHere, chooseCategory, checkBoxLayout, writePhrase, phraseText, buttonLO.getLayout());

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
        return categoriesSelected == 1 && phraseText.getText().length() >= 10 && phraseText.getText().length() <= 40;
    }
    
    public void setInvalidSelections() {
        Text invalid = new Text("Lue ohjeet (suluissa)!");
        layout.getChildren().add(invalid);
    }
    
    public Button getBackButton() {
        return buttonLO.getButton("Takaisin aloitussivulle");
    }
    
    public Button getSaveButton() {
        return buttonLO.getButton("Tallenna uusi fraasi");
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
