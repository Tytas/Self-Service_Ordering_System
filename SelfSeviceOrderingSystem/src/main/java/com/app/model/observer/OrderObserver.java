package com.app.model.observer;

import com.app.model.Order;

public interface OrderObserver {
    void update(Order order, OrderEvent event);
}
