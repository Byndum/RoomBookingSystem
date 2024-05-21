module com.example.roombookingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.roombookingsystem to javafx.fxml;
    exports com.example.roombookingsystem;
}