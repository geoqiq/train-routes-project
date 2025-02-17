module org.example.trenuricaps {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.trenuricaps.controller to javafx.fxml;
    exports org.example.trenuricaps;
}