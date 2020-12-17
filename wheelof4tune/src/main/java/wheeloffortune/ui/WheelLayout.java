
package wheeloffortune.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class WheelLayout implements Layout {
    
    private Label heading;
    private Label spinned;

    private HBox layout;

    public WheelLayout() {
        heading = new Label("ALOITTAKAA PELI PYÖRITTÄMÄLLÄ ONNENPYÖRÄÄ");
        spinned = new Label("(nappi: Pyöritä)");

        layout = new HBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(heading, spinned);
    }

    public void setNewSpin(String player, String spin) {
        heading = new Label(player.toUpperCase() + ", OSUIT SEKTORIIN ");
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
