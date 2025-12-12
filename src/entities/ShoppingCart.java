package entities;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items = new LinkedHashMap<>();

    public Map<Product, Integer> getItems() { return items; }

    public void addItem(Product product, int qty) {
        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    public void clearCart() {
        items.clear();
    }
}
