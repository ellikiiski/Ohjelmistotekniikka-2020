
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
        AddPhraseView aPhView = new AddPhraseView();
        StatisticsView statView = new StatisticsView();
        AddPlayersView aPlView = new AddPlayersView();
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
                stage.setScene(aPhView.getScene());
            }
        });
        
        //// uuden fraasin tallentaminen 
        
        aPhView.getSaveButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (aPhView.allowedToSave()) {
                    phDBh.addPhrase(aPhView.getPhraseText(), aPhView.getCategoryName());
                    aPhView.emptyScene();
                    stage.setScene(startView.getScene());
                } else {
                    aPhView.setInvalidSelections();
                    stage.setScene(aPhView.getScene());
                }                
            }
        });
        
        //// takaisin aloitussivulle tallentamatta mitään
        
        aPhView.getBackButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                    stage.setScene(startView.getScene());
            }
        });
        
        //
        // PELAAJIEN LISÄYSNÄKYMÄÄN
        //
        
        startView.getPlayButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(aPlView.getScene());
            }
        });
        
        //
        // PELINÄKYMÄÄN
        //
        
        aPlView.getAddPlayersButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(gameView.getScene());
            }
        });
        
        stage.setScene(startView.getScene());
        stage.show();
    }
    
}
