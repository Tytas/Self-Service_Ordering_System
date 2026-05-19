package com.app.view;

import com.app.model.menu.ProductItem;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public final class ProductCardView {
    private ProductCardView() {
    }

    public static VBox build(ProductItem product, Consumer<ProductItem> onProductSelected, String section) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(14));
        card.setAlignment(Pos.TOP_CENTER);
        card.setPrefSize(240, 300);
        card.setStyle("-fx-background-color: #2d3748; -fx-border-color: #4b5563; -fx-border-radius: 14; -fx-background-radius: 14; -fx-cursor: hand;");

        Region imageArea = new Region();
        imageArea.setPrefSize(200, 140);
        imageArea.setStyle("-fx-background-color: #f3f4f6; -fx-border-color: #d1d5db; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label imageText = new Label(product.getImageLabel());
        imageText.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 12px;");

        VBox imageBox = new VBox(imageArea, imageText);
        imageBox.setAlignment(Pos.CENTER);

        Label sectionLabel = new Label(section != null ? section : "");
        sectionLabel.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 11px;");

        Label name = new Label(product.getName());
        name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #f3f4f6;");

        Label description = new Label(product.getDescription());
        description.setWrapText(true);
        description.setStyle("-fx-text-fill: #d1d5db; -fx-font-size: 13px;");

        Label price = new Label("Price: " + product.getPrice() + " TL");
        price.setStyle("-fx-font-weight: bold; -fx-text-fill: #10b981;");

        Button viewButton = new Button("View Details");
        viewButton.setMaxWidth(Double.MAX_VALUE);
        viewButton.setOnAction(event -> onProductSelected.accept(product));
        viewButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-radius: 10; -fx-font-weight: bold; -fx-cursor: hand;");
        viewButton.setOnMouseEntered(e -> viewButton.setStyle("-fx-background-color: #059669; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-radius: 10; -fx-font-weight: bold; -fx-cursor: hand;"));
        viewButton.setOnMouseExited(e -> viewButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-radius: 10; -fx-font-weight: bold; -fx-cursor: hand;"));

        card.getChildren().addAll(imageBox, sectionLabel, name, description, price, viewButton);
        return card;
    }
}