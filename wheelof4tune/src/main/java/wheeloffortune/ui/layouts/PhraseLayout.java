
package wheeloffortune.ui.layouts;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PhraseLayout implements Layout {
    
    private final Label puzzle;
    private final Label category;
    private Label phrase;

    private VBox subLO;
    private HBox layout;

    public PhraseLayout(String phr, String cat) {
        puzzle = new Label("Tehtävä:");
        phrase = new Label(phr);
        category = new Label(cat + "-kategoria");
        
        refresh();
    }
    
    public void setPhrase(String p) {
        phrase = new Label(p);
        refresh();
    }
    
    @Override
    public void refresh() {        
        subLO = new VBox();
        subLO.setSpacing(10);
        subLO.getChildren().addAll(category, phrase);
        
        layout = new HBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(puzzle, subLO);
    }

    @Override
    public Pane getLayout() {
        refresh();
        return layout;
    }
    
}
