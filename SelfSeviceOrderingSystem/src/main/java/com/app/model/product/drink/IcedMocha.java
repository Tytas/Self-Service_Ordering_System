package com.app.model.product.drink;

public class IcedMocha extends ConcreteDrink {

    private static final String NAME = "Iced Mocha";
    private static final int PRICE = 200;
    private static final int SIZE_MILLIMETER = 330;

    public IcedMocha() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
