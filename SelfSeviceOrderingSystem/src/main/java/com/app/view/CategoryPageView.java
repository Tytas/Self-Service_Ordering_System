package com.app.view;

import com.app.model.menu.MenuCategory;
import com.app.model.product.Product;
import com.app.model.product.dessert.Dessert;
import com.app.model.product.drink.Drink;
import com.app.model.product.dessert.dessertDecorator.BitterChocolateDecorator;
import com.app.model.product.dessert.dessertDecorator.IceCreamDecorator;
import com.app.model.product.dessert.dessertDecorator.MilkChocolateDecorator;
import com.app.model.product.dessert.dessertDecorator.WhiteChocolateDecorator;
import com.app.model.product.drink.drinkDecorator.CaramelDecorator;
import com.app.model.product.drink.drinkDecorator.CreamDecorator;
import com.app.model.product.drink.drinkDecorator.SugarDecorator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CategoryPageView {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CategoryPageView.class.getName());
    public ScrollPane build(MenuCategory category, List<Product> products, Runnable onBack, Consumer<Product> onProductSelected) {
        VBox container = new VBox(18);
        container.setPadding(new Insets(24));

        HBox topRow = new HBox(12);
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: #374151; -fx-text-fill: #f3f4f6; -fx-border-color: #4b5563; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #4b5563; -fx-text-fill: #f3f4f6; -fx-border-color: #10b981; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #374151; -fx-text-fill: #f3f4f6; -fx-border-color: #4b5563; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
        backButton.setOnAction(event -> onBack.run());

        Label title = new Label(category.getDisplayName());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #f3f4f6;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        topRow.getChildren().addAll(backButton, title, spacer);


        container.getChildren().add(topRow);

        // Partition products into logical sections depending on category
        Map<String, List<Product>> sections = partitionIntoSections(category, products);

        for (Map.Entry<String, List<Product>> entry : sections.entrySet()) {
            String sectionTitle = entry.getKey();
            List<Product> sectionProducts = entry.getValue();

            if (sectionProducts.isEmpty()) continue;

            Label sectionLabel = new Label(sectionTitle);
            sectionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 8 0 6 0; -fx-text-fill: #f3f4f6;");

            FlowPane grid = new FlowPane();
            grid.setHgap(18);
            grid.setVgap(18);
            // Use a fixed wrap length so the page does not stretch with the parent window.
            grid.setPrefWrapLength(900);

            for (Product product : sectionProducts) {
                grid.getChildren().add(ProductCardView.build(product, onProductSelected, sectionTitle));
            }

            container.getChildren().addAll(sectionLabel, grid);
        }

        ScrollPane scroll = new ScrollPane(container);
        scroll.setFitToWidth(true);
        return scroll;
    }

    public static void showProductDialog(Stage owner, MenuCategory category, Product product) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        if (owner != null) {
            dialog.initOwner(owner);
        }
        dialog.setResizable(false);
        dialog.setAlwaysOnTop(true);
        dialog.setTitle(product.getName());

        VBox content = new VBox(12);
        content.setPadding(new Insets(16));
        content.setStyle("-fx-background-color: #1f2937;");
        content.setAlignment(Pos.TOP_LEFT);

        Label title = new Label(product.getName());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #f3f4f6;");

        Label desc = new Label(product.getDescription());
        desc.setWrapText(true);
        desc.setStyle("-fx-text-fill: #d1d5db;");

        Label priceLabel = new Label("Price: " + product.getPrice() + " TL");
        priceLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #10b981; -fx-font-size: 14px;");

        Label addonsLabel = new Label("Add-ons");
        addonsLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8 0 4 0; -fx-text-fill: #f3f4f6;");

        VBox addonsBox = new VBox(6);

        // Determine available add-ons for this product
        List<AddOn> addons = determineAddOnsForProduct(category, product);

        final int[] extraTotal = {0};
        for (AddOn a : addons) {
            javafx.scene.control.CheckBox cb = new javafx.scene.control.CheckBox(a.label + " (+" + a.price + ")");
            cb.setStyle("-fx-text-fill: #d1d5db;");
            cb.selectedProperty().addListener((obs, oldV, newV) -> {
                if (newV) extraTotal[0] += a.price;
                else extraTotal[0] -= a.price;
                priceLabel.setText("Price: " + (product.getPrice() + extraTotal[0]) + " TL");
            });
            addonsBox.getChildren().add(cb);
        }

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #374151; -fx-text-fill: #f3f4f6; -fx-border-color: #4b5563; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;");
        closeButton.setOnMouseEntered(e -> closeButton.setStyle("-fx-background-color: #4b5563; -fx-text-fill: #f3f4f6; -fx-border-color: #4b5563; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
        closeButton.setOnMouseExited(e -> closeButton.setStyle("-fx-background-color: #374151; -fx-text-fill: #f3f4f6; -fx-border-color: #4b5563; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
        closeButton.setOnAction(e -> dialog.close());

        Button addToCart = new Button("✓ Add to cart");
        addToCart.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;");
        addToCart.setOnMouseEntered(e -> addToCart.setStyle("-fx-background-color: #059669; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
        addToCart.setOnMouseExited(e -> addToCart.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
        addToCart.setOnAction(e -> {
            // collect selected addons
            List<String> selected = new ArrayList<>();
            for (javafx.scene.Node n : addonsBox.getChildren()) {
                if (n instanceof javafx.scene.control.CheckBox cb && cb.isSelected()) {
                    String text = cb.getText();
                    int idx = text.indexOf(" (");
                    selected.add(idx > 0 ? text.substring(0, idx) : text);
                }
            }
            // decorate product with selected addons and add to cart
            Product cartProduct = product;
            if (!selected.isEmpty()) {
                if (category == MenuCategory.DESSERTS && product instanceof Dessert baseDessert) {
                    for (String addon : selected) {
                        switch (addon) {
                            case "Milk Chocolate" -> baseDessert = new MilkChocolateDecorator(baseDessert);
                            case "Bitter Chocolate" -> baseDessert = new BitterChocolateDecorator(baseDessert);
                            case "White Chocolate" -> baseDessert = new WhiteChocolateDecorator(baseDessert);
                            case "Ice Cream" -> baseDessert = new IceCreamDecorator(baseDessert);
                        }
                    }
                    cartProduct = baseDessert;
                } else if ((category == MenuCategory.HOT_DRINKS || category == MenuCategory.COLD_DRINKS) && product instanceof Drink baseDrink) {
                    for (String addon : selected) {
                        switch (addon) {
                            case "Caramel" -> baseDrink = new CaramelDecorator(baseDrink);
                            case "Sugar" -> baseDrink = new SugarDecorator(baseDrink);
                            case "Cream" -> baseDrink = new CreamDecorator(baseDrink);
                        }
                    }
                    cartProduct = baseDrink;
                }
            }
            com.app.model.Cart.getInstance().addProduct(cartProduct);

            Label addedMessage = new Label("✓ Your order has been added to the cart.");
            addedMessage.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #10b981;");

            Button goToCartButton = new Button("🛒 Go to cart");
            goToCartButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;");
            goToCartButton.setOnMouseEntered(evt -> goToCartButton.setStyle("-fx-background-color: #059669; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
            goToCartButton.setOnMouseExited(evt -> goToCartButton.setStyle("-fx-background-color: #10b981; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;"));
            goToCartButton.setOnAction(goEvent -> {
                dialog.close();
                // debug: log cart size to ensure product was added
                LOGGER.fine(() -> "DEBUG: cart size before opening modal = " + com.app.model.Cart.getInstance().getItems().size());
                // ensure cart modal opens after the current dialog fully closes to avoid timing issues
                javafx.application.Platform.runLater(() -> com.app.view.AppView.openCartModal(owner));
            });

            VBox confirmation = new VBox(14, addedMessage, goToCartButton);
            confirmation.setAlignment(Pos.CENTER);            confirmation.setPadding(new Insets(20));
            content.getChildren().clear();
            content.getChildren().addAll(title, confirmation);
        });
        closeButton.setStyle("-fx-background-color: #111827; -fx-text-fill: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-font-weight: bold;");

        content.getChildren().addAll(title, desc, priceLabel, addonsLabel);
        if (!addons.isEmpty()) content.getChildren().add(addonsBox);
        else content.getChildren().add(new Label("No add-ons available for this item."));
        HBox buttons = new HBox(8, addToCart, closeButton);
        content.getChildren().add(buttons);

        Scene scene = new Scene(content, 420, 340);
        dialog.setScene(scene);

        dialog.setMinWidth(420);
        dialog.setMinHeight(340);
        dialog.setMaxWidth(420);
        dialog.setMaxHeight(340);

        dialog.setOnShown(event -> {
            if (owner != null) {
                dialog.setX(owner.getX() + (owner.getWidth() - dialog.getWidth()) / 2.0);
                dialog.setY(owner.getY() + (owner.getHeight() - dialog.getHeight()) / 2.0);
            } else {
                dialog.centerOnScreen();
            }
        });

        dialog.showAndWait();
    }

    private static List<AddOn> determineAddOnsForProduct(MenuCategory category, Product product) {
        String name = product.getName().toLowerCase();
        List<AddOn> list = new ArrayList<>();

        // Dessert decorators
        AddOn milkChoc = new AddOn("Milk Chocolate", 20);
        AddOn bitterChoc = new AddOn("Bitter Chocolate", 22);
        AddOn whiteChoc = new AddOn("White Chocolate", 20);
        AddOn iceCream = new AddOn("Ice Cream", 35);

        // Drink decorators
        AddOn caramel = new AddOn("Caramel", 10);
        AddOn sugar = new AddOn("Sugar", 5);
        AddOn cream = new AddOn("Cream", 15);

        if (category == MenuCategory.DESSERTS) {
            list.add(milkChoc);
            list.add(bitterChoc);
            list.add(whiteChoc);
            list.add(iceCream);
            return list;
        }

        if (category == MenuCategory.HOT_DRINKS || category == MenuCategory.COLD_DRINKS) {
            if (name.contains("juice") || name.contains("water")) {
                return list;
            }
            list.add(caramel);
            list.add(sugar);
            list.add(cream);
            return list;
        }

        return list;
    }

    private static Map<String, List<Product>> partitionIntoSections(MenuCategory category, List<Product> products) {
        Map<String, List<Product>> sections = new LinkedHashMap<>();
        if (category == MenuCategory.HOT_DRINKS) {
            sections.put("Coffee with milk", new ArrayList<>());
            sections.put("Coffee", new ArrayList<>());
            for (Product p : products) {
                String name = p.getName().toLowerCase();
                if (name.contains("latte") || name.contains("cappuccino") || name.contains("mocha")) sections.get("Coffee with milk").add(p);
                else sections.get("Coffee").add(p);
            }
            return sections;
        }

        if (category == MenuCategory.COLD_DRINKS) {
            sections.put("Iced Coffee", new ArrayList<>());
            sections.put("Juice", new ArrayList<>());
            sections.put("Water", new ArrayList<>());
            sections.put("Other Drinks", new ArrayList<>());
            for (Product p : products) {
                String name = p.getName().toLowerCase();
                if (name.contains("iced") || name.contains("cold brew")) sections.get("Iced Coffee").add(p);
                else if (name.contains("juice")) sections.get("Juice").add(p);
                else if (name.contains("water")) sections.get("Water").add(p);
                else sections.get("Other Drinks").add(p);
            }
            return sections;
        }

        // Default: single "Products" section
        sections.put("Products", new ArrayList<>(products));
        return sections;
    }

    private static class AddOn {
        final String label;
        final int price;

        AddOn(String label, int price) {
            this.label = label;
            this.price = price;
        }
    }
}