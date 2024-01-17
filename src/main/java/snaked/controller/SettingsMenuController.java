package snaked.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import snaked.App;
import snaked.model.Difficulty;
import snaked.model.GameState;
import snaked.model.MapSkin;
import snaked.model.SnakeSkin;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SettingsMenuController {
    public static final String SELECTED_STYLE = "-fx-background-color: #111111;-fx-text-fill: #fff;";
    @FXML
    ImageView volumeIcon = new ImageView();
    @FXML
    Button easyButton = new Button();
    @FXML
    Button mediumButton = new Button();
    @FXML
    Button hardButton = new Button();
    @FXML
    Button map1Button = new Button();
    @FXML
    Button map2Button = new Button();
    @FXML
    Button map3Button = new Button();
    @FXML
    Button snake1Button = new Button();
    @FXML
    Button snake2Button = new Button();
    @FXML
    Button snake3Button = new Button();

    Image muteVolume = new Image("snaked/Icons/muteVolume.png");
    Image fullVolume = new Image("snaked/Icons/fullVolume.png");

    @FXML
    public void initialize() {
        changeDifficulty(GameState.getInstance().getOptions().getDifficulty());
        changeSnakeSkin(GameState.getInstance().getOptions().getSnakeSkin());
        changeMapSkin(GameState.getInstance().getOptions().getMapSkin());
        setSoundOn(GameState.getInstance().getOptions().isSoundEffectsOn());
    }

    @FXML //button for toggling sound & changes buttons icon on click.
    protected void volumeButtonClick() {
        setSoundOn(!GameState.getInstance().getOptions().isSoundEffectsOn()); // toggle sound
    }

    @FXML //button that shows the original scene form the main menu
    protected void backButtonClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(App.mainMenu);
    }

    private void selectButtonNr(int index, Button... buttons) {
        for(Button button : buttons) {
            button.setStyle(null);
        }
        buttons[index-1].setStyle(SELECTED_STYLE);
    }

    private void setSoundOn(boolean soundOn) {
        GameState.getInstance().getOptions().setSoundEffectsOn(soundOn);
        if(soundOn) {
            volumeIcon.setImage(fullVolume);
            App.soundEffect.start();
            App.soundEffect.loop(-1);
        }
        else{
            volumeIcon.setImage(muteVolume);
            App.soundEffect.stop();
        }
    }

    private void changeDifficulty(Difficulty difficulty) {
        GameState.getInstance().getOptions().setDifficulty(difficulty);
        selectButtonNr(difficulty.getOrdinalRepresentation(), easyButton, mediumButton, hardButton);
    }

    private void changeSnakeSkin(SnakeSkin snakeSkin) {
        GameState.getInstance().getOptions().setSnakeSkin(snakeSkin);
        selectButtonNr(snakeSkin.getOrdinalRepresentation(), snake1Button, snake2Button, snake3Button);
    }

    private void changeMapSkin(MapSkin mapSkin) {
        GameState.getInstance().getOptions().setMapSkin(mapSkin);
        selectButtonNr(mapSkin.getOrdinalRepresentation(), map1Button, map2Button, map3Button);
    }

    @FXML //changes color of buttons to indicate what options have been chosen.
    protected void easyButtonClick(){
        changeDifficulty(Difficulty.EASY);
    }
    @FXML
    protected void mediumButtonClick(){
        changeDifficulty(Difficulty.MEDIUM);
    }
    @FXML
    protected void hardButtonClick(){
        changeDifficulty(Difficulty.HARD);
    }


    @FXML
    protected void snake1ButtonClick(){
        changeSnakeSkin(SnakeSkin.ONE);
    }
    @FXML
    protected void snake2ButtonClick(){
        changeSnakeSkin(SnakeSkin.TWO);
    }
    @FXML
    protected void snake3ButtonClick(){
        changeSnakeSkin(SnakeSkin.THREE);
    }

    @FXML
    protected void map1ButtonClick(){
        changeMapSkin(MapSkin.ONE);
    }
    @FXML
    protected void map2ButtonClick(){
        changeMapSkin(MapSkin.TWO);

    }
    @FXML
    protected void map3ButtonClick(){
        changeMapSkin(MapSkin.THREE);
    }

}