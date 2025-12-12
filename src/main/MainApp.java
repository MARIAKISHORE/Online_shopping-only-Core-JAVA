package main;

import java.util.*;
import entities.*;
import services.*;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        AdminService adminService = new AdminService();
        OrderService orderService = new OrderService();

        while (true) {
            System.out.println("\n1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {

            case 1:
                adminMenu(sc, productService, adminService, orderService);
                break;

            case 2:
                customerMenu(sc, productService, customerService, orderService);
                break;

            case 3:
                System.out.println("Exiting...");
                sc.close();
                return;

            default:
                System.out.println("Invalid option!");
            }
        }
    }

    // -------------------------- ADMIN MENU --------------------------

    public static void adminMenu(Scanner sc, ProductService productService,
            AdminService adminService, OrderService orderService) {

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");

            System.out.print("Choose an option: ");
            int ch = sc.nextInt();

            switch (ch) {

            case 1:
                System.out.print("Enter Product ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Product Price: ");
                double price = sc.nextDouble();
                System.out.print("Enter Stock Quantity: ");
                int qty = sc.nextInt();

                productService.addProduct(new Product(id, name, price, qty));
                System.out.println("Product added successfully!");
                break;

            case 2:
                System.out.print("Enter Product ID to remove: ");
                int removeId = sc.nextInt();
                productService.removeProduct(removeId);
                System.out.println("Product removed successfully!");
                break;

            case 3:
                System.out.println("Products:");
                for (Product p : productService.getAllProducts())
                    System.out.println(p);
                break;

            case 4:
                System.out.print("Enter Admin ID: ");
                int aid = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Admin Username: ");
                String aname = sc.nextLine();
                System.out.print("Enter Email: ");
                String aemail = sc.nextLine();

                adminService.addAdmin(new Admin(aid, aname, aemail));
                System.out.println("Admin created successfully!");
                break;

            case 5:
                System.out.println("Admins:");
                for (Admin a : adminService.getAdmins())
                    System.out.println(a);
                break;

            case 6:
                System.out.print("Enter Order ID: ");
                int oid = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                String status = sc.nextLine();

                orderService.updateOrderStatus(oid, status);
                System.out.println("Order status updated!");
                break;

            case 7:
                System.out.println("Orders:");
                for (Order o : orderService.getAllOrders()) {
                    System.out.println(o);
                    for (ProductQuantityPair pq : o.getProducts())
                        System.out.println("  " + pq);
                }
                break;

            case 8:
                return;

            default:
                System.out.println("Invalid option!");
            }
        }
    }

    // -------------------------- CUSTOMER MENU --------------------------

    public static void customerMenu(Scanner sc, ProductService productService,
            CustomerService customerService, OrderService orderService) {

        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");

            int ch = sc.nextInt();

            switch (ch) {

            case 1:
                System.out.print("Enter User ID: ");
                int uid = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Username: ");
                String uname = sc.nextLine();
                System.out.print("Enter Email: ");
                String uemail = sc.nextLine();
                System.out.print("Enter Address: ");
                String address = sc.nextLine();

                customerService.addCustomer(new Customer(uid, uname, uemail, address));
                System.out.println("Customer created successfully!");
                break;

            case 2:
                System.out.println("Customers:");
                for (Customer c : customerService.getCustomers())
                    System.out.println(c);
                break;

            case 3:
                System.out.print("Enter Customer ID: ");
                int cid = sc.nextInt();
                Customer customer = customerService.getCustomer(cid);

                if (customer == null) {
                    System.out.println("Customer not found!");
                    break;
                }

                while (true) {
                    System.out.print("Enter Product ID to add to order (or -1 to complete): ");
                    int pid = sc.nextInt();

                    if (pid == -1)
                        break;

                    Product p = productService.getProduct(pid);
                    if (p == null) {
                        System.out.println("Invalid Product ID!");
                        continue;
                    }

                    System.out.print("Enter quantity: ");
                    int q = sc.nextInt();

                    customer.getShoppingCart().addItem(p, q);
                }

                orderService.placeOrder(customer);
                System.out.println("Order placed successfully!");
                break;

            case 4:
                System.out.print("Enter Customer ID: ");
                int custId = sc.nextInt();
                List<Order> orders = orderService.getOrdersByCustomer(custId, customerService.getCustomers());

                System.out.println("Orders:");
                for (Order o : orders) {
                    System.out.println(o);
                    for (ProductQuantityPair pq : o.getProducts())
                        System.out.println("  " + pq);
                }
                break;

            case 5:
                System.out.println("Products:");
                for (Product p : productService.getAllProducts())
                    System.out.println(p);
                break;

            case 6:
                return;

            default:
                System.out.println("Invalid option!");
            }
        }
    }
}
