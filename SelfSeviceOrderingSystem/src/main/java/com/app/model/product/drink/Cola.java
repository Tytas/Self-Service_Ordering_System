package com.app.model.product.drink;

public class Cola extends ConcreteDrink {

    private static final String NAME = "Cola";
    private static final int PRICE = 50;
    private static final int SIZE_MILLIMETER = 330;

    public Cola() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
