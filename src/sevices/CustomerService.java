package services;

import java.util.*;
import entities.Customer;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id) return c;
        }
        return null;
    }
}
