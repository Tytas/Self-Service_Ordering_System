package com.app.model.product.dessert.dessertDecorator;

import com.app.model.product.dessert.Dessert;

public class BitterChocolateDecorator extends dessertDecorator {
    private static final int EXTRA_PRICE = 22;

    public BitterChocolateDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + EXTRA_PRICE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Bitter Chocolate";
    }
}
