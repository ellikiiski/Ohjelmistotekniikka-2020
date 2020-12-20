
package wheeloffortune.ui.layouts;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ErrorMessageLayout implements Layout {
    
    private List<Label> messages;    
    private VBox layout;
    
    public ErrorMessageLayout() {
        messages = new ArrayList<>();
        refresh();
    }
    
    //// lisää uuden virheilmoituksen
    public void setNewErrorMessage(String error) {
        messages.add(new Label(error));
        refresh();
    }
    
    //// tyhjentää asettelun poistamalla vanhat virheilmoitukset
    public void clear() {
        messages = new ArrayList<>();
        refresh();
    }
    
    /// Rajapinnan metodit

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
