package com.app.model.product.dessert;

import java.util.Map;

public class SanSebastian extends ConcreteDessert{

    private static final String NAME = "San Sebastian";
    private static final int PRICE = 50;
    private static final int SIZE_GRAMME = 150;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public SanSebastian() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
    
}
