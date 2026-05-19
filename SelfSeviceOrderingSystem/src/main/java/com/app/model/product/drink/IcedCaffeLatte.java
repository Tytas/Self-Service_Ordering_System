package com.app.model.product.drink;

public class IcedCaffeLatte extends ConcreteDrink {

    private static final String NAME = "Iced Caffé Latte";
    private static final int PRICE = 120;
    private static final int SIZE_MILLIMETER = 330;

    public IcedCaffeLatte() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
