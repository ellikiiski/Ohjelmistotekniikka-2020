
package wheeloffortune.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WheelLayout implements Layout {
    
    private Label spinned;
    private Label instructions;

    private VBox layout;

    public WheelLayout() {
        spinned = new Label("ALOITA PELI PYÖRITTÄMÄLLÄ ONNENPYÖRÄÄ");
        instructions = new Label("(Paina nappia \"Pyöritä\")");

        layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(spinned, instructions);
    }

    public void setNewSpin(String player, String spin, String instruction) {
        spinned = new Label(player.toUpperCase() + ", OSUIT SEKTORIIN " + spin);
        instructions = new Label(instruction);
        refresh();
    }

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(10);
        layout.getChildren().addAll(spinned, instructions);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
