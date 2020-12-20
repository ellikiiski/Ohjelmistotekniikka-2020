
package wheeloffortune.ui.layouts;

import java.util.HashMap;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ButtonLayout implements Layout {
    
    private final HashMap<String, Button> buttons;    
    private final int spacing;    
    private HBox layout;

    public ButtonLayout(String[] bs, int space) {
        buttons = new HashMap<>();
        for (String b : bs) {
            buttons.put(b, new Button(b));
        }
        spacing = space;        
        refresh();
    }
    
    /// Napin getteri nimen perustteella

    public Button getButton(String b) {
        return buttons.getOrDefault(b, null);
    }
    
    /// Nappien disablointi ja enablointi nimen perusteella

    public void disableButton(String b) {
        for (String buttonName : buttons.keySet()) {
            if (buttonName.equals(b)) {
                buttons.get(b).setDisable(true);
            }
        }
    }

    public void disableAll() {
        for (Button b : buttons.values()) {
            b.setDisable(true);
        }
    }
    
    public void enableButton(String b) {
        for (String buttonName : buttons.keySet()) {
            if (buttonName.equals(b)) {
                buttons.get(b).setDisable(false);
            }
        }
    }

    public void enableAll() {
        for (Button b : buttons.values()) {
            b.setDisable(false);
        }
    }
    
    /// Rajapinnan metodit

    @Override
    public void refresh() {
        layout = new HBox();
        layout.setSpacing(spacing);
        for (Button button : buttons.values()) {
            layout.getChildren().add(button);
        }
    }

    @Override
    public Pane getLayout() {
        refresh();
        return layout;
    }
    
}
