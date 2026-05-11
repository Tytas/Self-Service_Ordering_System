package com.app.model.product.snack;

public class Panini extends Snack {

    private static final String NAME = "Panini";
    private static final int PRICE = 200;
    private static final int SIZE_GRAMME = 100;

    public Panini() {
        super();
        this.name = NAME;
        this.price = PRICE;
        this.sizeGramme = SIZE_GRAMME;
    }
}