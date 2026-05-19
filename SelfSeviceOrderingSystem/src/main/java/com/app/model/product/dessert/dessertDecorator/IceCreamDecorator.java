package com.app.model.product.dessert.dessertDecorator;

import com.app.model.product.dessert.Dessert;

public class IceCreamDecorator extends dessertDecorator {
    private static final int EXTRA_PRICE = 35;

    public IceCreamDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + EXTRA_PRICE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Ice Cream";
    }
}
