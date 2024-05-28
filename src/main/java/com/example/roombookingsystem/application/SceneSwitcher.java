package com.example.roombookingsystem.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void switchScene(FxmlView view) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(view.getPath()));
        Scene scene = new Scene(root, view.getWidth(), view.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createPopUp(FxmlView view) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(view.getPath()));
        Scene scene = new Scene(root, view.getWidth(), view.getHeight());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
