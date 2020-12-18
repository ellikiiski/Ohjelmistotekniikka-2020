
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
        
        refresh();
    }
    
    public Button getBackButton() {
        return back;
    }

    @Override
    public void refresh() {
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(notImplemented, back);

        scene = new Scene(layout, 600, 400);
    }
    
    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
    
}
