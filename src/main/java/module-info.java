module com.example.roombookingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.roombookingsystem to javafx.fxml;
    //exports com.example.roombookingsystem;
//    exports com.example.roombookingsystem.application;
//    opens com.example.roombookingsystem.application to javafx.fxml;
//    exports com.example.roombookingsystem.application.controller;
//    opens com.example.roombookingsystem.application.controller to javafx.fxml;
    exports com.example.roombookingsystem;
    exports com.example.roombookingsystem.application;
    exports com.example.roombookingsystem.application.controller;
    opens com.example.roombookingsystem.application.controller to javafx.fxml;
    exports com.example.roombookingsystem.domain;
    exports com.example.roombookingsystem.foundation;
    exports com.example.roombookingsystem.persistence;
    exports com.example.roombookingsystem.presentation;
    exports com.example.roombookingsystem.services;
}