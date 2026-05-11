package com.app.model.product.snack;

import com.app.model.FoodCreator;
import com.app.model.product.Product;

public class SnackCreator extends FoodCreator {

    public SnackCreator() {
    }

    @Override
    public Product createProduct(String type) {
        if (type.equalsIgnoreCase("Burger")) {
            return new Burger();
        } else if (type.equalsIgnoreCase("Panini")) {
            return new Panini();
        } else {
            throw new IllegalArgumentException("Unknown snack type: " + type);
        }
    }
}
