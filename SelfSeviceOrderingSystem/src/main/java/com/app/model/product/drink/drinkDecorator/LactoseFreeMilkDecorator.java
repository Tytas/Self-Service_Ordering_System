package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

public class LactoseFreeMilkDecorator extends DrinkDecorator {
    private static final int LACTOSE_FREE_MILK_PRICE_INCREASE = 2;

    public LactoseFreeMilkDecorator(Drink wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + LACTOSE_FREE_MILK_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Lactose Free Milk";
    }
}
