package com.app.model.product.drink;

public class ChaiTeaLatte extends ConcreteDrink {

    private static final String NAME = "Chai Tea Latte";
        private static final int PRICE = 200;
    private static final int SIZE_MILLIMETER = 330;

    public ChaiTeaLatte() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
