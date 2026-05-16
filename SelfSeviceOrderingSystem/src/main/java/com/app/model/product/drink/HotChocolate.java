package com.app.model.product.drink;

public class HotChocolate extends ConcreteDrink {

    private static final String NAME = "Hot Chocolate";
    private static final int PRICE = 100;
    private static final int SIZE_MILLIMETER = 330;

    public HotChocolate() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
