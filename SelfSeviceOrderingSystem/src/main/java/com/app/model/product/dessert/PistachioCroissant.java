package com.app.model.product.dessert;

import java.util.Map;

public class PistachioCroissant extends ConcreteDessert{

    private static final String NAME = "Pistachio Croissant";
    private static final int PRICE = 0;
    private static final int SIZE_GRAMME = 0;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public PistachioCroissant() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}