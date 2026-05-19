package com.app.view;

import com.app.model.menu.MenuCategory;
import java.util.List;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuView {
    public VBox build(List<MenuCategory> categories, Consumer<MenuCategory> onCategorySelected) {
        VBox container = new VBox(22);
        container.setPadding(new Insets(36));
        container.setAlignment(Pos.TOP_CENTER);

        Label heading = new Label("Choose a Category");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #f3f4f6;");

        Label helper = new Label("Select a category to browse its products.");
        helper.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 13px;");

        VBox buttons = new VBox(14);
        buttons.setMaxWidth(420);
        buttons.setAlignment(Pos.CENTER);

        for (MenuCategory category : categories) {
            Button button = new Button(category.getDisplayName());
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefHeight(72);
            button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-radius: 12; -fx-background-color: #374151; -fx-text-fill: #f3f4f6; -fx-border-color: #4b5563; -fx-cursor: hand;");
            button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-radius: 12; -fx-background-color: #4b5563; -fx-text-fill: #f3f4f6; -fx-border-color: #10b981; -fx-border-width: 2; -fx-cursor: hand;"));
            button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-radius: 12; -fx-background-color: #374151; -fx-text-fill: #f3f4f6; -fx-border-color: #4b5563; -fx-cursor: hand;"));
            button.setOnAction(event -> onCategorySelected.accept(category));
            buttons.getChildren().add(button);
        }

        container.getChildren().addAll(heading, helper, buttons);
        return container;
    }
}