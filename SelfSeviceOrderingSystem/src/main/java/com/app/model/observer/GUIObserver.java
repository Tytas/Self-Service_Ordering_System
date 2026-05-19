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
        // Platform.runLater garantit que la mise à jour se fait sur le thread JavaFX
        Platform.runLater(() -> {
            switch (event) {
                case ORDER_CONFIRMED -> {
                    statusLabel.setText("Commande confirmée — préparation en cours...");
                    statusLabel.setStyle("-fx-text-fill: #e67e22;");
                    progressBar.setProgress(0.33);
                }
                case PREPARATION_DONE -> {
                    statusLabel.setText("Commande prête — service en cours...");
                    statusLabel.setStyle("-fx-text-fill: #27ae60;");
                    progressBar.setProgress(0.66);
                }
                case SERVICE_DONE -> {
                    statusLabel.setText("Commande servie ! Bon appétit !");
                    statusLabel.setStyle("-fx-text-fill: #2980b9;");
                    progressBar.setProgress(1.0);
                }
            }
        });
    }

    public void reset() {
    Platform.runLater(() -> {
        statusLabel.setText("Prêt");
        statusLabel.setStyle("-fx-text-fill: #7f8c8d;");
        progressBar.setProgress(0.0);
    });
}
}
