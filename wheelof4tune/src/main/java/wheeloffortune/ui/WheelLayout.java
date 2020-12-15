
package wheeloffortune.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class WheelLayout implements Layout {
    
    private final Label heading;
    private Label spinned;

    private HBox layout;

    public WheelLayout() {
        heading = new Label("OSUIT SEKTORIIN ");
        spinned = new Label("ei vielä mitään");

        layout = new HBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(heading, spinned);
    }

    public void setSpinnedSector(String spin) {
        spinned = new Label(spin);
        refresh();
    }

    @Override
    public void refresh() {
        layout = new HBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(heading, spinned);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
