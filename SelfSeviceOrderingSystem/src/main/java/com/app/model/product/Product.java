package com.app.model.product;

import java.util.Map;

public interface Product {
    String getName();
    int getPrice();
    Map<String, Integer> getIngredients();
    String getDescription();
}
