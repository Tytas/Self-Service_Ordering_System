package com.app.model;

import com.app.model.product.Product;
import com.app.model.product.snack.Snack;

public class SnackCreator extends FoodCreator {
    private String name;
    private int price;
    private int sizeGramme;

    public SnackCreator(String name, int price, int sizeGramme) {
        this.name = name;
        this.price = price;
        this.sizeGramme = sizeGramme;
    }

    @Override
    public Product createProduct() {
        return new Snack(name, price, sizeGramme);
    }
}
