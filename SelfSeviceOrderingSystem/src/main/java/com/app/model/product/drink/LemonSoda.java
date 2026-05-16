package com.app.model.product.drink;

public class LemonSoda extends ConcreteDrink {

    private static final String NAME = "Lemon Soda";
    private static final int PRICE = 60;
    private static final int SIZE_MILLIMETER = 330;

    public LemonSoda() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
