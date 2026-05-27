package com.app.model;
import com.app.model.product.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.app.model.observer.OrderEvent;
import com.app.model.observer.OrderObserver;
 
public class Order {
 
    // ═══════════════ Produits ═══════════════
 
    private final List<Product> items = new ArrayList<>();
 
    public void addProduct(Product product) {
        items.add(product);
    }
 
    public void clear() {
        items.clear();
    }
 
    public int getTotalPrice() {
        return items.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
 
    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }
 
    // ═══════════════ Observers ═══════════════
 
    private final List<OrderObserver> observers = new ArrayList<>();
 
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
 
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
 
    private void notifyObservers(OrderEvent event) {
        observers.forEach(o -> o.update(this, event));
    }

    /**
     * Remove all registered observers. Useful to avoid leaks when discarding this order.
     */
    public void clearObservers() {
        observers.clear();
    }
 
 
    public void confirm() {
        notifyObservers(OrderEvent.ORDER_CONFIRMED);
    }
 
    public void preparationDone() {
        notifyObservers(OrderEvent.PREPARATION_DONE);
    }
 
    public void serviceDone() {
        notifyObservers(OrderEvent.SERVICE_DONE);
    }
}