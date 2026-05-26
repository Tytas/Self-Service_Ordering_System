package com.app.model.observer;

import com.app.model.Order;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class GUIObserver implements OrderObserver {

    private final Label statusLabel;
    private final ProgressBar progressBar;

    public GUIObserver(Label statusLabel, ProgressBar progressBar) {
        this.statusLabel = statusLabel;
        this.progressBar = progressBar;
    }

    @Override
    public void update(Order order, OrderEvent event) {
        // Ensure UI updates run on the JavaFX Application Thread
        Platform.runLater(() -> {
            switch (event) {
                case ORDER_CONFIRMED -> {
                    statusLabel.setText("Order confirmed — preparation in progress...");
                    statusLabel.setStyle("-fx-text-fill: #e67e22;");
                    progressBar.setProgress(0.33);
                }
                case PREPARATION_DONE -> {
                    statusLabel.setText("Order ready — serving in progress...");
                    statusLabel.setStyle("-fx-text-fill: #27ae60;");
                    progressBar.setProgress(0.66);
                }
                case SERVICE_DONE -> {
                    statusLabel.setText("Order served. Enjoy your meal!");
                    statusLabel.setStyle("-fx-text-fill: #2980b9;");
                    progressBar.setProgress(1.0);
                }
            }
        });
    }

    public void reset() {
    Platform.runLater(() -> {
        statusLabel.setText("Ready");
        statusLabel.setStyle("-fx-text-fill: #7f8c8d;");
        progressBar.setProgress(0.0);
    });
}
}
