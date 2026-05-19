package com.app.model.product.drink;

public class Affogato extends ConcreteDrink {

    private static final String NAME = "Affogato";
    private static final int PRICE = 150;
    private static final int SIZE_MILLIMETER = 200;

    public Affogato() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
