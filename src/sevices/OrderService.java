package services;

import java.util.*;
import entities.*;

public class OrderService {
    private List<Order> allOrders = new ArrayList<>();

    public Order placeOrder(Customer customer) {
        Order order = new Order(customer);

        for (Map.Entry<Product, Integer> e : customer.getShoppingCart().getItems().entrySet()) {
            Product p = e.getKey();
            int qty = e.getValue();

            if (qty > p.getStockQuantity()) continue;

            p.setStockQuantity(p.getStockQuantity() - qty);
            order.addProduct(p, qty);
        }

        customer.getOrders().add(order);
        allOrders.add(order);

        customer.getShoppingCart().clearCart();

        return order;
    }

    public List<Order> getOrdersByCustomer(int customerId, List<Customer> customers) {
        for (Customer c : customers) {
            if (c.getUserId() == customerId) return c.getOrders();
        }
        return new ArrayList<>();
    }

    public List<Order> getAllOrders() { return allOrders; }

    public void updateOrderStatus(int orderId, String status) {
        for (Order o : allOrders) {
            if (o.getOrderId() == orderId) {
                o.setStatus(status);
                break;
            }
        }
    }
}
