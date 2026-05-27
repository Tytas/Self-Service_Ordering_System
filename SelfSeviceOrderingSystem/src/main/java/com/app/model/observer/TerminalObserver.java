package com.app.model.observer;

import com.app.model.Order;

public class TerminalObserver implements OrderObserver {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(TerminalObserver.class.getName());

    @Override
    public void update(Order order, OrderEvent event) {
        switch (event) {
            case ORDER_CONFIRMED -> {
                LOGGER.info("=== New order confirmed ===");
                order.getItems().forEach(p ->
                    LOGGER.info("  - " + p.getName() + " : " + p.getPrice() + " TL")
                );
                LOGGER.info("  Total: " + order.getTotalPrice() + " TL");
            }
            case PREPARATION_DONE ->
                LOGGER.info("Order ready - awaiting service...");
            case SERVICE_DONE ->
                LOGGER.info("Order served at table. Enjoy your meal!");
        }
    }
}
