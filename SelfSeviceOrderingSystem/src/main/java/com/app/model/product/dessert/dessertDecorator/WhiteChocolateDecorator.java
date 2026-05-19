package com.app.model.product.dessert.dessertDecorator;

import com.app.model.product.dessert.Dessert;

public class WhiteChocolateDecorator extends dessertDecorator {
    private static final int EXTRA_PRICE = 20;

    public WhiteChocolateDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + EXTRA_PRICE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + White Chocolate";
    }
}
