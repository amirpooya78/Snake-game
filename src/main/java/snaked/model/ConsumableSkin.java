package snaked.model;

import javafx.scene.image.Image;
import lombok.Getter;

public enum ConsumableSkin {
    APPLE(1, new Image("snaked/Images/Apple.png"));

    @Getter private final int ordinalRepresentation;

    @Getter private final Image image;

    ConsumableSkin(int ordinalRepresentation, Image image) {
        this.ordinalRepresentation = ordinalRepresentation;
        this.image = image;
    }
}
