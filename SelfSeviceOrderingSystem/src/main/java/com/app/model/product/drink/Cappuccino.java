package com.app.model.product.drink;

public class Cappuccino extends ConcreteDrink {

    private static final String NAME = "Cappuccino";
    private static final int PRICE = 120;
    private static final int SIZE_MILLIMETER = 330;

    public Cappuccino() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
