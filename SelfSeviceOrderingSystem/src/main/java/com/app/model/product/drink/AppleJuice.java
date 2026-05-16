package com.app.model.product.drink;

public class AppleJuice extends ConcreteDrink {

    private static final String NAME = "Apple Juice";
    private static final int PRICE = 80;
    private static final int SIZE_MILLIMETER = 250;

    public AppleJuice() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
