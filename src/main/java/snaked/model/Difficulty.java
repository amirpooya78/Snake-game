package snaked.model;

import lombok.Getter;

public enum Difficulty {
    EASY(1, 1, 1),
    MEDIUM(2,2, 2),
    HARD(3, 4, 4);

    @Getter private final int ordinalRepresentation;
    @Getter
    private final int scoreMultiplier;
    @Getter
    private final int speedMultiplier;

    Difficulty(int ordinalRepresentation, int scoreMultiplier, int speedMultiplier) {
        this.ordinalRepresentation = ordinalRepresentation;
        this.scoreMultiplier = scoreMultiplier;
        this.speedMultiplier = speedMultiplier;
    }
}
