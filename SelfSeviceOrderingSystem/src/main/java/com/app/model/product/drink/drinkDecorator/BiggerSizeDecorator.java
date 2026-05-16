package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class BiggerSizeDecorator extends DrinkDecorator {
    private static final int BIGGER_SIZE_PRICE_INCREASE = 2;

    public BiggerSizeDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + BIGGER_SIZE_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Bigger Size";
    }
}
