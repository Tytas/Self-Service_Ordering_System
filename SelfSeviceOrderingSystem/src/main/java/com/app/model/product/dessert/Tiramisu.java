package com.app.model.product.dessert;

import java.util.Map;

public class Tiramisu extends ConcreteDessert {

    private static final String NAME = "Tiramisu";
    private static final int PRICE = 250;
    private static final int SIZE_GRAMME = 165;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public Tiramisu() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}