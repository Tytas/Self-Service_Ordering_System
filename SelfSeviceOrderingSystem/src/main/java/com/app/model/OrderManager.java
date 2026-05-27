package com.app.model;

import com.app.model.observer.OrderObserver;
import com.app.model.product.Product;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class OrderManager {
    private static final OrderManager INSTANCE = new OrderManager();
    private volatile Order currentOrder;
    private volatile double progress = 0.0;
    private volatile String status = "Ready";
    private final ExecutorService executor;

    private OrderManager() {}

    {
        ThreadFactory tf = r -> {
            Thread t = new Thread(r, "OrderProcessor");
            t.setDaemon(true);
            return t;
        };
        executor = Executors.newSingleThreadExecutor(tf);
    }

    public static OrderManager getInstance() {
        return INSTANCE;
    }

    public void prepareOrder(List<Product> items) {
        currentOrder = new Order();
        items.forEach(currentOrder::addProduct);
        progress = 0.0;
        status = "Order prepared";
    }

    public void attachObserver(OrderObserver observer) {
        if (currentOrder != null) {
            currentOrder.addObserver(observer);
        }
    }

    public void startCurrentOrder() {
        if (currentOrder == null) {
            status = "No order";
            return;
        }

        // Run order progression on a background executor to avoid blocking the UI
        executor.submit(() -> {
            try {
                currentOrder.confirm();
                progress = 0.33;
                status = "Order confirmed";

                // wait 10 seconds before marking preparation done
                Thread.sleep(10_000);

                currentOrder.preparationDone();
                progress = 0.66;
                status = "Preparation done";

                // wait 5 seconds before marking service done
                Thread.sleep(5_000);

                currentOrder.serviceDone();
                progress = 1.0;
                status = "Service done";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                status = "Interrupted";
            }
        });
    }

    public void resetCurrentOrder() {
        if (currentOrder != null) {
            currentOrder.clearObservers();
        }
        currentOrder = null;
        progress = 0.0;
        status = "Ready";
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public double getProgress() {
        return progress;
    }

    public String getStatus() {
        return status;
    }
}
