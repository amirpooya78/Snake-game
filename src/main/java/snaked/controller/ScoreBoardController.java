package snaked.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import snaked.App;

public class ScoreBoardController {

    @FXML
    private Label highScores;


    @FXML //Back button that takes you to the menu screen
    protected void returnToMenu(ActionEvent click){
        Node node = (Node) click.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(App.mainMenu);
    }

    @FXML //To display top 5 highest scores
    public void setHighScores(String scores){
        highScores.setText(scores);
    }
}
