package com.app.model.product.dessert;

import com.app.model.FoodCreator;
import com.app.model.product.Product;

public class DessertCreator extends FoodCreator {

    public DessertCreator() {
    }

    @Override
    public Product createProduct(String type) {
        if (type.equalsIgnoreCase("San Sebastian")) {
            return new SanSebastian();
        } else if (type.equalsIgnoreCase("Pistachio Croissant")) {
            return new PistachioCroissant();
        } else if (type.equalsIgnoreCase("Blueberry Tart")) {
            return new BlueberryTart();
        } else if (type.equalsIgnoreCase("Oreo Cake")) {
            return new OreoCake();
        } else if (type.equalsIgnoreCase("KitKat Cake")) {
            return new KitKatCake();
        } else if (type.equalsIgnoreCase("Chocolate Strawberry Cake")) {
            return new ChocolateStrawberryCake();
        } else if (type.equalsIgnoreCase("Tiramisu")) {
            return new Tiramisu();
        } else if (type.equalsIgnoreCase("Banana Cake Roll with Chocolate")) {
            return new BananaCakeRoll();
        } else if (type.equalsIgnoreCase("Hazelnut Croquant")) {
            return new HazelnutCroquant();
        } else if (type.equalsIgnoreCase("Paris Brest")) {
            return new ParisBrest();
        } else if (type.equalsIgnoreCase("Strawberry Almond Custard")) {
            return new StrawberryAlmondCustard();
        } else if (type.equalsIgnoreCase("Lotus Pudding")) {
            return new LotusPudding();
        } else if (type.equalsIgnoreCase("Creamy Waffle")) {
            return new CreamyWaffle();
        } else if (type.equalsIgnoreCase("Brownie")) {
            return new Brownie();
        } else if (type.equalsIgnoreCase("Chocolate Souffle")) {
            return new ChocolateSouffle();
        } else {
            throw new IllegalArgumentException("Unknown dessert type: " + type);
        }
    }
}
