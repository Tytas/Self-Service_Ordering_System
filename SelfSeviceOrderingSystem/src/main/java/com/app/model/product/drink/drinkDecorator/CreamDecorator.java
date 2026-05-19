package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class CreamDecorator extends DrinkDecorator {
    private static final int CREAM_PRICE_INCREASE = 2;

    public CreamDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + CREAM_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Cream";
    }
}
