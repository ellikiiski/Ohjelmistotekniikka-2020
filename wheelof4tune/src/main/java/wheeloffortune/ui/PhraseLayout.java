
package wheeloffortune.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PhraseLayout implements Layout {
    
    private Label phrase;
    private final Label category;

    private VBox layout;

    public PhraseLayout(String phr, String cat) {
        phrase = new Label(cat);
        category = new Label(phr);
        
        refresh();
    }
    
    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(phrase, category);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
