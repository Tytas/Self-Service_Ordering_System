package com.app.model.product.dessert.dessertDecorator;
import com.app.model.product.dessert.Dessert;

public class MilkChocolateDecorator extends dessertDecorator {
    private static final int MILK_CHOCOLATE_PRICE_INCREASE = 2;

    public MilkChocolateDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + MILK_CHOCOLATE_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Milk Chocolate";
    }
}

