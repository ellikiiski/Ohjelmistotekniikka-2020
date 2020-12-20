
package wheeloffortune.ui.layouts;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class WheelLayout implements Layout {
    
    private final Label wheelOfFortune;
    private final Label lastSpinWas;
    private Label spinned;

    private VBox subLO;
    private HBox layout;

    public WheelLayout() {
        wheelOfFortune = new Label("Onnenpyörä:");
        lastSpinWas = new Label("VIIMEKSI PYÖRÄYTETTY");
        spinned = new Label("(peliä ei vielä avattu)");

        refresh();
    }
    
    public void setToInit() {
        spinned = new Label("(peliä ei vielä avattu)");
        
        refresh();
    }

    public void setNewSpin(String spin) {
        spinned = new Label(spin);
        refresh();
    }

    @Override
    public void refresh() {
        subLO = new VBox();
        subLO.setSpacing(10);
        subLO.getChildren().addAll(lastSpinWas, spinned);
        
        layout = new HBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(wheelOfFortune, subLO);
    }

    @Override
    public Pane getLayout() {
        return layout;
    }
    
}
