package snaked.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import snaked.App;
import snaked.model.*;

import java.io.IOException;

public class GameBoardController {

    @FXML Stage stage;
    @FXML Scene scene;

    @FXML
    Text highestScore = new Text();

    @FXML
    Text eatenConsumables = new Text();

    @FXML GridPane grid = new GridPane();

    // -- constructor.
    public GameBoardController() {

    }

    @FXML
    public void initialize() {
        GameState.getInstance().setGameBoard(new GameBoard());
        GameState.getInstance().setSnake(new Snake());
        Integer highscoreValue = GameState.getInstance().getNHighestScores(1).stream().findFirst().orElse(0);
        highestScore.setText(highscoreValue.toString());
        SnakeSkin snakeSkin = GameState.getInstance().getOptions().getSnakeSkin();
        ConsumableSkin consumableSkin = GameState.getInstance().getOptions().getConsumableSkin();
        grid.setBackground(
                MapSkin.getImageAsBackground(GameState.getInstance().getOptions().getMapSkin().getMapImage())
        );

        int gameBoardSize = GameState.getInstance().getOptions().getGameBoardSize();
        double cellSize = grid.getPrefHeight() / gameBoardSize;

        for (int i = 0; i < gameBoardSize; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
            grid.getRowConstraints().add(new RowConstraints(cellSize));
        }

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.3 * 1 / GameState.getInstance().getOptions().getDifficulty().getSpeedMultiplier()),
                event -> {
                    if (!GameState.getInstance().getGameBoard().nextTurn()) {
                        timeline.stop();

                        int score = GameState.getInstance().getGameBoard().getScore();
                        if (score > 0)
                            GameState.getInstance().addScore(score);

                        navigateToGameOver();
                        return;
                    }

                    eatenConsumables.setText(Integer.toString(GameState.getInstance().getGameBoard().getScore()));

                    // clear gameboard
                    grid.getChildren().clear();

                    BoardCell[][] board = GameState.getInstance().getGameBoard().getBoardAsArray();
                    for (int x = 0; x < board.length; x++) {
                        for (int y = 0; y < board[0].length; y++) {
                            BoardCell cell = board[x][y];
                            if (cell != null) {
                                switch (cell) {
                                    case SNAKE_HEAD -> {
                                        ImageView imageView = getImageViewFromImage(snakeSkin.getHeadImage(), cellSize, GameState.getInstance().getGameBoard().getCurrentDirection());
                                        grid.add(imageView, x - 0, gameBoardSize - y - 1);
                                    }
                                    case SNAKE_BODYPART -> {
                                        ImageView imageView = getImageViewFromImage(snakeSkin.getBodyImage(), cellSize);
                                        grid.add(imageView, x - 0, gameBoardSize - y - 1);
                                    }
                                    case CONSUMABLE -> {
                                        ImageView imageView = getImageViewFromImage(consumableSkin.getImage(), cellSize);
                                        grid.add(imageView, x - 0, gameBoardSize - y - 1);
                                    }
                                }
                            }

                        }
                    }
                    GameState.getInstance().getGameBoard().setLastDirection(
                            GameState.getInstance().getGameBoard().getCurrentDirection()
                    );
                });

        timeline.getKeyFrames().add(keyframe);
        timeline.play();
    }

    private ImageView getImageViewFromImage(Image image, double size) {
        return getImageViewFromImage(image, size, null);
    }

    private ImageView getImageViewFromImage(Image image, double size, Direction currentDirection) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        if (currentDirection != null)
            rotateImageView(imageView, GameState.getInstance().getGameBoard().getCurrentDirection());
        return imageView;
    }

    private void rotateImageView(ImageView imageView, Direction currentDirection) {
        if (currentDirection == null)
            throw new IllegalArgumentException("currentDirection must not be null");

        switch (currentDirection) {
            case LEFT -> imageView.setRotate(0);
            case UP -> imageView.setRotate(90);
            case RIGHT -> imageView.setRotate(180);
            case DOWN -> imageView.setRotate(270);
        }
    }

    private void navigateToGameOver() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/GameOver.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
            //Sets the current score and highScore in game over screen
            GameOverController controller = fxmlLoader.getController();
            controller.setGameOverHighScore(GameState.getInstance().getNHighestScores(1).stream().findFirst().orElse(0).toString());
            controller.setGameOverCurrentScore(eatenConsumables.getText());

            stage = (Stage) (eatenConsumables.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            GameState.getInstance().getLogger().severe("Couldn't navigate to Game Over Screen");
        }
    }

    // -- Method for going back to main menu.
    public void goToMainMenu(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/MainMenu.fxml"));

        this.scene = new Scene(fxmlLoader.load());

        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
}
