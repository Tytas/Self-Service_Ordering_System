package com.app.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

import com.app.model.product.Product;
import com.app.model.product.dessert.Dessert;
import com.app.model.product.dessert.DessertCreator;
import com.app.model.product.dessert.dessertDecorator.BitterChocolateDecorator;
import com.app.model.product.dessert.dessertDecorator.IceCreamDecorator;
import com.app.model.product.dessert.dessertDecorator.MilkChocolateDecorator;
import com.app.model.product.dessert.dessertDecorator.WhiteChocolateDecorator;
import com.app.model.product.drink.Drink;
import com.app.model.product.drink.DrinkCreator;
import com.app.model.product.drink.drinkDecorator.CaramelDecorator;
import com.app.model.product.drink.drinkDecorator.CreamDecorator;
import com.app.model.product.drink.drinkDecorator.SugarDecorator;


/**
 * Singleton that manages the currently active order and its progress timeline.
 * UI can query getCurrentOrder(), add observers and read progress/status.
 */
public class OrderManager {
    private static final OrderManager INSTANCE = new OrderManager();

    private Order currentOrder;
    private Timeline timeline;
    private double progress = 0.0; // 0..1
    private String status = "";

    private OrderManager() {}

    public static OrderManager getInstance() {
        return INSTANCE;
    }

    public synchronized Order getCurrentOrder() {
        return currentOrder;
    }

    public synchronized double getProgress() {
        return progress;
    }

    public synchronized String getStatus() {
        return status;
    }

    public synchronized void resetCurrentOrder() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        currentOrder = null;
        progress = 0.0;
        status = "";
    }

    public synchronized Order prepareOrder(List<CartItem> cartItems) {
        // stop previous timeline if any
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        currentOrder = new Order();
        // initial state
        progress = 0.0;
        status = "Your order is being prepared";

        if (cartItems != null) {
            for (CartItem item : cartItems) {
                Product product = buildProduct(item);
                if (product != null) {
                    currentOrder.addProduct(product);
                }
            }
        }

        return currentOrder;
    }

    public synchronized Order startCurrentOrder() {
        if (currentOrder == null) {
            return null;
        }

        // notify observers that order confirmed
        currentOrder.confirm();

        // timeline to update status/progress at 0s,10s,20s,30s
        timeline = new Timeline(
            new KeyFrame(Duration.seconds(0), e -> updateProgress(0.25, "Your order is being prepared")),
            new KeyFrame(Duration.seconds(10), e -> {
                updateProgress(0.5, "Your order is ready");
                currentOrder.preparationDone();
            }),
            new KeyFrame(Duration.seconds(20), e -> {
                updateProgress(0.75, "Your order is on the way");
                currentOrder.onTheWay();
            }),
            new KeyFrame(Duration.seconds(30), e -> {
                updateProgress(1.0, "Your order has been delivered");
                currentOrder.serviceDone();
                currentOrder = null;
                timeline = null;
            })
        );
        timeline.play();

        return currentOrder;
    }

    private Product buildProduct(CartItem item) {
        Product product = null;

        try {
            product = new DrinkCreator().createProduct(item.getName());
        } catch (IllegalArgumentException drinkError) {
            try {
                product = new DessertCreator().createProduct(item.getName());
            } catch (IllegalArgumentException dessertError) {
                return null;
            }
        }

        for (String addon : item.getAddons()) {
            String addonName = normalizeAddonName(addon);
            if (product instanceof Dessert dessert) {
                product = applyDessertAddon(dessert, addonName);
            } else if (product instanceof Drink drink) {
                product = applyDrinkAddon(drink, addonName);
            }
        }

        return product;
    }

    private String normalizeAddonName(String addon) {
        int idx = addon.indexOf(" (+");
        if (idx > 0) {
            return addon.substring(0, idx).trim();
        }
        return addon.trim();
    }

    private Product applyDessertAddon(Dessert dessert, String addonName) {
        return switch (addonName) {
            case "Milk Chocolate" -> new MilkChocolateDecorator(dessert);
            case "Bitter Chocolate" -> new BitterChocolateDecorator(dessert);
            case "White Chocolate" -> new WhiteChocolateDecorator(dessert);
            case "Ice Cream" -> new IceCreamDecorator(dessert);
            default -> dessert;
        };
    }

    private Product applyDrinkAddon(Drink drink, String addonName) {
        return switch (addonName) {
            case "Caramel" -> new CaramelDecorator(drink);
            case "Sugar" -> new SugarDecorator(drink);
            case "Cream" -> new CreamDecorator(drink);
            default -> drink;
        };
    }

    private synchronized void updateProgress(double p, String newStatus) {
        this.progress = p;
        this.status = newStatus;
        // notify UI-level observers via Order events when appropriate (already done where needed)
        // Ensure UI updates occur on FX thread when observers respond.
        // Observers attached to the Order will be notified by the OrderManager where we called Order methods.
    }

    public synchronized void attachObserver(com.app.model.observer.OrderObserver observer) {
        if (currentOrder != null) currentOrder.addObserver(observer);
    }

    public synchronized void detachObserver(com.app.model.observer.OrderObserver observer) {
        if (currentOrder != null) currentOrder.removeObserver(observer);
    }
}
