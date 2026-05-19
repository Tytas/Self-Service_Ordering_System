package com.app.model.product.dessert;

import java.util.Map;

public class ChocolateStrawberryCake extends ConcreteDessert {

    private static final String NAME = "Chocolate Strawberry Cake";
    private static final int PRICE = 260;
    private static final int SIZE_GRAMME = 180;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public ChocolateStrawberryCake() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}