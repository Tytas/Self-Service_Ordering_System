package com.app.model;

import com.app.model.product.Product;
import com.app.model.product.drink.ConcreteDrink;

public class DrinkCreator extends FoodCreator {
    private String name;
    private int price;
    private int sizeMillimeter;

    public DrinkCreator(String name, int price, int sizeMillimeter) {
        this.name = name;
        this.price = price;
        this.sizeMillimeter = sizeMillimeter;
    }

    @Override
    public Product createProduct() {
        return new ConcreteDrink(name, price, sizeMillimeter);
    }
}
