package com.app.model.product.dessert;

import java.util.Map;

public class ParisBrest extends ConcreteDessert {

    private static final String NAME = "Paris Brest";
    private static final int PRICE = 240;
    private static final int SIZE_GRAMME = 155;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public ParisBrest() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}