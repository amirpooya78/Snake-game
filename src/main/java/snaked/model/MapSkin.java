package snaked.model;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import lombok.Getter;

import java.nio.file.Path;

public enum MapSkin {
    ONE(1, new Image("snaked/Images/1map.png")),
    TWO(2, new Image("snaked/Images/2map.png")),
    THREE(3, new Image("snaked/Images/3map.png"));

    @Getter private final int ordinalRepresentation;
    @Getter private final Image mapImage;

    MapSkin(int ordinalRepresentation, Image mapImage) {
        this.ordinalRepresentation = ordinalRepresentation;
        this.mapImage = mapImage;
    }

    public static Background getImageAsBackground(Image image) {
        return new Background(
                new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)
        );
    }
}
