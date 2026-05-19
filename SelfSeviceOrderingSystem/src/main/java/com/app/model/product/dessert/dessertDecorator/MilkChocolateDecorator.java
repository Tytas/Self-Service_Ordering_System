package com.app.model.product.dessert.dessertDecorator;

import com.app.model.product.dessert.Dessert;

public class MilkChocolateDecorator extends dessertDecorator {
    private static final int EXTRA_PRICE = 20;

    public MilkChocolateDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + EXTRA_PRICE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Milk Chocolate";
    }
}
