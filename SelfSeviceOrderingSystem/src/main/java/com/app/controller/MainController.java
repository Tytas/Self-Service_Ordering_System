package com.app.controller;

import com.app.model.Order;
import com.app.model.observer.GUIObserver;
import com.app.model.observer.TerminalObserver;
import com.app.model.product.Product;
import com.app.model.product.dessert.DessertCreator;
import com.app.model.product.drink.Drink;
import com.app.model.product.drink.DrinkCreator;
import com.app.model.product.drink.drinkDecorator.AlmondMilkDecorator;
import com.app.model.product.drink.drinkDecorator.BiggerSizeDecorator;
import com.app.model.product.drink.drinkDecorator.CoconutMilkDecorator;
import com.app.model.product.drink.drinkDecorator.CreamDecorator;
import com.app.model.product.drink.drinkDecorator.LactoseFreeMilkDecorator;
import com.app.model.product.drink.drinkDecorator.OatMilkDecorator;
import com.app.model.product.drink.drinkDecorator.SugarDecorator;

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
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class MainController {

    // ═══════════════ FXML Bindings ═══════════════

    @FXML private ListView<String> orderListView;
    @FXML private Label totalLabel;
    @FXML private Label statusLabel;
    @FXML private VBox drinksBox;
    @FXML private VBox dessertsBox;
    @FXML private CheckBox sugarCheck;
    @FXML private CheckBox cremeCheck;
    @FXML private CheckBox almondMilkCheck;
    @FXML private CheckBox coconutMilkCheck;
    @FXML private CheckBox lactoseFreeMilkCheck;
    @FXML private CheckBox oatMilkCheck;
    @FXML private CheckBox biggerSizeCheck;
    @FXML private ProgressBar progressBar;

    // ═══════════════ State ═══════════════

    private final DrinkCreator drinkCreator = new DrinkCreator();
    private final DessertCreator dessertCreator = new DessertCreator();
    private final Order order = new Order();
    private GUIObserver guiObserver;

    private final Map<String, String> drinkCatalog = Map.ofEntries(
        Map.entry("Affogato", "Affogato"),
        Map.entry("Americano", "Americano"),
        Map.entry("Apple Juice", "AppleJuice"),
        Map.entry("Caffe Latte", "CaffeLatte"),
        Map.entry("Caffe Mocha", "CaffeMocha"),
        Map.entry("Cappuccino", "Cappuccino"),
        Map.entry("Caramel Latte", "CaramelLatte"),
        Map.entry("Chai Tea Latte", "ChaiTeaLatte"),
        Map.entry("Cola", "Cola"),
        Map.entry("Cold Brew", "ColdBrew"),
        Map.entry("Espresso", "Espresso"),
        Map.entry("Fanta", "Fanta"),
        Map.entry("Filter Coffee", "FilterCoffee"),
        Map.entry("Hot Chocolate", "HotChocolate"),
        Map.entry("Iced Caffe Latte", "IcedCaffeLatte"),
        Map.entry("Iced Chai Tea Latte", "IcedChaiTeaLatte"),
        Map.entry("Iced Mocha", "IcedMocha"),
        Map.entry("Iced Tea", "IcedTea"),
        Map.entry("Lemon Juice", "LemonJuice"),
        Map.entry("Lemon Soda", "LemonSoda"),
        Map.entry("Orange Juice", "OrangeJuice"),
        Map.entry("Pineapple Juice", "PineappleJuice"),
        Map.entry("Sparkling Water", "SparklingWater"),
        Map.entry("Sprite", "Sprite"),
        Map.entry("Water", "Water")
    );
    private final List<String> dessertCatalog = List.of(
        "San Sebastian",
        "Pistachio Croissant",
        "Blueberry Tart",
        "Oreo Cake",
        "KitKat Cake",
        "Chocolate Strawberry Cake",
        "Tiramisu",
        "Banana Cake Roll with Chocolate",
        "Hazelnut Croquant",
        "Paris Brest",
        "Strawberry Almond Custard",
        "Lotus Pudding",
        "Creamy Waffle",
        "Brownie",
        "Chocolate Souffle"
    );

    // ═══════════════ Initialization ═══════════════

    @FXML
    public void initialize() {
        updateStatus("Ready");
        order.addObserver(new TerminalObserver());
        guiObserver = new GUIObserver(statusLabel, progressBar);
        order.addObserver(guiObserver);

        drinksBox.getChildren().clear();
        drinkCatalog.forEach((label, type) -> drinksBox.getChildren().add(createProductButton(label, type, "drink")));

        dessertsBox.getChildren().clear();
        dessertCatalog.forEach(type -> dessertsBox.getChildren().add(createProductButton(type, type, "dessert")));
    }

    // ═══════════════ Handlers ═══════════════

    @FXML
    private void onAddProduct(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String type = (String) sourceButton.getUserData();
        String category = (String) sourceButton.getProperties().get("category");

        try {
            Product product = createProduct(type, category);
            product = applyDecorators(product);
            addToOrder(product);
            updateStatus(product.getName() + " added to the order.");
        } catch (IllegalArgumentException e) {
            updateStatus("Error: " + e.getMessage());
        }
    }

    @FXML
    private void onClearOrder() {
        order.clear();
        orderListView.getItems().clear();
        totalLabel.setText("0 ₺");
        guiObserver.reset();
        updateStatus("Order cleared.");
    }

    @FXML
    private void onConfirmOrder() {
        if (order.getItems().isEmpty()) {
            updateStatus("Your order is empty!");
            return;
        }
        order.confirm();

        Timeline preparation = new Timeline(
            new KeyFrame(Duration.seconds(30), e -> order.preparationDone())
        );

        Timeline service = new Timeline(
            new KeyFrame(Duration.seconds(10), e -> order.serviceDone())
        );

        preparation.setOnFinished(e -> service.play());
        preparation.play();
        updateStatus("Order confirmed! Total: " + order.getTotalPrice() + " ₺");
    }

    // ═══════════════ Helpers ═══════════════

    private Button createProductButton(String label, String type, String category) {
        Button button = new Button(label);
        button.setUserData(type);
        button.getProperties().put("category", category);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-background-radius: 4;");
        button.setOnAction(this::onAddProduct);
        return button;
    }

    private Product createProduct(String type, String category) {
        if ("drink".equals(category)) {
            return drinkCreator.createProduct(type);
        } else if ("dessert".equals(category)) {
            return dessertCreator.createProduct(type);
        }
        throw new IllegalArgumentException("Unknown category: " + category);
    }

    private Product applyDecorators(Product product) {
        if (!(product instanceof Drink drink)) {
            return product;
        }

        if (sugarCheck.isSelected()) {
            product = new SugarDecorator(drink);
            drink = (Drink) product;
        }
        if (cremeCheck.isSelected()) {
            product = new CreamDecorator(drink);
            drink = (Drink) product;
        }
        if (almondMilkCheck.isSelected()) {
            product = new AlmondMilkDecorator(drink);
            drink = (Drink) product;
        }
        if (coconutMilkCheck.isSelected()) {
            product = new CoconutMilkDecorator(drink);
            drink = (Drink) product;
        }
        if (lactoseFreeMilkCheck.isSelected()) {
            product = new LactoseFreeMilkDecorator(drink);
            drink = (Drink) product;
        }
        if (oatMilkCheck.isSelected()) {
            product = new OatMilkDecorator(drink);
            drink = (Drink) product;
        }
        if (biggerSizeCheck.isSelected()) {
            product = new BiggerSizeDecorator(drink);
        }
        return product;
    }

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
