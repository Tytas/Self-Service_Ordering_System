package com.app.model.observer;

import com.app.model.Order;

public class TerminalObserver implements OrderObserver {

    @Override
    public void update(Order order, OrderEvent event) {
        switch (event) {
            case ORDER_CONFIRMED -> {
                System.out.println("=== Nouvelle commande confirmée ===");
                order.getItems().forEach(p ->
                    System.out.println("  - " + p.getName() + " : " + p.getPrice() + " TL")
                );
                System.out.println("  Total : " + order.getTotalPrice() + " TL");
            }
            case PREPARATION_DONE ->
                System.out.println("✅ Commande prête ! En attente de service...");
            case ORDER_ON_THE_WAY ->
                System.out.println("🚚 Commande en route !");
            case SERVICE_DONE ->
                System.out.println("🍽️  Commande servie en table. Bon appétit !");
        }
    }
}
