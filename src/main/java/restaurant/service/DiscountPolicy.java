package restaurant.service;

import restaurant.model.Customer;

public interface DiscountPolicy {

    double apply(double amount, Customer customer);

    static DiscountPolicy loyaltyDiscountPolicy() {
        return (amount, customer) -> customer.getLoyaltyPoints() > 100
                ? amount * 0.9
                : amount;
    }
}
