package com.app.model.product.drink;

public class Fanta extends ConcreteDrink {

    private static final String NAME = "Fanta";
    private static final int PRICE = 60;
    private static final int SIZE_MILLIMETER = 330;

    public Fanta() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
