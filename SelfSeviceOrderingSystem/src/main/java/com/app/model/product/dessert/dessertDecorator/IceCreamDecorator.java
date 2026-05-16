package com.app.model.product.dessert.dessertDecorator;
import com.app.model.product.dessert.Dessert;

public class IceCreamDecorator extends dessertDecorator {
    private static final int ICE_CREAM_PRICE_INCREASE = 3;

    public IceCreamDecorator(Dessert wrappee) {
        super(wrappee);
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice() + ICE_CREAM_PRICE_INCREASE;
    }

    @Override
    public String getDescription() {
        return wrappee.getDescription() + " + Ice Cream";
    }
}
