package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class CaramelDecorator extends DrinkDecorator {
    private static final int CARAMEL_PRICE_INCREASE = 2;

    public CaramelDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + CARAMEL_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Caramel";
    }
}
