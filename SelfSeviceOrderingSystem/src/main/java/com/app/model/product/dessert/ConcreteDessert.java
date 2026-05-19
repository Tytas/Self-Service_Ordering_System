package com.app.model.product.dessert;

import java.util.HashMap;
import java.util.Map;

public abstract class ConcreteDessert extends Dessert {
    protected String name;
    protected int price;
    protected int sizeGramme;
    protected Map<String, Integer> ingredients;

    public ConcreteDessert() {
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

    public void addIngredient(String ingredient, int quantity) {
        ingredients.put(ingredient, quantity);
    }

    @Override
    public String getDescription() {
        return "Dessert: " + name + " (" + sizeGramme + " grammes) - " + price + " TL";
    }
}
