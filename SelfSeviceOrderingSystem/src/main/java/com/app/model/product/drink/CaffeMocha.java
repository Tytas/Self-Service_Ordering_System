package com.app.model.product.drink;

public class CaffeMocha extends ConcreteDrink {

    private static final String NAME = "Caffé Mocha";
    private static final int PRICE = 140;
    private static final int SIZE_MILLIMETER = 330;

    public CaffeMocha() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
