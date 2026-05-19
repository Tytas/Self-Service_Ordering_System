package com.app.model.product.drink;

public class Espresso extends ConcreteDrink {

    private static final String NAME = "Espresso";
    private static final int PRICE = 200;
    private static final int SIZE_MILLIMETER = 30;

    public Espresso() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
