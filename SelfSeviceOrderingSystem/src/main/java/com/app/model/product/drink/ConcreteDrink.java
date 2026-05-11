package com.app.model.product.drink;

import java.util.HashMap;
import java.util.Map;

public abstract class ConcreteDrink implements Drink {
    protected String name;
    protected int price;
    protected int sizeMillimeter;
    protected Map<String, Integer> ingredients;

    public ConcreteDrink() {
        this.ingredients = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public int getSizeMillimeter() {
        return sizeMillimeter;
    }

    public void addIngredient(String ingredient, int quantity) {
        ingredients.put(ingredient, quantity);
    }

    @Override
    public String getDescription() {
        return "Drink: " + name + " (" + sizeMillimeter + "ml) - " + price + "$";
    }
}
