module com.example.newappli {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.newappli to javafx.fxml;
    exports com.example.newappli;
}