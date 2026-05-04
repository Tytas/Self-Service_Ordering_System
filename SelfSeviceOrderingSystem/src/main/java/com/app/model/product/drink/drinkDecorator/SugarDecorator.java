package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class SugarDecorator extends DrinkDecorator {
    private static final int SUGAR_PRICE_INCREASE = 1;

    public SugarDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + SUGAR_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Sugar";
    }
}
