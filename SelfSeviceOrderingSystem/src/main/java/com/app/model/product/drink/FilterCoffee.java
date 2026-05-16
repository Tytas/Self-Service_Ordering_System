package com.app.model.product.drink;

public class FilterCoffee extends ConcreteDrink {

    private static final String NAME = "Filter Coffee";
    private static final int PRICE = 90;
    private static final int SIZE_MILLIMETER = 300;

    public FilterCoffee() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
