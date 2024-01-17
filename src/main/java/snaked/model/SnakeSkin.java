package snaked.model;

import javafx.scene.image.Image;
import lombok.Getter;

public enum SnakeSkin {
    ONE(1, new Image("snaked/Images/1snake_head.png"), new Image("snaked/Images/1snake_body.png")),
    TWO(2, new Image("snaked/Images/2snake_head.png"), new Image("snaked/Images/2snake_body.png")),
    THREE(3, new Image("snaked/Images/3snake_head.png"), new Image("snaked/Images/3snake_body.png"));

    @Getter private final int ordinalRepresentation;

    @Getter private final Image headImage;
    @Getter private final Image bodyImage;

    SnakeSkin(int ordinalRepresentation, Image headImage, Image bodyImage) {
        this.ordinalRepresentation = ordinalRepresentation;
        this.headImage = headImage;
        this.bodyImage = bodyImage;
    }
}
