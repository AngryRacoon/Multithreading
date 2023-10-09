module com.example.multitreading {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.multitreading to javafx.fxml;
    exports com.example.multitreading;
}