package com.app.model.product.dessert.dessertDecorator;
import com.app.model.product.dessert.Dessert;

public class BitterChocolateDecorator extends dessertDecorator {
    private static final int BITTER_CHOCOLATE_PRICE_INCREASE = 3;

    public BitterChocolateDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + BITTER_CHOCOLATE_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Bitter Chocolate";
    }
}
