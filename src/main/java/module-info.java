module configure_ups {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires org.slf4j;
    exports configure_ups;
    exports configure_ups.model;
    exports configure_ups.controller;
    opens configure_ups;
    opens configure_ups.controller to javafx.fxml;
}