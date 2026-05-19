package com.app.model.product.drink;

import com.app.model.FoodCreator;
import com.app.model.product.Product;

public class DrinkCreator extends FoodCreator {

    public DrinkCreator() {
    }

    @Override
    public Product createProduct(String type) {
        if (type.equalsIgnoreCase("Affogato")) {
            return new Affogato();
        } else if (type.equalsIgnoreCase("Americano")) {
            return new Americano();
        } else if (type.equalsIgnoreCase("Apple Juice")) {
            return new AppleJuice();
        } else if (type.equalsIgnoreCase("Caffe Latte")) {
            return new CaffeLatte();
        } else if (type.equalsIgnoreCase("Caffe Mocha")) {
            return new CaffeMocha();
        } else if (type.equalsIgnoreCase("Cappuccino")) {
            return new Cappuccino();
        } else if (type.equalsIgnoreCase("Caramel Latte")) {
            return new CaramelLatte();
        } else if (type.equalsIgnoreCase("Chai Tea Latte")) {
            return new ChaiTeaLatte();
        } else if (type.equalsIgnoreCase("Cola")) {
            return new Cola();
        } else if (type.equalsIgnoreCase("Cold Brew")) {
            return new ColdBrew();
        } else if (type.equalsIgnoreCase("Espresso")) {
            return new Espresso();
        } else if (type.equalsIgnoreCase("Fanta")) {
            return new Fanta();
        } else if (type.equalsIgnoreCase("Filter Coffee")) {
            return new FilterCoffee();
        } else if (type.equalsIgnoreCase("Hot Chocolate")) {
            return new HotChocolate();
        } else if (type.equalsIgnoreCase("Iced Caffe Latte")) {
            return new IcedCaffeLatte();
        } else if (type.equalsIgnoreCase("Iced Chai Tea Latte")) {
            return new IcedChaiTeaLatte();
        } else if (type.equalsIgnoreCase("Iced Mocha")) {
            return new IcedMocha();
        } else if (type.equalsIgnoreCase("Iced Tea")) {
            return new IcedTea();
        } else if (type.equalsIgnoreCase("Lemon Juice")) {
            return new LemonJuice();
        } else if (type.equalsIgnoreCase("Lemon Soda")) {
            return new LemonSoda();
        } else if (type.equalsIgnoreCase("Orange Juice")) {
            return new OrangeJuice();
        } else if (type.equalsIgnoreCase("Pineapple Juice")) {
            return new PineappleJuice();
        } else if (type.equalsIgnoreCase("Sparkling Water")) {
            return new SparklingWater();
        } else if (type.equalsIgnoreCase("Sprite")) {
            return new Sprite();
        } else if (type.equalsIgnoreCase("Water")) {
            return new Water();
        } else {
            throw new IllegalArgumentException("Unknown drink type: " + type);
        }
    }
}
