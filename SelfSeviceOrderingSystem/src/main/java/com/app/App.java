package com.app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        // Top bar
        HBox topBar = createTopBar();
        root.setTop(topBar);

        Scene scene = new Scene(root, 1000, 700);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        primaryStage.setTitle("Self-Service Ordering System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createTopBar() {
        HBox topBar = new HBox();
        topBar.setStyle("-fx-padding: 12; -fx-spacing: 16;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        // Back button
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-padding: 8 16;");

        // Title in the middle
        Label titleLabel = new Label("Cafe Menu");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        HBox.setHgrow(titleLabel, Priority.ALWAYS);
        titleLabel.setAlignment(Pos.CENTER);

        // Cart button
        Button cartButton = new Button("Your Order");
        cartButton.setStyle("-fx-padding: 8 16;");

        topBar.getChildren().addAll(backButton, titleLabel, cartButton);
        return topBar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}