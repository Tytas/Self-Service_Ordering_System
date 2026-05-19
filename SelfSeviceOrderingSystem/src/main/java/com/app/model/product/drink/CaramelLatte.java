package com.app.model.product.drink;

import java.util.Map;

public class CaramelLatte extends ConcreteDrink {

    private static final String NAME = "CaramelLatte";
    private static final int PRICE = 250;
    private static final int SIZE_MILLIMETER = 500;
    private static final Map<String, Integer> INGREDIENTS = Map.of("Caramel", 150, "Milk", 200, "Espresso", 100, "Sugar", 50);

    public CaramelLatte() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
        this.ingredients = INGREDIENTS;
    }
}
