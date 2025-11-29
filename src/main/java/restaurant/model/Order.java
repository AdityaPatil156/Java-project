package restaurant.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final Customer customer;
    private final Table table;
    private final List<MenuItemRecord> items = new ArrayList<>();
    private final LocalDateTime created = LocalDateTime.now();

    public Order(Customer c, Table t) {
        this.customer = c;
        this.table = t;
    }

    public double calculateTotal() {
        double sum = 0;
        for (var i : items) {
            sum += i.price();
        }
        return sum;
    }

    public void addItem(MenuItemRecord item) {
        items.add(item);
    }

    @Override
    public String toString() {
        return "Order by " + customer.getFullName() +
               " at table " + table.getNumber() +
               " on " + created +
               " Total=" + calculateTotal();
    }
}
