package com.app.model.product.drink;

public class SparklingWater extends ConcreteDrink {

    private static final String NAME = "Sparkling Water";
    private static final int PRICE = 40;
    private static final int SIZE_MILLIMETER = 330;

    public SparklingWater() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
