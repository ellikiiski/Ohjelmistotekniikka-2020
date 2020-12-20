
package wheeloffortune.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import wheeloffortune.domain.PhraseDBhandler;
import wheeloffortune.domain.PlayerDBhandler;

public class GUI extends Application {
    
    // GUIssa toimii toistaiseksi kunnolla vain uuden fraasin lisäys.
    // Pelin pelaamisessa toimii pelaajien lisäys ja onnenpyörän pyörittely.

    @Override
    public void start(Stage stage) throws Exception {
        
        PlayerDBhandler plDBh = new PlayerDBhandler("playerDB.txt");
        PhraseDBhandler phDBh = new PhraseDBhandler("phraseDB.txt");
        
        StartView startView = new StartView();
        AddPhraseView aPhView = new AddPhraseView();
        StatisticsView statView = new StatisticsView(plDBh);
        AddPlayersView aPlView = new AddPlayersView();
        GameView gameView = new GameView();
        GameOverView gameOverView = new GameOverView();
        
        //
        // STATISTIIKKANÄKYMÄÄN (ei toteutettu)
        //
        
        startView.getPlayerStatsButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(statView.getScene());
                stage.setTitle("Pelaajatilastot");
            }
        });
        
        //// takaisin aloitussivulle
        
        statView.getBackButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(startView.getScene());
                stage.setTitle("Tervetuloa");
            }
        });
        
        //
        // UUDEN FLRAASIN LISÄÄMISNÄKYMÄÄN
        //
        
        startView.getAddPhraseButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                aPhView.clearView();
                stage.setScene(aPhView.getScene());
                stage.setTitle("Lisää uusi fraasi");
            }
        });
        
        //// uuden fraasin tallentaminen 
        
        aPhView.getSaveButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (!aPhView.oneCategorySelected()) {
                    aPhView.setInvalidCategoriesMessage();
                    stage.setScene(aPhView.getScene());
                } else if (!aPhView.phraseLongEnough()) {
                    aPhView.setTooShortPhrase();
                    stage.setScene(aPhView.getScene());
                } else if (!aPhView.phreaseNotTooLong()) {
                    aPhView.setTooLongPhrase();
                    stage.setScene(aPhView.getScene());
                } else {
                    phDBh.addPhrase(aPhView.getPhraseText(), aPhView.getCategoryName());
                    stage.setScene(startView.getScene());
                }                
            }
        });
        
        //// takaisin aloitussivulle tallentamatta mitään
        
        aPhView.getBackButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                    stage.setScene(startView.getScene());
                    stage.setTitle("Tervetuloa");
            }
        });
        
        //
        // PELAAJIEN LISÄYSNÄKYMÄÄN
        //
        
        startView.getPlayButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                aPlView.clearView();
                stage.setScene(aPlView.getScene());
                stage.setTitle("Lisää pelaajat");
            }
        });
        
        //
        // PELINÄKYMÄÄN
        //
        
        aPlView.getAddPlayersButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (aPlView.all3PlayersFilledIn()) {
                    gameView.setGame(plDBh, phDBh, aPlView.getGivenPlayers());
                    stage.setScene(gameView.getScene());
                    stage.setTitle("ONNENPYÖRÄ");
                } else {
                    aPlView.setInvalidPlayersMessage();
                    stage.setScene(aPlView.getScene());
                }
            }
        });
        
        //
        // PELAAMINEN
        //
        
        // Onnenpyörän pyörittäminen
        
        gameView.getSpinButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                gameView.spinTheWheel();
                stage.setScene(gameView.getScene());
            }
        });
        
        // Konsonantin veikkaaminen
        
        gameView.getGuessButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                gameView.guessConsonant();
                stage.setScene(gameView.getScene());
            }
        });
        
        // vokaalin ostaminen
        
        gameView.getBuyNounButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                gameView.setBuyNoun();
                stage.setScene(gameView.getScene());
            }
        });
        
        gameView.getBuyButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                gameView.buyNoun();
                stage.setScene(gameView.getScene());
            }
        });
        
        // ratkaisun yrittäminen
        
        gameView.getGuessThePhraseButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                gameView.setGuessThePhrase();
                stage.setScene(gameView.getScene());
            }
        });
        
        gameView.getSolveButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (gameView.guessThePhrase()) {
                    gameOverView.setGameOver(gameView.getWinnerName(), gameView.getCorrectPhrase(), gameView.getWinningMoney());
                    stage.setScene(gameOverView.getScene());
                } else {
                    stage.setScene(gameView.getScene());
                }
            }
        });
        
        // takaisin aloitussivulle
        
        gameOverView.getBackToTheStartButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                    stage.setScene(startView.getScene());
                    stage.setTitle("Tervetuloa");
            }
        });
        
        // statistiikkanäkymään
        
        gameOverView.getToStatisticsButton().setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                stage.setScene(statView.getScene());
                stage.setTitle("Pelaajatilastot");
            }
        });
        
        stage.setScene(startView.getScene());
        stage.setTitle("Tervetuloa");
        stage.show();
    }
    
}
