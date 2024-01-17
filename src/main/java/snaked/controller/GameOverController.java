package snaked.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import snaked.App;

import java.io.IOException;

public class GameOverController {
    App app = new App(); //I'm not sure if this is a good way to do it, but it works :))
    @FXML
    private Label gameOverHighScore;
    @FXML
    private Label gameOverCurrentScore;

    @FXML
    protected void mainMenuButtonClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(App.mainMenu);
    }
    @FXML
    protected void restartGameButtonClick(ActionEvent event) throws IOException {
        app.startGame(event);
    }
    @FXML
    protected void settingsMenuButtonClick(ActionEvent event) throws IOException {
        app.goToSettings(event);
    }
    @FXML
    protected void setGameOverHighScore(String highScore){
        gameOverHighScore.setText(highScore);
    }
    @FXML
    protected  void setGameOverCurrentScore(String currentScore){
        gameOverCurrentScore.setText(currentScore);
    }

}
