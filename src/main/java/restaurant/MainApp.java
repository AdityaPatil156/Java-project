package restaurant;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import restaurant.exception.TableNotAvailableException;
import restaurant.model.*;
import restaurant.service.DiscountPolicy;
import restaurant.service.RestaurantService;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RestaurantService service = new RestaurantService("Java Bistro", 5);

        System.out.println("===== RESTAURANT MANAGEMENT SYSTEM =====");

        // -----------------------------
        // Preloaded demo data
        // -----------------------------
        Staff chef = new Staff("Gordon", "Ramsay", Role.CHEF);
        Staff waiter = new Staff("John", "Doe", Role.WAITER);

        MenuItemRecord burger = new MenuItemRecord("Burger", 10.0, Category.MAIN);
        MenuItemRecord coffee = new MenuItemRecord("Coffee", 3.0, Category.DRINK);
        MenuItemRecord pizza = new MenuItemRecord("Pizza", 12.5, Category.MAIN);

        Customer currentCustomer = null;

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Register Customer");
            System.out.println("2. Create Order");
            System.out.println("3. View All Orders");
            System.out.println("4. Demo Staff & Menu (Inheritance + Records)");
            System.out.println("5. Demo Payments (Sealed Classes + Polymorphism)");
            System.out.println("6. Demo Order Filter (Lambda + Predicate)");
            System.out.println("7. Demo Exception Handling");
            System.out.println("8. Demo Discount System (Strategy Pattern)");
            System.out.println("9. Demo Pattern Matching");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ----------------------------------------
                // 1. Register Customer
                // ----------------------------------------
                case 1 -> {
                    System.out.print("Enter first name: ");
                    String first = sc.nextLine();

                    System.out.print("Enter last name: ");
                    String last = sc.nextLine();

                    System.out.print("Enter loyalty points: ");
                    int points = sc.nextInt();
                    sc.nextLine();

                    ImmutableAddress addr = new ImmutableAddress("Main Street", "Athlone", "Ireland");

                    currentCustomer = new Customer(first, last, addr, points);
                    service.registerCustomer(currentCustomer);

                    System.out.println("✅ Customer Registered: " + currentCustomer.getFullName());
                }

                // ----------------------------------------
                // 2. Create Order
                // ----------------------------------------
                case 2 -> {
                    if (currentCustomer == null) {
                        System.out.println("⚠ Register customer first!");
                        break;
                    }

                    try {
                        System.out.print("Enter table number (1-5): ");
                        int table = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter food name: ");
                        String foodName = sc.nextLine();

                        System.out.print("Enter price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();

                        MenuItemRecord item = new MenuItemRecord(foodName, price, Category.MAIN);

                        Order order = service.createOrder(currentCustomer, table, item);

                        DiscountPolicy policy = DiscountPolicy.loyaltyDiscountPolicy();
                        double finalAmount = policy.apply(order.calculateTotal(), currentCustomer);

                        System.out.println("✅ Order Created!");
                        System.out.println("Order Total: " + order.calculateTotal());
                        System.out.println("After Discount: " + finalAmount);

                    } catch (TableNotAvailableException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                }

                // ----------------------------------------
                // 3. View all orders
                // ----------------------------------------
                case 3 -> {
                    System.out.println("📋 All Orders:");
                    Predicate<Order> allOrders = o -> true;
                    List<Order> orders = service.filterOrders(allOrders);

                    if (orders.isEmpty()) System.out.println("No orders found.");
                    else orders.forEach(System.out::println);
                }

                // ----------------------------------------
                // 4. OOP + RECORDS DEMO
                // ----------------------------------------
                case 4 -> {
                    // System.out.println("\n--- STAFF (Inheritance) ---");
                    // System.out.println(chef);
                    // System.out.println(waiter);

                    System.out.println("\n--- MENU ITEMS (Records) ---");
                    System.out.println(burger);
                    System.out.println(coffee);
                    System.out.println(pizza);
                }

                // ----------------------------------------
                // 5. SEALED CLASSES (Payment)
                // ----------------------------------------
                case 5 -> {
                    System.out.println("\n--- PAYMENT DEMO ---");

                    Payment cash = new CashPayment(20.0);
                    Payment card = new CardPayment(PaymentMethod.CARD, 45.0, "****2222");
                    Payment online = new OnlinePayment(30.0, "PayPal");

                    cash.process();
                    card.process();
                    online.process();
                }

                // ----------------------------------------
                // 6. LAMBDA FILTER DEMO
                // ----------------------------------------
                case 6 -> {
                    System.out.println("\n--- HIGH VALUE ORDERS (> 20) ---");

                    Predicate<Order> highValue = o -> o.calculateTotal() > 20;
                    List<Order> filtered = service.filterOrders(highValue);

                    if (filtered.isEmpty()) System.out.println("No high value orders.");
                    else filtered.forEach(System.out::println);
                }

                // ----------------------------------------
                // 7. EXCEPTION HANDLING DEMO
                // ----------------------------------------
                case 7 -> {
                    System.out.println("\n--- EXCEPTION HANDLING DEMO ---");

                    try {
                        service.createOrder(currentCustomer, 99); // invalid table
                    } catch (Exception e) {
                        System.out.println("Caught Exception: " + e.getMessage());
                    }
                }

                // ----------------------------------------
                // 8. DISCOUNT SYSTEM DEMO
                // ----------------------------------------
                case 8 -> {
                    System.out.println("\n--- DISCOUNT DEMO ---");

                    if (currentCustomer == null) {
                        System.out.println("Register a customer first.");
                        break;
                    }

                    double amount = 50.0;
                    DiscountPolicy policy = DiscountPolicy.loyaltyDiscountPolicy();

                    System.out.println("Original: " + amount);
                    System.out.println("After Discount: " + policy.apply(amount, currentCustomer));
                }

                // ----------------------------------------
                // 9. Pattern matching demo
                // ----------------------------------------
                case 9 -> {
                    System.out.println("\n--- PATTERN MATCHING DEMO ---");

                    Object obj = currentCustomer != null ? currentCustomer : "No customer";

                    if (obj instanceof Customer) {
                        Customer c = (Customer) obj;
                        System.out.println("Customer: " + c.getFullName());
                    } else if (obj instanceof String) {
                        String s = (String) obj;
                        System.out.println("Message: " + s);
                    } else {
                        System.out.println("Unknown type");
                    }
                }

                // ----------------------------------------
                // 10. Exit
                // ----------------------------------------
                case 10 -> {
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
