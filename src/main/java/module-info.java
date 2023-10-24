module com.example.multitreading {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.multithreading to javafx.fxml;
    exports com.example.multithreading;
}