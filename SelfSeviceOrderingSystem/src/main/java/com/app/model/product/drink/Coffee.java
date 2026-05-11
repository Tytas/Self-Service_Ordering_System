package com.app.model.product.drink;

public class Coffee extends ConcreteDrink {

    private static final String NAME = "Coffee";
    private static final int PRICE = 100;
    private static final int SIZE_MILLIMETER = 330;

    public Coffee() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
