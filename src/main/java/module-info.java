/**
 * Module containing all snake classes and resources
 * @author Maxim Carr
 */
module com.snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    exports com.snake;
    opens com.snake to javafx.fxml;
}