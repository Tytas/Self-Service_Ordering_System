package com.app.model.product.drink;

public class LemonJuice extends ConcreteDrink {

    private static final String NAME = "Lemon Juice";
    private static final int PRICE = 190;
    private static final int SIZE_MILLIMETER = 250;

    public LemonJuice() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
