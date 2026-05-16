package com.app.model.product.drink;

public class OrangeJuice extends ConcreteDrink {

    private static final String NAME = "Orange Juice";
    private static final int PRICE = 80;
    private static final int SIZE_MILLIMETER = 250;

    public OrangeJuice() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
