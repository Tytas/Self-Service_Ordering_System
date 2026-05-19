package com.app.model;

import java.util.List;

public class CartItem {
    private final String name;
    private final int basePrice;
    private final List<String> addons;

    public CartItem(String name, int basePrice, List<String> addons) {
        this.name = name;
        this.basePrice = basePrice;
        this.addons = addons;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public List<String> getAddons() {
        return addons;
    }

    public int getTotalPrice() {
        int extra = addons.stream().mapToInt(a -> {
            // parse last number in string like "Milk Chocolate (+20)" if present
            int idx = a.lastIndexOf("(+");
            if (idx >= 0) {
                String num = a.substring(idx + 2, a.length() - 1);
                try { return Integer.parseInt(num); } catch (NumberFormatException e) { return 0; }
            }
            return 0;
        }).sum();
        return basePrice + extra;
    }
}
