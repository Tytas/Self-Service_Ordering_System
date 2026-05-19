package com.app.model.product.drink;
import java.util.Map;

public class Water extends ConcreteDrink {

    private static final String NAME = "Water";
        private static final int PRICE = 30;
    private static final int SIZE_MILLIMETER = 500;
    private static final Map<String, Integer> INGREDIENTS = Map.of("Water", 500);

    public Water() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
        this.ingredients = INGREDIENTS;
    }
    
}
