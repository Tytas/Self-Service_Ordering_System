package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class OatMilkDecorator extends DrinkDecorator {
    private static final int OAT_MILK_PRICE_INCREASE = 1;

    public OatMilkDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + OAT_MILK_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Oat Milk";
    }
}
