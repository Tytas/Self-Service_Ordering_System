package com.app.model;
import com.app.model.product.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
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
}