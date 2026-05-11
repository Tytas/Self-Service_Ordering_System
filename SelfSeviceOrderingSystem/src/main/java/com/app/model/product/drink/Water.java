package com.app.model.product.drink;

public class Water extends ConcreteDrink {

    private static final String NAME = "Water";
    private static final int PRICE = 50;
    private static final int SIZE_MILLIMETER = 500;

    public Water() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
    
}
