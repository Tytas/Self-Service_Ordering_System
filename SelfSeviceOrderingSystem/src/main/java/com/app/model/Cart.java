package com.app.model;

import com.app.model.product.Product;
import java.util.List;

public class Cart {
    private static final Cart INSTANCE = new Cart();
    private final Order order = new Order();

    private Cart() {}

    public static Cart getInstance() { return INSTANCE; }

    public void addProduct(Product product) {
        order.addProduct(product);
    }

    public List<Product> getItems() {
        return order.getItems();
    }

    public Order getOrder() {
        return order;
    }

    public int getTotalPrice() {
        return order.getTotalPrice();
    }

    public void clear() {
        order.clear();
    }
}
