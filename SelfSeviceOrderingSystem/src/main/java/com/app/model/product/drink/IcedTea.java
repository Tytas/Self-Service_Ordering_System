package com.app.model.product.drink;

public class IcedTea extends ConcreteDrink {

    private static final String NAME = "Iced Tea";
    private static final int PRICE = 190;
    private static final int SIZE_MILLIMETER = 330;

    public IcedTea() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
