package com.app.controller;

import com.app.model.Order;
import com.app.model.product.Product;
import com.app.model.product.drink.DrinkCreator;
import com.app.model.product.drink.drinkDecorator.SugarDecorator;
import com.app.model.product.drink.drinkDecorator.CreamDecorator;

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

    private final Order order = new Order();

    // ═══════════════ Initialization ═══════════════

    @FXML
    public void initialize() {
        updateStatus("Ready");
    }

    // ═══════════════ Handlers ═══════════════

    @FXML
    private void onAddDrink(ActionEvent event) {
        String type = (String) ((Button) event.getSource()).getUserData();

        try {
            Product drink = drinkCreator.createProduct(type);

            // Apply decorators according to the checkboxes
            if (sugarCheck.isSelected()) {
                drink = new SugarDecorator((com.app.model.product.drink.Drink) drink);
            }
            if (cremeCheck.isSelected()) {
                drink = new CreamDecorator((com.app.model.product.drink.Drink) drink);
            }

            addToOrder(drink);
            updateStatus(drink.getName() + " added to the order.");

        } catch (IllegalArgumentException e) {
            updateStatus("Error: " + e.getMessage());
        }
    }

    @FXML
    private void onClearOrder() {
        order.clear();
        orderListView.getItems().clear();
        totalLabel.setText("0 ₺");
        updateStatus("Order cleared.");
    }

    @FXML
    private void onConfirmOrder() {
        if (order.getItems().isEmpty()) {
            updateStatus("Your order is empty!");
            return;
        }
        // TODO: send the order to the service / database
        updateStatus("Order confirmed! Total: " + order.getTotalPrice() + " ₺");
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