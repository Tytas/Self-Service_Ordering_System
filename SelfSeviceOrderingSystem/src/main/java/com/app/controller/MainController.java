package com.app.controller;

import com.app.model.Order;
import com.app.model.product.Product;
import com.app.model.product.drink.DrinkCreator;
import com.app.model.product.drink.drinkDecorator.SugarDecorator;
import com.app.model.product.drink.drinkDecorator.CremeDecorator;
import com.app.model.product.snack.SnackCreator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MainController {

    // ═══════════════ FXML Bindings ═══════════════

    @FXML private ListView<String> orderListView;
    @FXML private Label totalLabel;
    @FXML private Label statusLabel;
    @FXML private CheckBox sugarCheck;
    @FXML private CheckBox cremeCheck;

    // ═══════════════ State ═══════════════

    private final DrinkCreator drinkCreator = new DrinkCreator();
    private final SnackCreator snackCreator = new SnackCreator();

    private final Order order = new Order();

    // ═══════════════ Init ═══════════════

    @FXML
    public void initialize() {
        updateStatus("Prêt");
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
        updateStatus("Commande vidée.");
    }

    @FXML
    private void onConfirmOrder() {
        if (order.getItems().isEmpty()) {
            updateStatus("Votre commande est vide !");
            return;
        }
        // TODO : envoyer la commande au service / base de données
        updateStatus("Commande confirmée ! Total : " + order.getTotalPrice() + " ₺");
        onClearOrder();
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