
package wheeloffortune.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StatisticsView implements View {
    
    private VBox layout;
    private Text notImplemented;
    private Button back;
    private Scene scene;
    
    public StatisticsView() {
        notImplemented = new Text("Statistiikat ei vielä toteutettu!!");
        back = new Button("Takaisin aloitussivulle");
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(notImplemented, back);
        
        scene = new Scene(layout, 600, 400);
    }
    
    public Button getBackButton() {
        return back;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
