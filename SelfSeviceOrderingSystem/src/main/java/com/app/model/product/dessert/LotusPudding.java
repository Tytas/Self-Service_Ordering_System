package com.app.model.product.dessert;

import java.util.Map;

public class LotusPudding extends ConcreteDessert {

    private static final String NAME = "Lotus Pudding";
    private static final int PRICE = 0;
    private static final int SIZE_GRAMME = 0;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public LotusPudding() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}