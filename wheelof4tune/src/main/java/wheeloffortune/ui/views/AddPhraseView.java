
package wheeloffortune.ui.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import wheeloffortune.gamelogic.Category;
import wheeloffortune.ui.layouts.ButtonLayout;
import wheeloffortune.ui.layouts.ErrorMessageLayout;

public class AddPhraseView implements View {
    
    private final ErrorMessageLayout emlo;    
    private VBox layout;
    private final Label addNewhraseHere;
    private final Text chooseCategory;
    private CheckBox[] categories;
    private VBox checkBoxLayout;
    private final Text writePhrase;
    private TextArea phraseText;
    private final ButtonLayout buttonLO;
    private Scene scene;
    
    public AddPhraseView() {
        emlo = new ErrorMessageLayout();        
        addNewhraseHere = new Label("Täällä voit lisätä uuden fraasin tietokantaan");
        chooseCategory = new Text("Valitse fraasin kategoria allaolevista (tasan yksi)");
        writePhrase = new Text("Kirjoita alle uusi fraasi (10-40 merkkiä), varo kirjoitusvirheitä");
        String[] bs = {"Takaisin aloitussivulle", "Tallenna uusi fraasi"};
        buttonLO = new ButtonLayout(bs, 10);        
        clearView();
    }
    
    /// Checkboksien asetteluun liittyviä metodeja
    
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
            categories[i] = new CheckBox(Category.values()[i].getName());
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
    
    /// Tarkistusmetodit: täyttääkö fraasi vaatimukset
    
    //// tarkistaa onko tasan yksi kategoria valittu
    public boolean oneCategorySelected() {
        int categoriesSelected = 0;
        for (CheckBox cb : categories) {
            if (cb.isSelected()) {
                categoriesSelected++;
            }
        }
        return categoriesSelected == 1;
    }
    
    //// tarkistaa onko fraasi tarpeeksi pitkä eli vähintään 10 merkkiä
    public boolean phraseLongEnough() {
        return phraseText.getText().length() >= 10;
    }
    
    //// tarkistaa onko fraasi tarpeeksi lyhyt eli maksimissaan 40 merkkiä
    public boolean phreaseNotTooLong() {
        return phraseText.getText().length() <= 40;
    }
    
    /// Virheilmoitusten lisäys
    
    public void setInvalidCategoriesMessage() {
        emlo.setNewErrorMessage("Valitse tasan yksi kategoria!");
        refresh();
    }
    
    public void setTooShortPhrase() {
        emlo.setNewErrorMessage("Ehdottamasi fraasi on liian lyhyt!");
        refresh();
    }
    
    public void setTooLongPhrase() {
        emlo.setNewErrorMessage("Ehdottamasi fraasi on liian pitkä!");
        refresh();
    }
    
    /// Nappien getterit
    
    public Button getBackButton() {
        return buttonLO.getButton("Takaisin aloitussivulle");
    }
    
    public Button getSaveButton() {
        return buttonLO.getButton("Tallenna uusi fraasi");
    }
    
    //// tyhjentää näkymän
    public void clearView() {
        emlo.clear();
        initCheckBoxLayout();
        phraseText = new TextArea();
        phraseText.setPrefHeight(80);
        refresh();
    }
    
    /// Rajapinnan metodit
    
    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(addNewhraseHere, chooseCategory, checkBoxLayout, writePhrase, phraseText, buttonLO.getLayout(), emlo.getLayout());

        scene = new Scene(layout, 600, 400);
    }

    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
    
}
