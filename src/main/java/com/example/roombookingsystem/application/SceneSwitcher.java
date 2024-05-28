package com.example.roombookingsystem.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SceneSwitcher {
    private static SceneSwitcher instance;
    private Stage primaryStage;

    private SceneSwitcher() {}

    public static SceneSwitcher getInstance() {
        if (instance == null) {
            instance = new SceneSwitcher();
        }
        return instance;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void switchScene(String fxmlFilePath) {
        Parent root = FXMLLoader.load(getClass().getResource())
    }

}
