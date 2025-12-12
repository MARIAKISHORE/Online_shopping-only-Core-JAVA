package entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int counter = 1;

    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status;

    public Order(Customer customer) {
        this.orderId = counter++;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<ProductQuantityPair> getProducts() { return products; }
    public String getStatus() { return status; }

    public void addProduct(Product p, int qty) {
        products.add(new ProductQuantityPair(p, qty));
    }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer: " +
                customer.getUsername() + ", Status: " + status;
    }
}
