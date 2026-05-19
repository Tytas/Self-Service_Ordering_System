package com.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private static final Cart INSTANCE = new Cart();
    private final List<CartItem> items = new ArrayList<>();

    private Cart() {}

    public static Cart getInstance() { return INSTANCE; }

    public void addItem(CartItem item) { items.add(item); }

    public List<CartItem> getItems() { return Collections.unmodifiableList(items); }

    public int getTotalPrice() { return items.stream().mapToInt(CartItem::getTotalPrice).sum(); }

    public void clear() { items.clear(); }
}
