package com.app.model.product.snack;

public class Burger extends Snack {

    private static final String NAME = "Burger";
    private static final int PRICE = 150;
    private static final int SIZE_GRAMME = 50;

    public Burger() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
    }
}