package restaurant.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import restaurant.exception.*;
import restaurant.model.*;

public class RestaurantService {

    private final Table[] tables;
    private final List<Order> orders = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    public RestaurantService(String name, int count) {
        this.tables = new Table[count];
        for(int i = 0; i < count; i++) {
            tables[i] = new Table(i + 1);
        }
    }

    public void registerCustomer(Customer c) {
        customers.add(c);
    }

    public Order createOrder(Customer c, int tableNo, MenuItemRecord... items)
            throws TableNotAvailableException {

        Table t = tables[tableNo - 1];
        if (t.getStatus() != TableStatus.AVAILABLE) {
            throw new TableNotAvailableException("Table not free");
        }

        Order o = new Order(c, t);
        for (var i : items) {
            o.addItem(i);
        }
        orders.add(o);
        return o;
    }

    public List<Order> filterOrders(Predicate<Order> p) {
        List<Order> r = new ArrayList<>();
        for (var o : orders) {
            if (p.test(o)) {
                r.add(o);
            }
        }
        return r;
    }

    public void tryMutateDate(LocalDateTime d) {
        d = d.plusDays(1);
    }
}
