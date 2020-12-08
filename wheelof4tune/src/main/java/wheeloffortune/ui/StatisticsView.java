
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
        this.notImplemented = new Text("Statistiikat ei vielä toteutettu!!");
        this.back = new Button("Takaisin aloitussivulle");
        this.layout = new VBox();
        this.layout.setSpacing(20);
        this.layout.getChildren().addAll(this.notImplemented, this.back);
        
        this.scene = new Scene(this.layout, 600, 400);
    }
    
    public Button getBackButton() {
        return back;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
    
}
