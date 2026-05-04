package com.app.model.product.snack;

import com.app.model.product.Product;
import java.util.HashMap;
import java.util.Map;

public class Snack implements Product {
    private String name;
    private int price;
    private int sizeGramme;
    private Map<String, Integer> ingredients;

    public Snack(String name, int price, int sizeGramme) {
        this.name = name;
        this.price = price;
        this.sizeGramme = sizeGramme;
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
