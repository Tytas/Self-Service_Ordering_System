package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class CremeDecorator extends DrinkDecorator {
    private static final int CREME_PRICE_INCREASE = 2;

    public CremeDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + CREME_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Creme";
    }
}
