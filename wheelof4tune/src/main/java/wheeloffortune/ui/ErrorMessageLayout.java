
package wheeloffortune.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ErrorMessageLayout implements Layout {
    
    private VBox layout;
    private List<Label> messages;
    
    public ErrorMessageLayout() {
        messages = new ArrayList<>();
        
        refresh();
    }
    
    public void setNewErrorMessage(String error) {
        messages.add(new Label(error));
        refresh();
    }
    
    public void clear() {
        messages = new ArrayList<>();
        refresh();
    }

    @Override
    public void refresh() {
        layout = new VBox();
        for (Label message : messages) {
            layout.getChildren().add(message);
        }
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
