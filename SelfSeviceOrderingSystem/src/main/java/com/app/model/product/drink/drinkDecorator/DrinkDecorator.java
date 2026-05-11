package com.app.model.product.drink.drinkDecorator;
import com.app.model.product.drink.Drink;

import java.util.Map;

public abstract class DrinkDecorator implements Drink {
    protected Drink wrappee;
    public DrinkDecorator(Drink wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String getName() {
        return wrappee.getName();
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice();
    }

    @Override
    public Map<String, Integer> getIngredients() {
        return wrappee.getIngredients();
    }

    @Override
    public int getSizeMillimeter() {
        return wrappee.getSizeMillimeter();
    }

    @Override
    public abstract String getDescription();
}
