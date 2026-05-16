package com.app.model.product.drink;

public class ColdBrew extends ConcreteDrink {

    private static final String NAME = "Cold Brew";
    private static final int PRICE = 110;
    private static final int SIZE_MILLIMETER = 330;

    public ColdBrew() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
