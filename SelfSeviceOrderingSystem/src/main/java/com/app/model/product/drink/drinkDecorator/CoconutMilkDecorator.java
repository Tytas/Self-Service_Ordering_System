package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class CoconutMilkDecorator extends DrinkDecorator {
    private static final int COCONUT_MILK_PRICE_INCREASE = 1;

    public CoconutMilkDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + COCONUT_MILK_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Coconut Milk";
    }
}
