package com.example.roombookingsystem.application;

import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher.getInstance().setPrimaryStage(stage);
        SceneSwitcher.getInstance().switchScene(FxmlView.LOGIN);
    }

    public static void main(String[] args) {
        launch();
    }
}