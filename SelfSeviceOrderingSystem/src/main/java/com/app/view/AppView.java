package com.app.view;

import com.app.controller.UIController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;

public class AppView {
    private Stage primaryStage;

    public void show(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        UIController controller = new UIController(root);
        root.setTop(createHeader());
        controller.showMenu();

        Scene scene = new Scene(root, 1000, 700);
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);

        primaryStage.setTitle("Self-Service Ordering System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox(12);
        header.setStyle("-fx-padding: 18 24; -fx-background-color: #1f2937; -fx-border-color: #374151; -fx-border-width: 0 0 1 0;");

        Label title = new Label("Self-Service Ordering System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #f3f4f6;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button cartButton = new Button("🛒 Cart");
        cartButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8 16; -fx-cursor: hand;");
        cartButton.setOnAction(event -> showCartModal());
        cartButton.setOnMouseEntered(e -> cartButton.setStyle("-fx-background-color: #059669; -fx-text-fill: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8 16; -fx-cursor: hand;"));
        cartButton.setOnMouseExited(e -> cartButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8 16; -fx-cursor: hand;"));

        header.getChildren().addAll(title, spacer, cartButton);
        return header;
    }

    private void showCartModal() {
        openCartModal(this.primaryStage);
    }

    public static void openCartModal(Stage owner) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        if (owner != null) dialog.initOwner(owner);
        dialog.setResizable(false);
        dialog.setAlwaysOnTop(true);
        dialog.setTitle("Order Summary");

        VBox content = new VBox(14);
        content.setPadding(new Insets(18));
        content.setAlignment(Pos.CENTER_LEFT);
        content.setStyle("-fx-background-color: #1f2937;");

        Label title = new Label("Order Summary");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #f3f4f6;");

        java.util.List<com.app.model.CartItem> items = com.app.model.Cart.getInstance().getItems();

        Button orderNowButton = new Button("✓ Order Now");
        orderNowButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand;");
        orderNowButton.setDisable(items.isEmpty());
        orderNowButton.setOnMouseEntered(e -> {
            if (!orderNowButton.isDisable()) orderNowButton.setStyle("-fx-background-color: #059669; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand;");
        });
        orderNowButton.setOnMouseExited(e -> {
            if (!orderNowButton.isDisable()) orderNowButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand;");
        });

        // Progress UI inside cart modal
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(360);
        progressBar.setStyle("-fx-accent: #10b981;");
        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: #f3f4f6;");
        VBox progressBox = new VBox(8, statusLabel, progressBar);
        progressBox.setVisible(false);

        // prepare list and total so handler can reuse
        VBox listBox = new VBox(8);
        for (com.app.model.CartItem it : items) {
            Label item = new Label(it.getName() + " - " + it.getTotalPrice());
            item.setStyle("-fx-text-fill: #f3f4f6; -fx-font-size: 13px;");
            String addons = String.join(", ", it.getAddons());
            if (!addons.isBlank()) {
                Label a = new Label("  Add-ons: " + addons);
                a.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");
                listBox.getChildren().addAll(item, a);
            } else {
                listBox.getChildren().add(item);
            }
        }
        Label total = new Label("Total: " + com.app.model.Cart.getInstance().getTotalPrice() + " TL");
        total.setStyle("-fx-font-weight: bold; -fx-text-fill: #10b981; -fx-font-size: 14px;");

        Button clearButton = new Button("🗑️ Clear");
        clearButton.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand;");
        clearButton.setOnMouseEntered(e2 -> clearButton.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand;"));
        clearButton.setOnMouseExited(e2 -> clearButton.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-font-size: 13px; -fx-cursor: hand;"));
        clearButton.setOnAction(e -> {
            com.app.model.Cart.getInstance().clear();
            com.app.model.OrderManager.getInstance().resetCurrentOrder();
            orderNowButton.setDisable(true);
            progressBox.setVisible(false);
            content.getChildren().clear();
            content.getChildren().addAll(title, new Label("No items in the cart."), new HBox(8, orderNowButton, clearButton));
        });

        Runnable showEmptyCartView = () -> {
            content.getChildren().clear();
            Label emptyState = new Label("No items in the cart yet.");
            emptyState.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 13px;");
            HBox emptyButtons = new HBox(8, orderNowButton, clearButton);
            content.getChildren().addAll(title, emptyState, emptyButtons);
        };

        orderNowButton.setOnAction(event -> {
            com.app.model.observer.OrderObserver observer = new com.app.model.observer.OrderObserver() {
                @Override
                public void update(com.app.model.Order o, com.app.model.observer.OrderEvent ev) {
                    Platform.runLater(() -> {
                        progressBox.setVisible(true);
                        content.getChildren().clear();
                        content.getChildren().addAll(title, listBox, total, progressBox);
                        // reflect manager's current progress/status
                        progressBar.setProgress(com.app.model.OrderManager.getInstance().getProgress());
                        statusLabel.setText(com.app.model.OrderManager.getInstance().getStatus());
                        if (ev == com.app.model.observer.OrderEvent.SERVICE_DONE) {
                            // when finished, show empty cart
                            com.app.model.Cart.getInstance().clear();
                            showEmptyCartView.run();
                        }
                    });
                }
            };

            com.app.model.OrderManager manager = com.app.model.OrderManager.getInstance();
            manager.prepareOrder(items);
            manager.attachObserver(observer);
            manager.startCurrentOrder();
        });

        // If an order is already in progress, attach and show its progress so closing/reopening preserves state
        com.app.model.Order existing = com.app.model.OrderManager.getInstance().getCurrentOrder();
        if (existing != null) {
            orderNowButton.setDisable(true);
            com.app.model.observer.OrderObserver restoreObserver = new com.app.model.observer.OrderObserver() {
                @Override
                public void update(com.app.model.Order o, com.app.model.observer.OrderEvent ev) {
                    Platform.runLater(() -> {
                        progressBox.setVisible(true);
                        content.getChildren().clear();
                        content.getChildren().addAll(title, listBox, total, progressBox);
                        progressBar.setProgress(com.app.model.OrderManager.getInstance().getProgress());
                        statusLabel.setText(com.app.model.OrderManager.getInstance().getStatus());
                        if (ev == com.app.model.observer.OrderEvent.SERVICE_DONE) {
                            com.app.model.Cart.getInstance().clear();
                            showEmptyCartView.run();
                            orderNowButton.setDisable(false);
                        }
                    });
                }
            };
            com.app.model.OrderManager.getInstance().attachObserver(restoreObserver);
            // initialize UI to current progress
            progressBox.setVisible(true);
            content.getChildren().clear();
            content.getChildren().addAll(title, listBox, total, progressBox);
            progressBar.setProgress(com.app.model.OrderManager.getInstance().getProgress());
            statusLabel.setText(com.app.model.OrderManager.getInstance().getStatus());
        }

        if (items.isEmpty()) {
            Label emptyState = new Label("No items in the cart yet.");
            emptyState.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 13px;");
            HBox emptyButtons = new HBox(8, orderNowButton, clearButton);
            content.getChildren().addAll(title, emptyState, emptyButtons);
        } else {
            HBox buttons = new HBox(8, orderNowButton, clearButton);
            content.getChildren().addAll(title, listBox, total, buttons);
        }


        Scene scene = new Scene(content, 460, 380);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
