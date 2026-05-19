package com.app.model.product.dessert;

import java.util.Map;

public class KitKatCake extends ConcreteDessert {

    private static final String NAME = "KitKat Cake";
    private static final int PRICE = 265;
    private static final int SIZE_GRAMME = 190;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public KitKatCake() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}