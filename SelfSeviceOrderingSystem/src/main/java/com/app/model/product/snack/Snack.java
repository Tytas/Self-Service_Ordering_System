package com.app.model.product.snack;

import com.app.model.product.Product;
import java.util.HashMap;
import java.util.Map;

public abstract class Snack implements Product {
    protected String name;
    protected int price;
    protected int sizeGramme;
    protected Map<String, Integer> ingredients;

    public Snack() {
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

    public int getSizeGramme() {
        return sizeGramme;
    }

    public void addIngredient(String ingredient, int quantity) {
        ingredients.put(ingredient, quantity);
    }

    @Override
    public String getDescription() {
        return "Snack: " + name + " (" + sizeGramme + "g) - " + price + "€";
    }
}
