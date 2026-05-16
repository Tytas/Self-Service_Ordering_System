package com.app.model.product.dessert.dessertDecorator;
import com.app.model.product.dessert.Dessert;

public class WhiteChocolateDecorator extends dessertDecorator {
    private static final int WHITE_CHOCOLATE_PRICE_INCREASE = 2;

    public WhiteChocolateDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + WHITE_CHOCOLATE_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + White Chocolate";
    }
}
