package com.app.model.menu;

public class ProductItem {
    private final String name;
    private final String description;
    private final int price;
    private final String imageLabel;

    public ProductItem(String name, String description, int price, String imageLabel) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageLabel = imageLabel;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getImageLabel() {
        return imageLabel;
    }
}