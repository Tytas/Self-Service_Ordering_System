package com.app.model.product.drink;

public class Sprite extends ConcreteDrink {

    private static final String NAME = "Sprite";
    private static final int PRICE = 50;
    private static final int SIZE_MILLIMETER = 330;

    public Sprite() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
