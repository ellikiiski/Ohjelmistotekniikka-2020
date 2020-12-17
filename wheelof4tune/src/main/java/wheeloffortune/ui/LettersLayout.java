
package wheeloffortune.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LettersLayout implements Layout {
    
    private Label cLabel;
    private ButtonLayout cButtons1;
    private ButtonLayout cButtons2;
    private Label nLabel;
    private ButtonLayout nButtons;
    
    private VBox layout;
    
    public LettersLayout() {
        cLabel = new Label("Konsonantit");
        String[] bs1 = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M"};
        String[] bs2 = {"N", "P", "Q", "R", "S", "T", "V", "W", "X", "Z"};
        cButtons1 = new ButtonLayout(bs1, 2);
        cButtons2 = new ButtonLayout(bs2, 2);
        
        nLabel = new Label("Vokaalit (250€)");
        String[] bs3 = {"A", "E", "I", "O", "U", "Y", "Å", "Ä", "Ö"};
        nButtons = new ButtonLayout(bs3, 2);
        
        refresh();
    }

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(6);
        layout.getChildren().addAll(cLabel, cButtons1.getLayout(), cButtons2.getLayout(), nLabel, nButtons.getLayout());
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
