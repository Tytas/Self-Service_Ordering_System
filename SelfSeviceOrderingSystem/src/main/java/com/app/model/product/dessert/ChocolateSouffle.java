package com.app.model.product.dessert;

import java.util.Map;

public class ChocolateSouffle extends ConcreteDessert {

    private static final String NAME = "Chocolate Souffle";
    private static final int PRICE = 280;
    private static final int SIZE_GRAMME = 135;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public ChocolateSouffle() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}