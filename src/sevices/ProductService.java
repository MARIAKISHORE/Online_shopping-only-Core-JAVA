package services;

import java.util.*;
import entities.Product;

public class ProductService {
    private Map<Integer, Product> products = new LinkedHashMap<>();

    public void addProduct(Product p) {
        products.put(p.getProductId(), p);
    }

    public void removeProduct(int id) {
        products.remove(id);
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }
}
