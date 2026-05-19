package com.app.model.product.dessert;

import java.util.Map;

public class StrawberryAlmondCustard extends ConcreteDessert {

    private static final String NAME = "Strawberry Almond Custard";
    private static final int PRICE = 245;
    private static final int SIZE_GRAMME = 175;
    private static final Map<String, Integer> INGREDIENTS = Map.of();

    public StrawberryAlmondCustard() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
        this.ingredients = INGREDIENTS;
    }
}