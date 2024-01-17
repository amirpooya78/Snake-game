package snaked.model;

import lombok.Getter;

public class Snake {
    @Getter
    private int eatenConsumables = 0;

    public void increaseEatenConsumables() {
        eatenConsumables++;
    }

    public int getCurrentLength() {
        return GameState.getInstance().getOptions().getInitialSnakeLength() + eatenConsumables;
    }

}
