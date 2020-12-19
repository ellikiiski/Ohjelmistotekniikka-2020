
package wheeloffortune.ui;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import wheeloffortune.domain.Player;
import wheeloffortune.domain.PlayerDBhandler;

public class StatisticsView implements View {
    
    private PlayerDBhandler plDBh;
    
    private VBox layout;
    private Label heading;
    private Text stats;
    private Button back;
    private Scene scene;
    
    public StatisticsView(PlayerDBhandler p) {
        plDBh = p;
        heading = new Label("Pelaajat parhausjärjestyksessä:");
        back = new Button("Takaisin aloitussivulle");
        
        refresh();
    }
    
    public Button getBackButton() {
        return back;
    }
    
    private String initStats() {
        StringBuilder sb = new StringBuilder("");
        List<Player> list = plDBh.getPlayersInOrder();
        for (Player player : list) {
            sb.append(player.getBank() + "€ --- " + player.getName() + "\n");
        }
        return sb.toString();
    }

    @Override
    public void refresh() {
        stats = new Text(initStats());
        
        layout = new VBox();
        layout.setSpacing(20);
        layout.getChildren().addAll(heading, stats, back);

        scene = new Scene(layout, 600, 400);
    }
    
    @Override
    public Scene getScene() {
        refresh();
        return scene;
    }
    
}
