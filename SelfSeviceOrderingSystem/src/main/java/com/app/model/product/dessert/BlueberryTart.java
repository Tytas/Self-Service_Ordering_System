package com.app.model.product.dessert;

import java.util.Map;

public class BlueberryTart extends ConcreteDessert {

    private static final String NAME = "Blueberry Tart";
    private static final int PRICE = 225;
    private static final int SIZE_GRAMME = 140;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public BlueberryTart() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}