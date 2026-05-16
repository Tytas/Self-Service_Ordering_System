package com.app.model.product.drink;

public class IcedChaiTeaLatte extends ConcreteDrink {

    private static final String NAME = "Iced Chai Tea Latte";
    private static final int PRICE = 110;
    private static final int SIZE_MILLIMETER = 330;

    public IcedChaiTeaLatte() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeMillimeter = SIZE_MILLIMETER;
    }
}
