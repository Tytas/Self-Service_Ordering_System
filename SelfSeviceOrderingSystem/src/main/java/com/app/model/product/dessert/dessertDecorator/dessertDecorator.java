package com.app.model.product.dessert.dessertDecorator;
import com.app.model.product.dessert.Dessert;

import java.util.Map;

public abstract class dessertDecorator extends Dessert {
    protected Dessert wrappee;
    public dessertDecorator(Dessert wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public String getName() {
        return wrappee.getName();
    }

    @Override
    public int getPrice() {
        return wrappee.getPrice();
    }

    @Override
    public Map<String, Integer> getIngredients() {
        return wrappee.getIngredients();
    }

    @Override
    public int getSizeGramme() {
        return wrappee.getSizeGramme();
    }

    @Override
    public abstract String getDescription();
}
