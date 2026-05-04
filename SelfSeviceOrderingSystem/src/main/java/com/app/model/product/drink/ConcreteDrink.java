package com.app.model.product.drink;

import java.util.HashMap;
import java.util.Map;

public class ConcreteDrink implements Drink {
    private String name;
    private int price;
    private int sizeMillimeter;
    private Map<String, Integer> ingredients;

    public ConcreteDrink(String name, int price, int sizeMillimeter) {
        this.name = name;
        this.price = price;
        this.sizeMillimeter = sizeMillimeter;
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
