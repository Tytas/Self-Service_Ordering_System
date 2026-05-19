package com.app.model.product.drink;

public class CaffeLatte extends ConcreteDrink {

    private static final String NAME = "Caffé Latte";
        private static final int PRICE = 210;
    private static final int SIZE_MILLIMETER = 330;

    public CaffeLatte() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
