package com.app.view;

import com.app.controller.UIController;
import com.app.model.Cart;
import com.app.model.Order;
import com.app.model.OrderManager;
import com.app.model.product.Product;
import com.app.model.observer.GUIObserver;
import com.app.model.observer.TerminalObserver;
import com.app.model.observer.OrderObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class AppView {
    private Stage primaryStage;
    private static final Logger LOGGER = Logger.getLogger(AppView.class.getName());

    public void show(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        root.getStyleClass().add("app-root");

        UIController controller = new UIController(root);
        root.setTop(createHeader());
        controller.showMenu();

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/app.css").toExternalForm());

        primaryStage.setTitle("Self-Service Ordering System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox(12);
        header.getStyleClass().add("header");

        Label title = new Label("Self-Service Ordering System");
        title.getStyleClass().add("title-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button cartButton = new Button("🛒 Cart");
        cartButton.getStyleClass().addAll("button", "cart-button");
        cartButton.setOnAction(event -> showCartModal());

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
        content.getStyleClass().add("modal-content");

        Label title = new Label("Order Summary");
        title.getStyleClass().add("title-modal");

        java.util.List<Product> items = Cart.getInstance().getItems();

        Button orderNowButton = new Button("✓ Order Now");
        orderNowButton.getStyleClass().addAll("button", "primary-button");
        orderNowButton.setDisable(items.isEmpty());

        // Progress UI inside cart modal
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(360);
        progressBar.getStyleClass().add("progress-bar");
        Label statusLabel = new Label();
        statusLabel.getStyleClass().add("progress-label");
        VBox progressBox = new VBox(8, statusLabel, progressBar);
        progressBox.setVisible(false);

        // prepare list and total so handler can reuse
        VBox listBox = new VBox(8);
        for (com.app.model.product.Product it : items) {
            Label item = new Label(it.getName() + " - " + it.getPrice());
            item.getStyleClass().add("item-label");
            Label desc = new Label(it.getDescription());
            desc.getStyleClass().add("description-label");
            listBox.getChildren().addAll(item, desc);
        }
        Label total = new Label("Total: " + Cart.getInstance().getTotalPrice() + " TL");
        total.getStyleClass().add("total-label");

        Button clearButton = new Button("🗑️ Clear");
        clearButton.getStyleClass().addAll("button", "danger-button");
        clearButton.setOnAction(e -> {
            Cart.getInstance().clear();
            OrderManager.getInstance().resetCurrentOrder();
            orderNowButton.setDisable(true);
            progressBox.setVisible(false);
            content.getChildren().clear();
            Label emptyState = new Label("Cart is empty.");
            emptyState.getStyleClass().add("empty-state-label");
            HBox emptyButtons = new HBox(8, orderNowButton, clearButton);
            content.getChildren().addAll(title, emptyState, emptyButtons);
        });

        Runnable showEmptyCartView = () -> {
            content.getChildren().clear();
            Label emptyState = new Label("Cart is empty.");
            emptyState.getStyleClass().add("empty-state-label");
            HBox emptyButtons = new HBox(8, orderNowButton, clearButton);
            content.getChildren().addAll(title, emptyState, emptyButtons);
        };

        orderNowButton.setOnAction(event -> {
                OrderManager manager = OrderManager.getInstance();
            // prepare and show progress UI
            content.getChildren().clear();
            content.getChildren().addAll(title, listBox, total, progressBox);
            progressBox.setVisible(true);

                try {
                manager.prepareOrder(items);
                // attach GUI observer to update status label and progress bar
                GUIObserver guiObserver = new GUIObserver(statusLabel, progressBar);
                manager.attachObserver(guiObserver);
                // attach terminal observer to log to console
                manager.attachObserver(new TerminalObserver());
                // attach a small observer to clear cart and restore UI when service is done
                manager.attachObserver(new ServiceDoneObserver(showEmptyCartView, orderNowButton, manager));
                manager.startCurrentOrder();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Failed to start order", ex);
                Platform.runLater(() -> {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to start order");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                });
            }
        });

        // If an order is already in progress, attach and show its progress so closing/reopening preserves state
        Order existing = OrderManager.getInstance().getCurrentOrder();
        if (existing != null) {
            orderNowButton.setDisable(true);
            // attach GUI and terminal observers to reflect current order progress
            GUIObserver guiObserver = new GUIObserver(statusLabel, progressBar);
            OrderManager.getInstance().attachObserver(guiObserver);
            OrderManager.getInstance().attachObserver(new TerminalObserver());
            // attach small observer to clear cart when service completes
            OrderManager.getInstance().attachObserver(new ServiceDoneObserver(showEmptyCartView, orderNowButton, OrderManager.getInstance()));
            // initialize UI to current progress
            progressBox.setVisible(true);
            content.getChildren().clear();
            content.getChildren().addAll(title, listBox, total, progressBox);
            progressBar.setProgress(OrderManager.getInstance().getProgress());
            statusLabel.setText(OrderManager.getInstance().getStatus());
        }

        if (items.isEmpty()) {
            Label emptyState = new Label("Cart is empty.");
            emptyState.getStyleClass().add("empty-state-label");
            HBox emptyButtons = new HBox(8, orderNowButton, clearButton);
            content.getChildren().addAll(title, emptyState, emptyButtons);
        } else {
            HBox buttons = new HBox(8, orderNowButton, clearButton);
            content.getChildren().addAll(title, listBox, total, buttons);
        }


        Scene scene = new Scene(content, 460, 380);
        scene.getStylesheets().add(AppView.class.getResource("/styles/app.css").toExternalForm());
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private static class ServiceDoneObserver implements OrderObserver {
        private final Runnable showEmptyCartView;
        private final Button orderNowButton;
        private final OrderManager manager;

        ServiceDoneObserver(Runnable showEmptyCartView, Button orderNowButton, OrderManager manager) {
            this.showEmptyCartView = showEmptyCartView;
            this.orderNowButton = orderNowButton;
            this.manager = manager;
        }

        @Override
        public void update(Order o, com.app.model.observer.OrderEvent ev) {
            if (ev == com.app.model.observer.OrderEvent.SERVICE_DONE) {
                Platform.runLater(() -> {
                    Cart.getInstance().clear();
                    manager.resetCurrentOrder();
                    showEmptyCartView.run();
                    orderNowButton.setDisable(true);
                });
            }
        }
    }
}
