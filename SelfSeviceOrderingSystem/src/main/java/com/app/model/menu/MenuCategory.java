package com.app.model.menu;

public enum MenuCategory {
    HOT_DRINKS("Hot Drinks"),
    COLD_DRINKS("Cold Drinks"),
    DESSERTS("Desserts");

    private final String displayName;

    MenuCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}