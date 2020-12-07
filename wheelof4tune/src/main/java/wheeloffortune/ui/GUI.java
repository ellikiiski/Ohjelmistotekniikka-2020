
package wheeloffortune.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import wheeloffortune.domain.PhraseDBhandler;
import wheeloffortune.domain.PlayerDBhandler;

public class GUI extends Application {
    
    // KOODIKATSELMOIJA!!
    // Ei kannata välittää tästä tai mistään muustakaan graafiseen käyttöliittymään
    // liittyvästä luokasta, sillä ne ovat pahasti kesken!!
    // Jätä siis huomiotta kaikki tämän pakkauksen luokat paitsi TextUI.java.

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Noniin ja sit hommiin");
        
        PlayerDBhandler plDBh = new PlayerDBhandler("playerDB.txt");
        PhraseDBhandler phDBh = new PhraseDBhandler("phraseDB.txt");
        
        StartView startView = new StartView();
        AddPhraseView apView = new AddPhraseView();
        StatisticsView statView = new StatisticsView();
        GameView gameView = new GameView();
        
        //
        // STATISTIIKKANÄKYMÄÄN (ei toteutettu)
        //
        
        startView.getPlayerStatsButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(statView.getScene());
            }
        });
        
        //// takaisin aloitussivulle
        
        statView.getBackButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(startView.getScene());
            }
        });
        
        //
        // UUDEN FLRAASIN LISÄÄMISNÄKYMÄÄN
        //
        
        startView.getAddPhraseButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(apView.getScene());
            }
        });
        
        //// uuden fraasin tallentaminen 
        
        apView.getSaveButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (apView.allowedToSave()) {
                    phDBh.addPhrase(apView.getPhraseText(), apView.getCategoryName());
                    apView.emptyScene();
                    stage.setScene(startView.getScene());
                } else {
                    apView.setInvalidSelections();
                    stage.setScene(apView.getScene());
                }                
            }
        });
        
        //// takaisin aloitussivulle tallentamatta mitään
        
        apView.getBackButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                    stage.setScene(startView.getScene());
            }
        });
        
        //
        // PELINÄKYMÄÄN
        //
        
        /*startView.getPlayButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(gameView.getScene());
            }
        });*/
        
        stage.setScene(startView.getScene());
        stage.show();
    }
    
}
