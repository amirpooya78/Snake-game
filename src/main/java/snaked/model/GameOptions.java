package snaked.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GameOptions {
    private int startingPosX;
    private int startingPosY;
    private int initialSnakeLength;
    private int gameBoardSize;
    private Direction startingDirection;
    private SnakeSkin snakeSkin;
    private MapSkin mapSkin;
    private boolean soundEffectsOn;
    private Difficulty difficulty;
    private ConsumableSkin consumableSkin;
}
