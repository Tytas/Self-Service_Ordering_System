package com.app.model.product.drink;

import com.app.model.FoodCreator;
import com.app.model.product.Product;

public class DrinkCreator extends FoodCreator {

    public DrinkCreator() {
    }

    @Override
    public Product createProduct(String type) {
        if (type.equalsIgnoreCase("CaramelLatte")) {
            return new CaramelLatte();
        } else if (type.equalsIgnoreCase("Water")) {
            return new Water();
        } else {
            throw new IllegalArgumentException("Unknown drink type: " + type);
        }
    }
}
