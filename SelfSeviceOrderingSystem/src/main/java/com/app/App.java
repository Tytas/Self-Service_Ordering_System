package com.app;

import javafx.application.Application;
import javafx.stage.Stage;
import com.app.view.AppView;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new AppView().show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}