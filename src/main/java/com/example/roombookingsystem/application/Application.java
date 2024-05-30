package com.example.roombookingsystem.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher.getInstance().setPrimaryStage(stage);
        SceneSwitcher.getInstance().switchScene(FxmlView.ADHOC);
    }

    public static void main(String[] args) {
        launch();
    }
}