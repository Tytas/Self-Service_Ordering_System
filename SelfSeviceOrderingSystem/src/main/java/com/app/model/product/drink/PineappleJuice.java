package com.app.model.product.drink;

public class PineappleJuice extends ConcreteDrink {

    private static final String NAME = "Pineapple Juice";
    private static final int PRICE = 190;
    private static final int SIZE_MILLIMETER = 250;

    public PineappleJuice() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
