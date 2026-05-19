package com.app.model.product.drink;

public class Americano extends ConcreteDrink {

    private static final String NAME = "Americano";
    private static final int PRICE = 90;
    private static final int SIZE_MILLIMETER = 300;

    public Americano() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
