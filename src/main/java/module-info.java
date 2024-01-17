module snaked.snaked {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires java.logging;
    requires java.desktop;

    opens snaked to javafx.fxml;
    exports snaked;
    exports snaked.controller;
    exports snaked.model;

    opens snaked.controller to javafx.fxml;
}