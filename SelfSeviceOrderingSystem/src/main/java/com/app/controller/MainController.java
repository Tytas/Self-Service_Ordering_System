package com.app.controller;

import com.app.model.Order;
import com.app.model.observer.GUIObserver;
import com.app.model.observer.TerminalObserver;
import com.app.model.product.Product;
import com.app.model.product.drink.DrinkCreator;
import com.app.model.product.drink.drinkDecorator.SugarDecorator;
import com.app.model.product.drink.drinkDecorator.CremeDecorator;
import com.app.model.product.snack.SnackCreator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class MainController {

    // ═══════════════ FXML Bindings ═══════════════

    @FXML private ListView<String> orderListView;
    @FXML private Label totalLabel;
    @FXML private Label statusLabel;
    @FXML private CheckBox sugarCheck;
    @FXML private CheckBox cremeCheck;
    @FXML private ProgressBar progressBar;

    // ═══════════════ State ═══════════════

    private final DrinkCreator drinkCreator = new DrinkCreator();
    private final SnackCreator snackCreator = new SnackCreator();

    private final Order order = new Order();
    
    private GUIObserver guiObserver;

    // ═══════════════ Init ═══════════════

    @FXML
    public void initialize() {
        updateStatus("Prêt");
        order.addObserver(new TerminalObserver());
        guiObserver = new GUIObserver(statusLabel, progressBar);
        order.addObserver(guiObserver);
    }

    // ═══════════════ Handlers ═══════════════

    @FXML
    private void onAddDrink(ActionEvent event) {
        String type = (String) ((Button) event.getSource()).getUserData();

        try {
            Product drink = drinkCreator.createProduct(type);

            // Applique les décorateurs selon les checkboxes
            if (sugarCheck.isSelected()) {
                drink = new SugarDecorator((com.app.model.product.drink.Drink) drink);
            }
            if (cremeCheck.isSelected()) {
                drink = new CremeDecorator((com.app.model.product.drink.Drink) drink);
            }

            addToOrder(drink);
            updateStatus(drink.getName() + " ajouté à la commande.");

        } catch (IllegalArgumentException e) {
            updateStatus("Erreur : " + e.getMessage());
        }
    }

    @FXML
    private void onAddSnack(ActionEvent event) {
        String type = (String) ((Button) event.getSource()).getUserData();

        try {
            Product snack = snackCreator.createProduct(type);
            addToOrder(snack);
            updateStatus(snack.getName() + " ajouté à la commande.");

        } catch (IllegalArgumentException e) {
            updateStatus("Erreur : " + e.getMessage());
        }
    }

    @FXML
    private void onClearOrder() {
        order.clear();
        orderListView.getItems().clear();
        totalLabel.setText("0 ₺");
        guiObserver.reset();
        updateStatus("Commande vidée.");
    }

    @FXML
    private void onConfirmOrder() {
        if (order.getItems().isEmpty()) {
            updateStatus("Votre commande est vide !");
            return;
        }
        order.confirm(); // → ORDER_CONFIRMED

        Timeline preparation = new Timeline(
            new KeyFrame(Duration.seconds(30), e -> order.preparationDone())
        );

        Timeline service = new Timeline(
            new KeyFrame(Duration.seconds(10), e -> order.serviceDone())
        );

        preparation.setOnFinished(e -> service.play());
        preparation.play();
        updateStatus("Commande confirmée ! Total : " + order.getTotalPrice() + " ₺");
    }

    // ═══════════════ Helpers ═══════════════

    private void addToOrder(Product product) {
    order.addProduct(product);
    orderListView.getItems().add(
        product.getName() + "  —  " + product.getPrice() + " ₺");
    totalLabel.setText(order.getTotalPrice() + " ₺");
}

    private void updateStatus(String message) {
        statusLabel.setText(message);
    }
}