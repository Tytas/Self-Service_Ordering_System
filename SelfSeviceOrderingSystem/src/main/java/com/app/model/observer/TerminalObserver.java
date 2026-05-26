package com.app.model.observer;

import com.app.model.Order;

public class TerminalObserver implements OrderObserver {

    @Override
    public void update(Order order, OrderEvent event) {
        switch (event) {
            case ORDER_CONFIRMED -> {
                System.out.println("=== New order confirmed ===");
                order.getItems().forEach(p ->
                    System.out.println("  - " + p.getName() + " : " + p.getPrice() + " TL")
                );
                System.out.println("  Total: " + order.getTotalPrice() + " TL");
            }
            case PREPARATION_DONE ->
                System.out.println("Order ready - awaiting service...");
            case SERVICE_DONE ->
                System.out.println("Order served at table. Enjoy your meal!");
        }
    }
}
