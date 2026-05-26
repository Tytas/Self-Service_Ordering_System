package com.app.model.menu;

import com.app.model.FoodCreator;
import com.app.model.product.Product;
import com.app.model.product.drink.DrinkCreator;
import com.app.model.product.dessert.DessertCreator;
import java.util.ArrayList;
import java.util.List;

public final class MenuCatalog {
    private MenuCatalog() {
    }

    public static List<MenuCategory> getCategories() {
        return List.of(MenuCategory.HOT_DRINKS, MenuCategory.COLD_DRINKS, MenuCategory.DESSERTS);
    }

    public static List<Product> getProducts(MenuCategory category) {
        List<Product> out = new ArrayList<>();
        if (category == MenuCategory.HOT_DRINKS) {
            FoodCreator creator = new DrinkCreator();
            String[] names = {
                "Affogato",
                "Espresso",
                "Americano",
                "Caffe Latte",
                "Caffe Mocha",
                "Cappuccino",
                "Filter Coffee",
                "Chai Tea Latte",
                "Caramel Latte",
                "Hot Chocolate"
            };
            for (String n : names) {
                Product p = creator.createProduct(n);
                out.add(p);
            }
            return out;
        }

        if (category == MenuCategory.COLD_DRINKS) {
            FoodCreator creator = new DrinkCreator();
            String[] names = {
                "Iced Caffe Latte",
                "Iced Chai Tea Latte",
                "Iced Mocha",
                "Cold Brew",
                "Iced Tea",
                "Cola",
                "Fanta",
                "Sprite",
                "Sparkling Water",
                "Apple Juice",
                "Orange Juice",
                "Pineapple Juice",
                "Lemon Juice",
                "Lemon Soda",
                "Water"
            };
            for (String n : names) {
                Product p = creator.createProduct(n);
                out.add(p);
            }
            return out;
        }

        if (category == MenuCategory.DESSERTS) {
            FoodCreator creator = new DessertCreator();
            String[] names = {
                "Brownie",
                "Tiramisu",
                "Paris Brest",
                "Strawberry Almond Custard",
                "San Sebastian",
                "Pistachio Croissant",
                "Blueberry Tart",
                "Oreo Cake",
                "KitKat Cake",
                "Chocolate Strawberry Cake",
                "Banana Cake Roll with Chocolate",
                "Hazelnut Croquant",
                "Lotus Pudding",
                "Creamy Waffle",
                "Chocolate Souffle"
            };
            for (String n : names) {
                Product p = creator.createProduct(n);
                out.add(p);
            }
            return out;
        }

        return out;
    }
}